package cn.iocoder.yudao.module.system.controller.admin.classStudent.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 班级学生 Excel VO
 *
 * @author 毕国勇
 */
@Data
public class ClassStudentExcelVO {

    @ExcelProperty("")
    private Integer id;

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("班级编码")
    private String classCode;

    @ExcelProperty("课程编码")
    private String courseCode;

    @ExcelProperty("班级学生")
    private String studentCode;

    @ExcelProperty("班级姓名")
    private String studentName;

    @ExcelProperty("创建时间")
    private Date createTime;

}
