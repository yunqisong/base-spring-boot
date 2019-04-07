package com.wosummer.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wosummer.base.kit.SecurityKit;
import com.wosummer.base.model.enums.BaseResultEnum;
import com.wosummer.base.model.exceptions.LogicException;
import com.wosummer.base.service.CommonService;
import com.wosummer.demo.entity.*;
import com.wosummer.demo.enums.ArticleStatusEnum;
import com.wosummer.demo.enums.Const;
import com.wosummer.demo.enums.MissionStatusEnum;
import com.wosummer.demo.enums.PayStatusEnum;
import com.wosummer.demo.kit.Kit;
import com.wosummer.demo.mapper.ArticleMapper;
import com.wosummer.demo.mapper.MissionMapper;
import com.wosummer.demo.mapper.RecordMapper;
import com.wosummer.demo.mapper.TagMapper;
import com.wosummer.demo.model.dto.mission.*;
import com.wosummer.demo.model.jwt.JwtUser;
import com.wosummer.demo.model.vo.mission.MissionListVo;
import com.wosummer.demo.model.vo.mission.MissionVo;
import com.wosummer.demo.model.vo.mission.PaidVo;
import com.wosummer.demo.model.vo.mission.RecordVo;
import com.wosummer.demo.service.IMissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.wosummer.demo.enums.Const.*;
import static com.wosummer.demo.kit.Kit.getNotBlankValue;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: MissionServiceImpl
 */
@Slf4j
@SuppressWarnings("ALL")
@Service
public class MissionServiceImpl implements IMissionService {


  private static final String ARTICLE = "article";
  @Autowired
  private TagMapper tagMapper;

  @Autowired
  private RecordMapper recordMapper;

  @Autowired
  private ArticleMapper articleMapper;

  @Autowired
  private CommonService commonService;

  @Autowired
  private MissionMapper missionMapper;

  /**
   * 分页查询
   *
   * @param missionQuery
   * @return
   */
  @Override
  public MissionListVo getAllMissions(MissionQuery missionQuery) {

    IPage<Mission> missionPage = missionMapper.selectPage(missionQuery.toPage(), queryBuilder(missionQuery));

    // allTag
    List<String> allTag = tagMapper.selectList(null).stream().map(Tag::getTag).collect(Collectors.toList());

    return new MissionListVo()
      .setList(missionPage.getRecords().stream().map(MissionVo::new).collect(Collectors.toList()))
      .setTotal(missionPage.getTotal())
      .setTag(allTag)
      ;
  }

  /**
   * 提交任务
   * TODO: 推送 切面
   *
   * @param postMissionForm
   * @return
   */
  @Override
  @PreAuthorize(HAS_ANY_ROLE_ADMIN)
  @Transactional(rollbackFor = Exception.class)
  public List<MissionVo> postMission(PostMissionForm postMissionForm) {

    List<MissionVo> missionVos = new ArrayList<>(postMissionForm.getCount());

    for (int i = 0; i < postMissionForm.getCount(); i++) {
      Mission mission = createNewMisison(postMissionForm);
      commonService.insertOrUpdate(mission, missionMapper);
      missionVos.add(new MissionVo(mission));
    }

    return missionVos;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Boolean deleteMission(String id) {
    if (missionMapper.deleteById(id) > 0) {
      return true;
    }
    throw new LogicException(BaseResultEnum.DELETE_FAIL);
  }

  @Override
  @PreAuthorize(HAS_ANY_ROLE_USER)
  @Transactional(rollbackFor = Exception.class)
  public Boolean userAcceptMission(String id) {
    Mission mission = missionMapper.selectById(id);

    // ID 不对 状态不对 过期
    if (Objects.isNull(mission) || !MissionStatusEnum.acceptAble(mission.getState()) || mission.getDeadline() < DateTime.now().getTime()) {
      throw new LogicException(BaseResultEnum.RECEIVE_FAIL);
    }

    // saveArticle
    JwtUser jwtUser = SecurityKit.currentUser();
    Article article = new Article()
      .setId(Kit.articleId())
      .setAuthor(jwtUser.getUser().getUsername())
      .setTitle("")
      .setContent("")
      .setInfo(new JSONObject().put("authority", 0).put("mid", mission.getId()).toString())
      .setState(ArticleStatusEnum.EDITABLE.getCode());
    commonService.insertOrUpdate(article, articleMapper);

    // save Mission
    // TODO : 不新生成的话虚拟列会放在Update语句中
    Mission newMission = new Mission()
      .setId(id)
      .setType(ARTICLE)
      .setState(MissionStatusEnum.UNSUBMITTED.getCode())
      .setInfo(
        JSONUtil
          .parseObj(mission.getInfo())
          .put("receiverId", jwtUser.getId())
          .put("receiverAccount", jwtUser.getUsername())
          .put("articleId", article.getId())
          .put("receiveTime", DateTime.now().getTime())
          .put("receiverName", Kit.getTargetByExpression("nickname", jwtUser.getUser().getInfo()))
          .toString()
      );

    return commonService.insertOrUpdate(newMission, missionMapper);
  }

  // TODO: 推送 切面

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Boolean submitMission(SubmitMissionForm submitMissionForm) {
    Mission mission = missionMapper.selectById(submitMissionForm.getId());
    if (Objects.isNull(mission) || !MissionStatusEnum.submitAble(mission.getState())) {
      throw new LogicException(BaseResultEnum.SUBMIT_FAIL);
    }

    String receiverId = "receiverId";
    if (!Objects.equals(Kit.getTargetByExpression(receiverId, mission.getInfo()), SecurityKit.currentId())) {
      throw new LogicException(BaseResultEnum.PERMISSION_DENY);
    }

    Mission newMission = new Mission()
      .setId(submitMissionForm.getId())
      .setState(MissionStatusEnum.CHECKING.getCode())
      .setInfo(JSONUtil.parseObj(mission.getInfo()).put("submittime", DateTime.now().getTime()).toString());

    return commonService.insertOrUpdate(newMission, missionMapper);
  }

  // TODO: 推送 切面

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Boolean updateMission(PostMissionForm postMissionForm) {
    if (postMissionForm.getId() == null) {
      new LogicException(BaseResultEnum.NEED_MORE_INFO);
    }

    Mission mission = missionMapper.selectById(postMissionForm.getId());

    if (Objects.isNull(mission)) {
      throw new LogicException(BaseResultEnum.UPDATE_FAIL, "result not find");
    }

    JSONObject info = JSONUtil.parseObj(mission.getInfo());

    Mission newMission = new Mission()
      .setId(mission.getId())
      .setDeadline(postMissionForm.getDeadline())
      .setType(getNotBlankValue(postMissionForm.getType(), mission.getType()))
      .setContent(getNotBlankValue(postMissionForm.getContent(), mission.getContent()))
      .setExampleImages(String.join(DOT, CollUtil.isEmpty(postMissionForm.getExampleImages()) ? Collections.EMPTY_LIST : postMissionForm.getExampleImages()));

    List<MissionStatusEnum> validStates = new ArrayList<>(10);
    MissionStatusEnum stateToUpdate = MissionStatusEnum.getByState(postMissionForm.getState());
    switch (stateToUpdate) {
      case RESOLVED:
      case REJECTED:
      case TOBEMODIFIED:
        validStates.add(MissionStatusEnum.CHECKING);
        break;

      case LOCKED:
        validStates.add(MissionStatusEnum.RESOLVED);
        break;

      default:
        break;
    }

    if (!validStates.contains(MissionStatusEnum.getByState(mission.getState()))) {
      throw new LogicException(BaseResultEnum.UPDATE_FAIL, "invaild state");
    }

    if (stateToUpdate == MissionStatusEnum.RESOLVED) {
      info.put("passtime", DateTime.now());
    }

    newMission.setInfo(info.toString()).setState(postMissionForm.getState());

    return commonService.insertOrUpdate(newMission, missionMapper);
  }

  @Override
  public List<MissionVo> getPaid(PayQuery payQuery) {

    // 判断大小 小的为start 大的为end
    ifSwap(payQuery);

    // 全部时间戳减一天 需求: 筛选出上一天完成的任务
    // endTime 应该是当天时间的23:59 所以加一天
    payQuery.setStartTime(payQuery.getStartTime() - 24 * 60 * 60 * 1000);

    // type 0-Paid.no, 1-Paid.yes 1555603200000 1554565547045 1555689600000
    List<Mission> missions = missionMapper.selectList(
      new QueryWrapper<Mission>()
        .in(Mission.STATE, MissionStatusEnum.RESOLVED.getCode(), MissionStatusEnum.LOCKED.getCode())
        .eq(Mission.PAID, payQuery.getPaidType())
        .between(Mission.PASSTIME, payQuery.getStartTime(), payQuery.getEndTime())
    );

    return missions.stream().map(MissionVo::new).collect(Collectors.toList());
  }

  // TODO 消息推送

  @Override
  @Transactional(rollbackFor = Exception.class)
  public PaidVo postPaid(PostPaidForm postPaidForm) {
    PaidVo paidVo = new PaidVo();
    for (String missionId : postPaidForm.getMissions()) {
      Mission mission = missionMapper.selectById(missionId);

      if (mission == null || !MissionStatusEnum.payAble(mission.getState()) || PayStatusEnum.isPaid(mission.getPaid())) {
        paidVo.getFail().add(missionId);
        continue;
      }

      Mission newMission = new Mission()
        .setId(missionId)
        .setInfo(JSONUtil.parseObj(mission.getInfo()).put("paid", PayStatusEnum.PAID.getCode()).toString())
        .setState(mission.getState());

      commonService.insertOrUpdate(newMission, missionMapper);
      paidVo.getSuccess().add(missionId);
    }

    return paidVo;
  }

  @Override
  public List<RecordVo> getRecords(String id) {

    return recordMapper.selectList(new QueryWrapper<Record>().eq(Record.MID, id)).stream().map(RecordVo::new).collect(Collectors.toList());
  }


  public void ifSwap(PayQuery payQuery) {
    if (payQuery.getEndTime() > payQuery.getStartTime()) {
      Long middleValue = payQuery.getStartTime();
      payQuery.setStartTime(payQuery.getEndTime());
      payQuery.setEndTime(payQuery.getStartTime());
    }
  }


  public Mission createNewMisison(PostMissionForm postMissionForm) {
    Mission mission = new Mission();
    // Info 构建
    JSONObject missionSeedInfo = new JSONObject();
    missionSeedInfo
      .put("paid", PayStatusEnum.NOT_PAY.getCode())
      .put("postid", SecurityKit.currentId())
      .put("rules", JSONArray.parse("[{ type: 1 }]"))
      .putAll(BeanUtil.beanToMap(postMissionForm.getInfo()));

    // 其余信息
    mission
      .setId(Kit.missionId())
      .setCreated(DateTime.now().getTime())
      .setState(MissionStatusEnum.UNCLAIMED.getCode())
      .setType(getNotBlankValue(postMissionForm.getType(), ARTICLE))
      .setDeadline(Objects.isNull(postMissionForm.getDeadline()) ? 24 * 60 * 60 * 1000 + DateTime.now().getTime() : postMissionForm.getDeadline())
      .setContent(postMissionForm.getContent())
      .setExampleImages(String.join(DOT, postMissionForm.getExampleImages()))
      .setInfo(missionSeedInfo.toString());

    return mission;
  }

  /**
   * 构建查询
   *
   * @param missionQuery
   * @return
   */
  private QueryWrapper<Mission> queryBuilder(MissionQuery missionQuery) {
    QueryWrapper<Mission> missionQueryWrapper = new QueryWrapper<>();

    if (Objects.nonNull(missionQuery.getPostId())) {
      missionQueryWrapper.eq(Mission.PAID, missionQuery.getPostId());
    }

    if (Objects.nonNull(missionQuery.getReceiverId())) {
      missionQueryWrapper.eq(Mission.RECEIVERID, missionQuery.getReceiverId());
    }

    if (StrUtil.isNotBlank(missionQuery.getState())) {
      missionQueryWrapper.in(Mission.STATE, missionQuery.getState().split(Const.DOT));
    }

    if (StrUtil.isNotBlank(missionQuery.getTag())) {
      missionQueryWrapper.and(
        mqw -> {
          List<KindTag> kindTags = JSON.parseArray("[" + missionQuery.getTag() + "]", KindTag.class);
          for (KindTag kindTag : kindTags) {
            if (Objects.nonNull(kindTag.getId()) && !Objects.equals(kindTag.getId(), 0L)) {
              mqw.or().apply(jsonSearch("id"), kindTag.getId());
            } else {
              mqw.or().apply(jsonSearch("tag"), kindTag.getTag());
            }
          }
          return mqw;
        }
      );
    }

    missionQueryWrapper
      .orderByAsc(Mission.STATE)
      .orderByDesc(Mission.DEADLINE, Mission.ID);

    return missionQueryWrapper;
  }

  /**
   * JSON搜索
   *
   * @param jsonKey
   * @return
   */
  private String jsonSearch(String jsonKey) {
    return " JSON_SEARCH(" + Mission.TAGS + " ->> '$[*]." + jsonKey + "', 'one', {0}) != '' ";
  }


}

