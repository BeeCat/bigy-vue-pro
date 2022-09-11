package cn.iocoder.yudao.module.system.controller.admin.student.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 学员管理 Excel 导出 Request VO", description = "参数和 StudentPageReqVO 是一致的")
@Data
public class StudentExportReqVO {

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "小名")
    private String title;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "生日")
    private Date birthDay;

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

    @ApiModelProperty(value = "爸爸手机号")
    private Integer fatherMobile;

    @ApiModelProperty(value = "妈妈手机号")
    private Integer motherMobile;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "是否删除")
    private Boolean deleted;

}
