package cn.iocoder.yudao.module.system.controller.admin.customerTrack.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("管理后台 -  Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ZhhCustomerTrackRespVO extends ZhhCustomerTrackBaseVO {

    @ApiModelProperty(value = "", required = true)
    private Integer id;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

}
