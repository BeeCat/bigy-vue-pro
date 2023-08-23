package cn.iocoder.yudao.module.system.controller.admin.customer.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
*  Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class ZhhCustomerBaseVO {

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

    @ApiModelProperty(value = "生日")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date birthDay;

    @ApiModelProperty(value = "报名日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date offerDate;

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

}
