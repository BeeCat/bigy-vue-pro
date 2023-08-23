package cn.iocoder.yudao.module.system.controller.admin.studentAccountRecord.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 学生账户 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class ZhhStudentAccountRecordExcelVO {

    @ExcelProperty("")
    private Integer id;

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("学生账户id")
    private Integer studentAccountId;

    @ExcelProperty("客户id")
    private Integer customerId;

    @ExcelProperty("学生id")
    private Integer studentId;

    @ExcelProperty("来源")
    private String sourceFrom;

    @ExcelProperty("来源老师")
    private String teacherFrom;

    @ExcelProperty("来源销售")
    private String saleFrom;

    @ExcelProperty("课程编码")
    private String classCode;

    @ExcelProperty("购买课程数")
    private Integer buyClassAmount;

    @ExcelProperty("剩余课程数")
    private Integer haveClassAmount;

    @ExcelProperty("消课课程数")
    private Integer costClassAmount;

    @ExcelProperty("创建时间")
    private Date createTime;

}
