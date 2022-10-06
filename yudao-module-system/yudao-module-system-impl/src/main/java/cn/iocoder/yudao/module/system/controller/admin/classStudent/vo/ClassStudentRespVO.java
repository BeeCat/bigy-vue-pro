package cn.iocoder.yudao.module.system.controller.admin.classStudent.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("管理后台 - 班级学生 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ClassStudentRespVO extends ClassStudentBaseVO {

    @ApiModelProperty(value = "", required = true)
    private Integer id;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

}
