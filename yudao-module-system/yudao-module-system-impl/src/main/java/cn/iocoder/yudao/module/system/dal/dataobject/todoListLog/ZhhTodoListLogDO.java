package cn.iocoder.yudao.module.system.dal.dataobject.todoListLog;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * todolist 记录 DO
 *
 * @author 芋道源码
 */
@TableName("zhh_todo_list_log")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZhhTodoListLogDO extends BaseDO {

    /**
     * 
     */
    @TableId
    private Integer id;
    /**
     * 任务id
     */
    private Integer todoListId;
    /**
     * 任务状态
     */
    private Integer todoListStatus;
    /**
     * 审核人
     */
    private String todoOperator;
    /**
     * 审核结果
     */
    private String todoResult;
    /**
     * 审核想法
     */
    private String todoMind;

}
