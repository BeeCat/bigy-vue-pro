package cn.iocoder.yudao.module.system.controller.admin.student.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;


/**
 * 学员管理 Excel VO
 *
 * @author 毕国勇
 */
@Data
public class StudentExcelVO {

    @ExcelProperty("id")
    private Integer id;

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("年龄")
    private Integer age;

    @ExcelProperty("小名")
    private String title;

    @ExcelProperty("生日")
    private Date birthDay;

    @ExcelProperty("地址")
    private String address;

    @ExcelProperty("报名日期")
    private Date offerDate;

    @ExcelProperty("爸爸姓名")
    private String fatherName;

    @ExcelProperty("妈妈姓名")
    private String motherName;

    @ExcelProperty("家长手机号1")
    private String fatherMobile;

    @ExcelProperty("家长手机号2")
    private String motherMobile;

    @ExcelProperty(value = "性别", converter = DictConvert.class)
    @DictFormat("system_user_sex") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Integer sex;

    @ExcelProperty("是否删除")
    private Boolean deleted;

}
