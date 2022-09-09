package cn.iocoder.yudao.module.system.controller.admin.student.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("管理后台 - 学员管理 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StudentRespVO extends StudentBaseVO {

    @ApiModelProperty(value = "", required = true)
    private Integer id;

}
