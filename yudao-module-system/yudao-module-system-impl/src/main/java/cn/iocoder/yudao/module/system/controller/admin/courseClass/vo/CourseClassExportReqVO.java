package cn.iocoder.yudao.module.system.controller.admin.courseClass.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 课程班级 Excel 导出 Request VO", description = "参数和 CourseClassPageReqVO 是一致的")
@Data
public class CourseClassExportReqVO {

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "班级编码")
    private String classCode;

    @ApiModelProperty(value = "课程编码")
    private String courseCode;

    @ApiModelProperty(value = "消课老师")
    private String teacherCode;

    @ApiModelProperty(value = "开始课程排课时间")
    private String beginClassPlanTime;

    @ApiModelProperty(value = "结束课程排课时间")
    private String endClassPlanTime;

    @ApiModelProperty(value = "课程老师")
    private String classRoomCode;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始课程开始时间")
    private Date beginClassStartTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束课程开始时间")
    private Date endClassStartTime;

    @ApiModelProperty(value = "课程时间")
    private String classTimeDicValue;

    @ApiModelProperty(value = "课程周几")
    private String  classDicValue;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始创建时间")
    private Date beginCreateTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束创建时间")
    private Date endCreateTime;

}
