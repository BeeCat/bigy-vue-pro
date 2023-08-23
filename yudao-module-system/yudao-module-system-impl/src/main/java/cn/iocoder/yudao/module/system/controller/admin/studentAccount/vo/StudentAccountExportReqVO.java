package cn.iocoder.yudao.module.system.controller.admin.studentAccount.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 学生账户 Excel 导出 Request VO", description = "参数和 StudentAccountPageReqVO 是一致的")
@Data
public class StudentAccountExportReqVO {

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "学生id")
    private Integer studentId;

    @ApiModelProperty(value = "课程编码")
    private String classCode;

    @ApiModelProperty(value = "购买课程数")
    private Integer buyClassAmount;

    @ApiModelProperty(value = "剩余课程数")
    private Integer haveClassAmount;

    @ApiModelProperty(value = "消课课程数")
    private Integer costClassAmount;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始创建时间")
    private Date beginCreateTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束创建时间")
    private Date endCreateTime;

}
