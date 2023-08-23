package cn.iocoder.yudao.module.system.controller.admin.classStudent.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

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
    private String studentAge;

    @ApiModelProperty(value = "班级性别")
    private String studentSex;

    @ApiModelProperty(value = "班级地址")
    private String studentAddress;

    /**
     * 阶段类型
     */
    @ApiModelProperty(value = "阶段类型")
    private String stageType;

    /**
     * 失效日期
     */
    @ApiModelProperty(value = "失效日期")
    private Date validateTime;

}
