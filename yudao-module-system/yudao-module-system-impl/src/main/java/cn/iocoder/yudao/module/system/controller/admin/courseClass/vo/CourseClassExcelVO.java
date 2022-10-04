package cn.iocoder.yudao.module.system.controller.admin.courseClass.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 课程班级 Excel VO
 *
 * @author 毕国勇
 */
@Data
public class CourseClassExcelVO {

    @ExcelProperty("")
    private Integer id;

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("班级编码")
    private String classCode;

    @ExcelProperty("课程编码")
    private String courseCode;

    @ExcelProperty("消课老师")
    private String teacherCode;

    @ExcelProperty("课程排课时间")
    private String classPlanTime;

    @ExcelProperty("课程老师")
    private String classRoomCode;

    @ExcelProperty("课程周几")
    private String classDicValue;

    @ExcelProperty("课程时间")
    private String classTimeDicValue;

    @ExcelProperty("创建时间")
    private Date createTime;

}
