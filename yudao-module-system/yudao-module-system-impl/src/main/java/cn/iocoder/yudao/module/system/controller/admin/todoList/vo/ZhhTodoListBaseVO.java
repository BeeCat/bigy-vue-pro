package cn.iocoder.yudao.module.system.controller.admin.todoList.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
* todolist 待办项 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class ZhhTodoListBaseVO {

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

    @ApiModelProperty(value = "分配时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date assignTime;

    @ApiModelProperty(value = "任务结束时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date deadTime;

    @ApiModelProperty(value = "执行人")
    private String receiver;

    @ApiModelProperty(value = "计划执行开始时间")
    private Date doStartTime;

    @ApiModelProperty(value = "计划执行结束时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date doEndTime;

    @ApiModelProperty(value = "预估时间（h）")
    private String doCostTime;

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

    @ApiModelProperty(value = "审核时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date reviewTime;

    @ApiModelProperty(value = "审核想法")
    private String reviewMind;

}
