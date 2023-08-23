package cn.iocoder.yudao.module.system.controller.admin.coursePackage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 课程包套餐 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class ZhhCoursePackageBaseVO {

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

}
