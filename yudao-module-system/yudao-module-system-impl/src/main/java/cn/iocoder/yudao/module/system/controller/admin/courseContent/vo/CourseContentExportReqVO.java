package cn.iocoder.yudao.module.system.controller.admin.courseContent.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 课程体系 Excel 导出 Request VO", description = "参数和 CourseContentPageReqVO 是一致的")
@Data
public class CourseContentExportReqVO {

    @ApiModelProperty(value = "课程名称")
    private String courseName;

    @ApiModelProperty(value = "课程编码")
    private String courseCode;

    @ApiModelProperty(value = "课程主题")
    private String courseSubject;

    @ApiModelProperty(value = "开始消课时长:1:1.5小时;2:2小时")
    private String beginCourseTime;

    @ApiModelProperty(value = "结束消课时长:1:1.5小时;2:2小时")
    private String endCourseTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始创建时间")
    private Date beginCreateTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束创建时间")
    private Date endCreateTime;

}
