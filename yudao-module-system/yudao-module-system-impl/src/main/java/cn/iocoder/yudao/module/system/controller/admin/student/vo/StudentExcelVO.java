package cn.iocoder.yudao.module.system.controller.admin.student.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 学员管理 Excel VO
 *
 * @author 毕国勇
 */
@Data
public class StudentExcelVO {

    @ExcelProperty("")
    private Integer id;

    @ExcelProperty("")
    private String name;

    @ExcelProperty("")
    private Integer age;

    @ExcelProperty("")
    private Date birthDay;

    @ExcelProperty("")
    private Date enterDate;

    @ExcelProperty("")
    private String address;

    @ExcelProperty("")
    private String fatherName;

    @ExcelProperty("")
    private String motherName;

    @ExcelProperty("")
    private Integer fatherMobile;

    @ExcelProperty("")
    private Integer motherMobile;

    @ExcelProperty("")
    private Integer sex;

}
