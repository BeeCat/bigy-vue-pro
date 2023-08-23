package cn.iocoder.yudao.module.system.controller.admin.coursePackage.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("管理后台 - 课程包套餐分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ZhhCoursePackagePageReqVO extends PageParam {

    @ApiModelProperty(value = "套餐名")
    private String name;

    @ApiModelProperty(value = "套餐金额")
    private Integer salePrice;

    @ApiModelProperty(value = "套餐课程数")
    private String courseAmount;

    @ApiModelProperty(value = "套餐内容")
    private String packageContent;

    @ApiModelProperty(value = "套餐内容")
    private String studentStageType;

    @ApiModelProperty(value = "套餐内容")
    private String packageRule;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始创建时间")
    private Date beginCreateTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束创建时间")
    private Date endCreateTime;

}
