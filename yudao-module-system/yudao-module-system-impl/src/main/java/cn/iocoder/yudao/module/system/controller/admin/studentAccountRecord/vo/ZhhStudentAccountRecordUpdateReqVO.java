package cn.iocoder.yudao.module.system.controller.admin.studentAccountRecord.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 学生账户更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ZhhStudentAccountRecordUpdateReqVO extends ZhhStudentAccountRecordBaseVO {

    @ApiModelProperty(value = "", required = true)
    @NotNull(message = "不能为空")
    private Integer id;

}