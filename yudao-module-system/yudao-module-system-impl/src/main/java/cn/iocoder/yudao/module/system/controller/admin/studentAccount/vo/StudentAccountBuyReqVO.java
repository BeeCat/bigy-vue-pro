package cn.iocoder.yudao.module.system.controller.admin.studentAccount.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@ApiModel("管理后台 - 学生账户更新 Request VO")
@Data
@ToString(callSuper = true)
public class StudentAccountBuyReqVO {

    @ApiModelProperty(value = "", required = true)
    @NotNull(message = "销售老师")
    private String trackTeacher;

    @ApiModelProperty(value = "", required = true)
    @NotNull(message = "授课老师")
    private String classTeacher;

    @ApiModelProperty(value = "", required = true)
    @NotNull(message = "班级为空")
    private String courseClass;

    @ApiModelProperty(value = "", required = true)
    @NotNull(message = "课包为空")
    private String coursePackage;

    @ApiModelProperty(value = "", required = true)
    @NotNull(message = "课程为空")
    private String course;

    @ApiModelProperty(value = "", required = true)
    @NotNull(message = "支付金额")
    private String coursePrice;

    @ApiModelProperty(value = "", required = true)
    @NotNull(message = "支付金额")
    private Integer buyCourseNum;

    @ApiModelProperty(value = "", required = true)
    @NotNull(message = "客户id")
    private String customerId;

    @ApiModelProperty(value = "来源")
    private String sourceFrom;

}
