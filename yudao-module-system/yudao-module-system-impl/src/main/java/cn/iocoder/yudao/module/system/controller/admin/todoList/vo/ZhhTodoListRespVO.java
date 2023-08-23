package cn.iocoder.yudao.module.system.controller.admin.todoList.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@ApiModel("管理后台 - todolist 待办项 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ZhhTodoListRespVO extends ZhhTodoListBaseVO {

    @ApiModelProperty(value = "", required = true)
    private Integer id;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;
    /**
     * 审核人
     */
    private String reviewerName;
    /**
     * 执行人
     */
    private String receiverName;
    /**
     * 分配人
     */
    private String assigneeName;

}
