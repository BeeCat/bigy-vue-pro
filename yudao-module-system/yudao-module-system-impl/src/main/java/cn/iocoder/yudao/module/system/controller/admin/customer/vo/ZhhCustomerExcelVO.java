package cn.iocoder.yudao.module.system.controller.admin.customer.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 *  Excel VO
 *
 * @author 芋道源码
 */
@Data
public class ZhhCustomerExcelVO {

    @ExcelProperty("")
    private Integer id;

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("小名")
    private String title;

    @ExcelProperty("来源")
    private String sourceFrom;

    @ExcelProperty("来源老师")
    private String teacherFrom;

    @ExcelProperty("来源销售")
    private String saleFrom;

    @ExcelProperty("年龄")
    private String age;

    @ExcelProperty("生日")
    private Date birthDay;

    @ExcelProperty("报名日期")
    private Date offerDate;

    @ExcelProperty("学校")
    private String school;

    @ExcelProperty("小区")
    private String village;

    @ExcelProperty("地址")
    private String address;

    @ExcelProperty("爸爸名")
    private String fatherName;

    @ExcelProperty("妈妈名")
    private String motherName;

    @ExcelProperty("爸爸电话")
    private String fatherMobile;

    @ExcelProperty("妈妈电话")
    private String motherMobile;

    @ExcelProperty("客户等级")
    private String gradeType;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("性别")
    private Integer sex;

    @ExcelProperty("创建时间")
    private Date createTime;

}
