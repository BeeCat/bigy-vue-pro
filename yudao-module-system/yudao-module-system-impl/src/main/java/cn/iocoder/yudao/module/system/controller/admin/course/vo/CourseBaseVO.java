package cn.iocoder.yudao.module.system.controller.admin.course.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 课程 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class CourseBaseVO {

    @ApiModelProperty(value = "课程名称")
    private String courseName;

    @ApiModelProperty(value = "课程编码")
    private String courseCode;

    @ApiModelProperty(value = "消课时长:1:1.5小时;2:2小时")
    private String courseTime;

    @ApiModelProperty(value = "课程排课时间")
    private Integer classPlanTime;

}
