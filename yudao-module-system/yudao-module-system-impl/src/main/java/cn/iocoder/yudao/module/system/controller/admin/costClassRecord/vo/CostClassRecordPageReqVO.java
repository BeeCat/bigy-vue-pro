package cn.iocoder.yudao.module.system.controller.admin.costClassRecord.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("管理后台 - 消课记录分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CostClassRecordPageReqVO extends PageParam {

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "学生id")
    private String studentCode;

    @ApiModelProperty(value = "学生id")
    private String studentName;

    @ApiModelProperty(value = "班级编码")
    private String classCode;

    @ApiModelProperty(value = "课程编码")
    private String courseCode;

    @ApiModelProperty(value = "消课老师")
    private String costTeacherCode;

    @ApiModelProperty(value = "课程记录编码")
    private String courseRecordCode;

    @ApiModelProperty(value = "课程内容编码")
    private String courseContentCode;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始消课时间")
    private Date beginCostTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束消课时间")
    private Date endCostTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始创建时间")
    private Date beginCreateTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束创建时间")
    private Date endCreateTime;

}
