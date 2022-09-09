package cn.iocoder.yudao.module.system.controller.admin.student.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("管理后台 - 学员管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StudentPageReqVO extends PageParam {

    @ApiModelProperty(value = "")
    private String name;

    @ApiModelProperty(value = "")
    private Integer age;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "")
    private Date birthDay;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始")
    private Date beginEnterDate;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束")
    private Date endEnterDate;

    @ApiModelProperty(value = "")
    private String address;

    @ApiModelProperty(value = "")
    private String fatherName;

    @ApiModelProperty(value = "")
    private String motherName;

    @ApiModelProperty(value = "")
    private Integer fatherMobile;

    @ApiModelProperty(value = "")
    private Integer motherMobile;

    @ApiModelProperty(value = "")
    private Integer sex;

}
