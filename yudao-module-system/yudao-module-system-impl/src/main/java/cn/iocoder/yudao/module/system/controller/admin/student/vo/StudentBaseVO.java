package cn.iocoder.yudao.module.system.controller.admin.student.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
* 学员管理 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class StudentBaseVO {

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "客户id")
    private Integer customerId;

    @ApiModelProperty(value = "年龄")
    private String age;

    @ApiModelProperty(value = "小名")
    private String title;

    @ApiModelProperty(value = "生日")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date birthDay;
    /**
     * 阶段类型: 试听；正式
     */
    @ApiModelProperty(value = "阶段类型")
    private String stageType;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "报名日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date offerDate;

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
