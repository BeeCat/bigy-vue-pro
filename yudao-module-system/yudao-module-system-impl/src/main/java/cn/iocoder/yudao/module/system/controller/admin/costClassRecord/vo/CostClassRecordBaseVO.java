package cn.iocoder.yudao.module.system.controller.admin.costClassRecord.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
* 消课记录 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class  CostClassRecordBaseVO {

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "学生id")
    private Integer studentCode;

    @ApiModelProperty(value = "班级编码")
    private Integer classCode;

    @ApiModelProperty(value = "课程编码")
    private Integer courseCode;

    @ApiModelProperty(value = "消课老师")
    private String costTeacherCode;

    @ApiModelProperty(value = "课程记录编码")
    private String courseRecordCode;

    @ApiModelProperty(value = "课程内容编码")
    private String courseContentCode;

    @ApiModelProperty(value = "消课类型：1:到课；0:请假")
    private Integer costClassType;

    @ApiModelProperty(value = "消课时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date costTime;

}
