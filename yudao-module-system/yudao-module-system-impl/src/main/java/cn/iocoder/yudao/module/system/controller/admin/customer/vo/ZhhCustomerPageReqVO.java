package cn.iocoder.yudao.module.system.controller.admin.customer.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("管理后台 - 分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ZhhCustomerPageReqVO extends PageParam {

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "小名")
    private String title;

    @ApiModelProperty(value = "来源")
    private String sourceFrom;

    @ApiModelProperty(value = "来源老师")
    private String teacherFrom;

    @ApiModelProperty(value = "来源销售")
    private String saleFrom;

    @ApiModelProperty(value = "年龄")
    private String age;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "生日")
    private Date birthDay;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始报名日期")
    private Date beginOfferDate;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束报名日期")
    private Date endOfferDate;

    @ApiModelProperty(value = "学校")
    private String school;

    @ApiModelProperty(value = "小区")
    private String village;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "爸爸名")
    private String fatherName;

    @ApiModelProperty(value = "妈妈名")
    private String motherName;

    @ApiModelProperty(value = "爸爸电话")
    private String fatherMobile;

    @ApiModelProperty(value = "妈妈电话")
    private String motherMobile;

    @ApiModelProperty(value = "客户等级")
    private String gradeType;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始创建时间")
    private Date beginCreateTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束创建时间")
    private Date endCreateTime;

}
