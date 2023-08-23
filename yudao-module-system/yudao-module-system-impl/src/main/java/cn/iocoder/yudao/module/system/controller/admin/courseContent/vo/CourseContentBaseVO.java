package cn.iocoder.yudao.module.system.controller.admin.courseContent.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 课程体系 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class CourseContentBaseVO {

    @ApiModelProperty(value = "课程名称")
    private String courseName;

    @ApiModelProperty(value = "课程编码")
    private String courseCode;

    @ApiModelProperty(value = "课程主题")
    private String courseSubject;

    @ApiModelProperty(value = "消课时长:1:1.5小时;2:2小时")
    private String courseTime;

}
