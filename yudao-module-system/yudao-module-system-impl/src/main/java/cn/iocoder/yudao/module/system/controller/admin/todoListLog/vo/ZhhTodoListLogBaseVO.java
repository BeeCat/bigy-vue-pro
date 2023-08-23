package cn.iocoder.yudao.module.system.controller.admin.todoListLog.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* todolist 记录 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class ZhhTodoListLogBaseVO {

    @ApiModelProperty(value = "任务id")
    private Integer todoListId;

    @ApiModelProperty(value = "任务状态")
    private Integer todoListStatus;

    @ApiModelProperty(value = "审核人")
    private String todoOperator;

    @ApiModelProperty(value = "审核结果")
    private String todoResult;

    @ApiModelProperty(value = "审核想法")
    private String todoMind;

}
