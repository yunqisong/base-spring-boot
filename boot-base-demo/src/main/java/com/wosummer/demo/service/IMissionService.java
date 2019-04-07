package com.wosummer.demo.service;

import com.wosummer.demo.model.dto.mission.*;
import com.wosummer.demo.model.vo.mission.MissionListVo;
import com.wosummer.demo.model.vo.mission.MissionVo;
import com.wosummer.demo.model.vo.mission.PaidVo;
import com.wosummer.demo.model.vo.mission.RecordVo;

import java.util.List;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: IMissionService
 */
@SuppressWarnings("ALL")
public interface IMissionService {

  /**
   * 查询Mission
   *
   * @param missionQuery
   * @return
   */
  MissionListVo getAllMissions(MissionQuery missionQuery);

  /**
   * 提交任务
   *
   * @param postMissionForm
   * @return
   */
  List<MissionVo> postMission(PostMissionForm postMissionForm);

  /**
   * 下线任务
   *
   * @param id
   * @return
   */
  Boolean deleteMission(String id);

  /**
   * 领取任务
   *
   * @param id
   * @return
   */
  Boolean userAcceptMission(String id);

  /**
   * 交稿
   *
   * @param submitMissionForm
   * @return
   */
  Boolean submitMission(SubmitMissionForm submitMissionForm);

  /**
   * 更新任务
   *
   * @param postMissionForm
   * @return
   */
  Boolean updateMission(PostMissionForm postMissionForm);

  /**
   * 付款列表
   *
   * @param payQuery
   * @return
   */
  List<MissionVo> getPaid(PayQuery payQuery);

  /**
   * 付款操作
   *
   * @param postPaidForm
   * @return
   */
  PaidVo postPaid(PostPaidForm postPaidForm);

  /**
   * 获取时间轴
   *
   * @param id
   * @return
   */
  List<RecordVo> getRecords(String id);
}
