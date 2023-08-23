package cn.iocoder.yudao.module.system.controller.admin.customerTrack.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("管理后台 - 分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ZhhCustomerTrackPageReqVO extends PageParam {

    @ApiModelProperty(value = "客户id")
    private Integer customerId;

    @ApiModelProperty(value = "跟踪老师")
    private String trackTeacher;

    @ApiModelProperty(value = "跟踪内容")
    private String trackContent;

    @ApiModelProperty(value = "跟踪结果")
    private String trackResult;

    @ApiModelProperty(value = "沟通前等级")
    private String beforeCustomerGrade;

    @ApiModelProperty(value = "沟通后等级")
    private String afterCustomerGrade;

    @ApiModelProperty(value = "沟通经验心得")
    private String trackExperience;

    @ApiModelProperty(value = "等级是否变化")
    private Integer gradeChangeFlag;

    @ApiModelProperty(value = "是否邀约成功")
    private Integer inviteFlag;

    @ApiModelProperty(value = "是否成单")
    private Integer orderFlag;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始创建时间")
    private Date beginCreateTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束创建时间")
    private Date endCreateTime;

}
