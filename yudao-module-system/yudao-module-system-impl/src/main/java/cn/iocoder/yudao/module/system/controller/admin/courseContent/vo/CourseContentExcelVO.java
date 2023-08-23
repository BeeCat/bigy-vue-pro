package cn.iocoder.yudao.module.system.controller.admin.courseContent.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 课程体系 Excel VO
 *
 * @author 毕国勇
 */
@Data
public class CourseContentExcelVO {

    @ExcelProperty("")
    private Integer id;

    @ExcelProperty("课程名称")
    private String courseName;

    @ExcelProperty("课程编码")
    private String courseCode;

    @ExcelProperty("课程主题")
    private String courseSubject;

    @ExcelProperty("消课时长:1:1.5小时;2:2小时")
    private String courseTime;

    @ExcelProperty("创建时间")
    private Date createTime;

}
