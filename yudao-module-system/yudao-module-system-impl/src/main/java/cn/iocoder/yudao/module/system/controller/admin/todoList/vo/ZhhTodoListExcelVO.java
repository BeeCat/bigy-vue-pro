package cn.iocoder.yudao.module.system.controller.admin.todoList.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * todolist 待办项 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class ZhhTodoListExcelVO {

    @ExcelProperty("")
    private Integer id;

    @ExcelProperty("任务名")
    private String taskName;

    @ExcelProperty("任务内容")
    private String taskContent;

    @ExcelProperty("优先级")
    private Integer taskPriority;

    @ExcelProperty("重要紧急")
    private Integer taskImportant;

    @ExcelProperty("分配人")
    private String assignee;

    @ExcelProperty("分配时间")
    private Date assignTime;

    @ExcelProperty("任务结束时间")
    private Date deadTime;

    @ExcelProperty("执行人")
    private String receiver;

    @ExcelProperty("计划执行开始时间")
    private Date doStartTime;

    @ExcelProperty("计划执行结束时间")
    private Date doEndTime;

    @ExcelProperty("预估时间（h）")
    private String doCostTime;

    @ExcelProperty("执行想法")
    private String doMind;

    @ExcelProperty("所属部门")
    private String ownerPart;

    @ExcelProperty("所属项目")
    private String ownerProject;

    @ExcelProperty("是否完成")
    private Integer successFlag;

    @ExcelProperty("完成情况")
    private String checkConfirm;

    @ExcelProperty("审核人")
    private String reviewer;

    @ExcelProperty("审核结果")
    private String reviewResult;

    @ExcelProperty("审核时间")
    private Date reviewTime;

    @ExcelProperty("审核想法")
    private String reviewMind;

    @ExcelProperty("创建时间")
    private Date createTime;

}
