package cn.iocoder.yudao.module.system.controller.admin.costClassRecord.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 消课记录 Excel VO
 *
 * @author 毕国勇
 */
@Data
public class CostClassRecordExcelVO {

    @ExcelProperty("")
    private Integer id;

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("学生id")
    private String studentCode;

    @ExcelProperty("班级编码")
    private String classCode;

    @ExcelProperty("课程编码")
    private String courseCode;

    @ExcelProperty("消课老师")
    private String costTeacherCode;

    @ExcelProperty("课程记录编码")
    private String courseRecordCode;

    @ExcelProperty("课程内容编码")
    private String courseContentCode;

    @ExcelProperty("消课时间")
    private Date costTime;

    @ExcelProperty("创建时间")
    private Date createTime;

}
