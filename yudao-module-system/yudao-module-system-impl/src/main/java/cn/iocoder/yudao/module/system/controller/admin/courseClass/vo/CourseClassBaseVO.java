package cn.iocoder.yudao.module.system.controller.admin.courseClass.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
* 课程班级 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class CourseClassBaseVO {

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "班级编码")
    private Integer classCode;

    @ApiModelProperty(value = "课程编码")
    private Integer courseCode;

    @ApiModelProperty(value = "消课老师")
    private Integer teacherCode;

    @ApiModelProperty(value = "课程排课时间")
    private String classPlanTime;

    @ApiModelProperty(value = "课程教室")
    private Integer classRoomCode;

    /**
     * 时间
     */
    @ApiModelProperty(value = "课程时间")
    private String classTimeDicValue;

    /**
     * 周几
     */
    @ApiModelProperty(value = "课程周几")
    private String classDicValue;

}
