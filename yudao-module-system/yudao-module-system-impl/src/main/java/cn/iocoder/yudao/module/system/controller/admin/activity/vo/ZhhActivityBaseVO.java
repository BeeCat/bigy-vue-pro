package cn.iocoder.yudao.module.system.controller.admin.activity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class ZhhActivityBaseVO {

    @ApiModelProperty(value = "活动名称")
    private String name;

    @ApiModelProperty(value = "活动内容")
    private String content;

    @ApiModelProperty(value = "活动规则")
    private String rule;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

}
