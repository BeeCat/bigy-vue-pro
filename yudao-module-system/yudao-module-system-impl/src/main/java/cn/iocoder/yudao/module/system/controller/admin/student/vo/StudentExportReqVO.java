package cn.iocoder.yudao.module.system.controller.admin.student.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 学员管理 Excel 导出 Request VO", description = "参数和 StudentPageReqVO 是一致的")
@Data
public class StudentExportReqVO {

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "年龄")
    private String age;

    @ApiModelProperty(value = "小名")
    private String title;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "生日")
    private Date birthDay;

    @ApiModelProperty(value = "客户id")
    private Integer customerId;

    /**
     * 阶段类型: 试听；正式
     */
    @ApiModelProperty(value = "阶段类型")
    private String stageType;

    @ApiModelProperty(value = "地址")
    private String address;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始报名日期")
    private Date beginOfferDate;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束报名日期")
    private Date endOfferDate;

    @ApiModelProperty(value = "爸爸姓名")
    private String fatherName;

    @ApiModelProperty(value = "妈妈姓名")
    private String motherName;

    @ApiModelProperty(value = "家长手机号1")
    private String fatherMobile;

    @ApiModelProperty(value = "家长手机号2")
    private String motherMobile;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "是否删除")
    private Boolean deleted;

}
