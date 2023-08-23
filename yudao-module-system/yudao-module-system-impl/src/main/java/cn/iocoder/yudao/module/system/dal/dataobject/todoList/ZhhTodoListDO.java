package cn.iocoder.yudao.module.system.dal.dataobject.todoList;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Date;

/**
 * todolist 待办项 DO
 *
 * @author 芋道源码
 */
@TableName("zhh_todo_list")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZhhTodoListDO extends BaseDO {

    /**
     * 
     */
    @TableId
    private Integer id;
    /**
     * 任务名
     */
    private String taskName;
    /**
     * 任务内容
     */
    private String taskContent;
    /**
     * 优先级
     */
    private Integer taskPriority;
    /**
     * 重要紧急
     */
    private Integer taskImportant;
    /**
     * 分配人
     */
    private String assignee;
    /**
     * 分配时间
     */
    private Date assignTime;
    /**
     * 任务结束时间
     */
    private Date deadTime;
    /**
     * 执行人
     */
    private String receiver;
    /**
     * 计划执行开始时间
     */
    private Date doStartTime;
    /**
     * 计划执行结束时间
     */
    private Date doEndTime;
    /**
     * 预估时间（h）
     */
    private String doCostTime;
    /**
     * 执行想法
     */
    private String doMind;
    /**
     * 所属部门
     */
    private String ownerPart;
    /**
     * 所属项目
     */
    private String ownerProject;
    /**
     * 是否完成
     */
    private Integer successFlag;
    /**
     * 完成情况
     */
    private String checkConfirm;
    /**
     * 审核人
     */
    private String reviewer;
    /**
     * 审核结果
     */
    private String reviewResult;
    /**
     * 审核时间
     */
    private Date reviewTime;
    /**
     * 审核想法
     */
    private String reviewMind;

}
