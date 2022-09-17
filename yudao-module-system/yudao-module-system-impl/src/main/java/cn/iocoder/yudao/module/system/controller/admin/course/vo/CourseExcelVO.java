package cn.iocoder.yudao.module.system.controller.admin.course.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 课程 Excel VO
 *
 * @author 毕国勇
 */
@Data
public class CourseExcelVO {

    @ExcelProperty("")
    private Integer id;

    @ExcelProperty("课程名称")
    private String courseName;

    @ExcelProperty("课程编码")
    private String courseCode;

    @ExcelProperty("消课时长:1:1.5小时;2:2小时")
    private String courseTime;

    @ExcelProperty("课程排课时间")
    private Integer classPlanTime;

    @ExcelProperty("创建时间")
    private Date createTime;

}
