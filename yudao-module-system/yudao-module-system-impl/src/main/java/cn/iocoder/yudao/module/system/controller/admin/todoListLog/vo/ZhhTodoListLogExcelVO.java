package cn.iocoder.yudao.module.system.controller.admin.todoListLog.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * todolist 记录 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class ZhhTodoListLogExcelVO {

    @ExcelProperty("")
    private Integer id;

    @ExcelProperty("任务id")
    private Integer todoListId;

    @ExcelProperty("任务状态")
    private Integer todoListStatus;

    @ExcelProperty("审核人")
    private String todoOperator;

    @ExcelProperty("审核结果")
    private String todoResult;

    @ExcelProperty("审核想法")
    private String todoMind;

    @ExcelProperty("创建时间")
    private Date createTime;

}
