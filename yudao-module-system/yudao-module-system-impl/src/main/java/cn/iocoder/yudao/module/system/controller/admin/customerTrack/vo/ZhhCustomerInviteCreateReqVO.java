package cn.iocoder.yudao.module.system.controller.admin.customerTrack.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@ApiModel("管理后台 - 创建 Request VO")
@Data
@ToString(callSuper = true)
public class ZhhCustomerInviteCreateReqVO {


    @ApiModelProperty(value = "客户id")
    private Integer customerId;

    @ApiModelProperty(value = "跟踪老师")
    private String trackTeacher;

    @ApiModelProperty(value = "跟踪老师")
    private String inviteTeacher;


    @ApiModelProperty(value = "跟踪老师")
    private String inviteClass;

    @ApiModelProperty(value = "试听日期")
    private Date inviteTime;


}
