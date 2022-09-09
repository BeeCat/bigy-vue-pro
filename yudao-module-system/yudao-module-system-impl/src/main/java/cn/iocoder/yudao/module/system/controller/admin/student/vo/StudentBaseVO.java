package cn.iocoder.yudao.module.system.controller.admin.student.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
* 学员管理 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class StudentBaseVO {

    @ApiModelProperty(value = "")
    private String name;

    @ApiModelProperty(value = "")
    private Integer age;

    @ApiModelProperty(value = "")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date birthDay;

    @ApiModelProperty(value = "")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date enterDate;

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
