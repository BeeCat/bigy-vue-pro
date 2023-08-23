package cn.iocoder.yudao.module.system.controller.admin.coursePackage.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 课程包套餐 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class ZhhCoursePackageExcelVO {

    @ExcelProperty("")
    private Integer id;

    @ExcelProperty("套餐名")
    private String name;

    @ExcelProperty("套餐金额")
    private Integer salePrice;

    @ExcelProperty("套餐课程数")
    private String courseAmount;

    @ExcelProperty(value = "套餐内容")
    private String packageContent;

    @ExcelProperty(value = "套餐内容")
    private String studentStageType;

    @ExcelProperty(value = "套餐内容")
    private String packageRule;

    @ExcelProperty("创建时间")
    private Date createTime;

}
