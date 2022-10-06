package cn.iocoder.yudao.module.system.controller.admin.classStudent.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 班级学生 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class ClassStudentBaseVO {

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "班级编码")
    private String classCode;

    @ApiModelProperty(value = "课程编码")
    private String courseCode;

    @ApiModelProperty(value = "班级学生")
    private String studentCode;

    @ApiModelProperty(value = "班级姓名")
    private String studentName;

    @ApiModelProperty(value = "班级年龄")
    private Integer studentAge;

    @ApiModelProperty(value = "班级性别")
    private String studentSex;

    @ApiModelProperty(value = "班级地址")
    private String studentAddress;

}
