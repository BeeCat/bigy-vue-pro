package cn.iocoder.yudao.module.system.controller.admin.course.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 课程 Excel 导出 Request VO", description = "参数和 CoursePageReqVO 是一致的")
@Data
public class CourseExportReqVO {

    @ApiModelProperty(value = "课程名称")
    private String courseName;

    @ApiModelProperty(value = "课程编码")
    private String courseCode;

    @ApiModelProperty(value = "开始消课时长:1:1.5小时;2:2小时")
    private String beginCourseTime;

    @ApiModelProperty(value = "结束消课时长:1:1.5小时;2:2小时")
    private String endCourseTime;

    @ApiModelProperty(value = "开始课程排课时间")
    private Integer beginClassPlanTime;

    @ApiModelProperty(value = "结束课程排课时间")
    private Integer endClassPlanTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始创建时间")
    private Date beginCreateTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束创建时间")
    private Date endCreateTime;

}
