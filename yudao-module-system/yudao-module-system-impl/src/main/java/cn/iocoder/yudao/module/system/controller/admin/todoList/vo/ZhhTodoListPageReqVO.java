package cn.iocoder.yudao.module.system.controller.admin.todoList.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("管理后台 - todolist 待办项分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ZhhTodoListPageReqVO extends PageParam {

    @ApiModelProperty(value = "任务名")
    private String taskName;

    @ApiModelProperty(value = "任务内容")
    private String taskContent;

    @ApiModelProperty(value = "优先级")
    private Integer taskPriority;

    @ApiModelProperty(value = "重要紧急")
    private Integer taskImportant;

    @ApiModelProperty(value = "分配人")
    private String assignee;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始分配时间")
    private Date beginAssignTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束分配时间")
    private Date endAssignTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始任务结束时间")
    private Date beginDeadTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束任务结束时间")
    private Date endDeadTime;

    @ApiModelProperty(value = "执行人")
    private String receiver;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始计划执行开始时间")
    private Date beginDoStartTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束计划执行开始时间")
    private Date endDoStartTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始计划执行结束时间")
    private Date beginDoEndTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束计划执行结束时间")
    private Date endDoEndTime;

    @ApiModelProperty(value = "开始预估时间（h）")
    private String beginDoCostTime;

    @ApiModelProperty(value = "结束预估时间（h）")
    private String endDoCostTime;

    @ApiModelProperty(value = "执行想法")
    private String doMind;

    @ApiModelProperty(value = "所属部门")
    private String ownerPart;

    @ApiModelProperty(value = "所属项目")
    private String ownerProject;

    @ApiModelProperty(value = "是否完成")
    private Integer successFlag;

    @ApiModelProperty(value = "完成情况")
    private String checkConfirm;

    @ApiModelProperty(value = "审核人")
    private String reviewer;

    @ApiModelProperty(value = "审核结果")
    private String reviewResult;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始审核时间")
    private Date beginReviewTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束审核时间")
    private Date endReviewTime;

    @ApiModelProperty(value = "审核想法")
    private String reviewMind;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始创建时间")
    private Date beginCreateTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束创建时间")
    private Date endCreateTime;

}
