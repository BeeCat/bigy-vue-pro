package cn.iocoder.yudao.module.system.controller.admin.courseClass.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 课程班级更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CourseClassUpdateReqVO extends CourseClassBaseVO {

    @ApiModelProperty(value = "", required = true)
    @NotNull(message = "不能为空")
    private Integer id;

}
