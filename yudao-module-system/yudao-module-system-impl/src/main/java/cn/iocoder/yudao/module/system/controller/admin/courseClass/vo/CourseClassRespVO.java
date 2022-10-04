package cn.iocoder.yudao.module.system.controller.admin.courseClass.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("管理后台 - 课程班级 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CourseClassRespVO extends CourseClassBaseVO {

    @ApiModelProperty(value = "", required = true)
    private Integer id;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;
    /**
     * 课程周几
     */
    private String classDicDesc;
    /**
     * 课程时间
     */
    private String classTimeDicDesc;

    /**
     *
     */
    private String teacherName;

}
