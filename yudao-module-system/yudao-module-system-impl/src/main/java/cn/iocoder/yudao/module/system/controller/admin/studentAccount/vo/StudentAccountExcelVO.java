package cn.iocoder.yudao.module.system.controller.admin.studentAccount.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 学生账户 Excel VO
 *
 * @author 毕国勇
 */
@Data
public class StudentAccountExcelVO {

    @ExcelProperty("")
    private Integer id;

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("学生id")
    private Integer studentId;

    @ExcelProperty("课程编码")
    private String classCode;

    @ExcelProperty("购买课程数")
    private Integer buyClassAmount;

    @ApiModelProperty(value = "剩余课程数")
    private Integer haveClassAmount;

    @ExcelProperty("消课课程数")
    private Integer costClassAmount;

    @ExcelProperty("创建时间")
    private Date createTime;

}
