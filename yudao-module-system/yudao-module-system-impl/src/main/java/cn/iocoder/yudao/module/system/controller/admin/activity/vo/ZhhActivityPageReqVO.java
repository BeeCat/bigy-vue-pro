package cn.iocoder.yudao.module.system.controller.admin.activity.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("管理后台 - 分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ZhhActivityPageReqVO extends PageParam {

    @ApiModelProperty(value = "活动名称")
    private String name;

    @ApiModelProperty(value = "活动内容")
    private String content;

    @ApiModelProperty(value = "活动规则")
    private String rule;

    @ApiModelProperty(value = "开始开始时间")
    private String beginStartTime;

    @ApiModelProperty(value = "结束开始时间")
    private String endStartTime;

    @ApiModelProperty(value = "开始结束时间")
    private String beginEndTime;

    @ApiModelProperty(value = "结束结束时间")
    private String endEndTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始创建时间")
    private Date beginCreateTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束创建时间")
    private Date endCreateTime;

}
