package cn.iocoder.yudao.module.system.controller.admin.classRoom.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 课程教室更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ClassRoomUpdateReqVO extends ClassRoomBaseVO {

    @ApiModelProperty(value = "", required = true)
    @NotNull(message = "不能为空")
    private Integer id;

}
