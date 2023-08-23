package cn.iocoder.yudao.module.system.controller.admin.todoListLog.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("管理后台 - todolist 记录分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ZhhTodoListLogPageReqVO extends PageParam {

    @ApiModelProperty(value = "任务id")
    private Integer todoListId;

    @ApiModelProperty(value = "任务状态")
    private Integer todoListStatus;

    @ApiModelProperty(value = "审核人")
    private String todoOperator;

    @ApiModelProperty(value = "审核结果")
    private String todoResult;

    @ApiModelProperty(value = "审核想法")
    private String todoMind;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始创建时间")
    private Date beginCreateTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束创建时间")
    private Date endCreateTime;

}
