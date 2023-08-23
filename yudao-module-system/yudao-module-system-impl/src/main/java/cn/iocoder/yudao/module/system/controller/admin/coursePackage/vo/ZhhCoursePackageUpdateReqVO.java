package cn.iocoder.yudao.module.system.controller.admin.coursePackage.vo;

import lombok.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 课程包套餐更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ZhhCoursePackageUpdateReqVO extends ZhhCoursePackageBaseVO {

    @ApiModelProperty(value = "", required = true)
    @NotNull(message = "不能为空")
    private Integer id;

}
