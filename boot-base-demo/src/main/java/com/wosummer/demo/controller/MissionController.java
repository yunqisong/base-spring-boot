package com.wosummer.demo.controller;

import com.wosummer.base.controller.BaseController;
import com.wosummer.base.model.Result;
import com.wosummer.demo.model.dto.mission.*;
import com.wosummer.demo.model.vo.mission.MissionListVo;
import com.wosummer.demo.model.vo.mission.MissionVo;
import com.wosummer.demo.model.vo.mission.PaidVo;
import com.wosummer.demo.model.vo.mission.RecordVo;
import com.wosummer.demo.service.IMissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

import static com.wosummer.demo.enums.Const.HAS_ANY_ROLE_ADMIN;

/**
 * @author yunqisong
 * @date 2019-04-05
 * @for: MissionController
 */
@Slf4j
@SuppressWarnings("ALL")
@RestController
@RequestMapping("mission")
@Api(tags = "任务相关 API")
public class MissionController extends BaseController {

  @Autowired
  private IMissionService missionService;

  @GetMapping
  @ApiOperation("获取任务分页列表")
  public Result<MissionListVo> getAllMissions(@Valid MissionQuery missionQuery) {

    return success(missionService.getAllMissions(missionQuery));
  }

  @PostMapping
  @ApiOperation("提交任务")
  public Result<List<MissionVo>> postMission(@Valid @RequestBody PostMissionForm postMissionForm) {
    if (Objects.nonNull(postMissionForm.getId())) {
      return success(missionService.userAcceptMission(postMissionForm.getId()));
    }
    return success(missionService.postMission(postMissionForm));
  }

  @DeleteMapping
  @PreAuthorize(HAS_ANY_ROLE_ADMIN)
  @ApiOperation("删除任务")
  public Result<Boolean> deleteMission(@Valid @NotBlank(message = "要删除的ID不可以为空") String id) {
    return success(missionService.deleteMission(id));
  }

  @PostMapping("article")
  @ApiOperation("交稿")
  public Result<Boolean> submitMission(@Valid @RequestBody SubmitMissionForm submitMissionForm) {

    return success(missionService.submitMission(submitMissionForm));
  }

  @GetMapping("record")
  @ApiOperation("获取 一个任务")
  public Result<List<RecordVo>> getRecords(@Valid @NotBlank(message = "要获取的ID不可以为空") String id) {

    return success(missionService.getRecords(id));
  }

  @GetMapping("pay")
  @ApiOperation("付款列表")
  public Result<List<MissionVo>> getPaid(PayQuery payQuery) {

    return success(missionService.getPaid(payQuery));
  }

  @PostMapping("pay")
  public Result<PaidVo> postPaid(@Valid @RequestBody PostPaidForm postPaidForm) {

    return success(missionService.postPaid(postPaidForm));
  }

  @PostMapping("content")
  @ApiOperation("更新任务")
  public Result<Boolean> updateMission(@Valid @RequestBody PostMissionForm postMissionForm) {

    return success(missionService.updateMission(postMissionForm));
  }
}

