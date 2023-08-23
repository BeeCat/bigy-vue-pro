package cn.iocoder.yudao.module.system.controller.admin.studentAccountRecord.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("管理后台 - 学生账户分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ZhhStudentAccountRecordPageReqVO extends PageParam {

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "学生账户id")
    private Integer studentAccountId;

    @ApiModelProperty(value = "客户id")
    private Integer customerId;

    @ApiModelProperty(value = "学生id")
    private Integer studentId;

    @ApiModelProperty(value = "来源")
    private String sourceFrom;

    @ApiModelProperty(value = "来源老师")
    private String teacherFrom;

    @ApiModelProperty(value = "来源销售")
    private String saleFrom;

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
