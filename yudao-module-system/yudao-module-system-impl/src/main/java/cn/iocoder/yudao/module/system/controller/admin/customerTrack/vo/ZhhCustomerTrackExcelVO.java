package cn.iocoder.yudao.module.system.controller.admin.customerTrack.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 *  Excel VO
 *
 * @author 芋道源码
 */
@Data
public class ZhhCustomerTrackExcelVO {

    @ExcelProperty("")
    private Integer id;

    @ExcelProperty("客户id")
    private Integer customerId;

    @ExcelProperty("跟踪老师")
    private String trackTeacher;

    @ExcelProperty("跟踪内容")
    private String trackContent;

    @ExcelProperty("跟踪结果")
    private String trackResult;

    @ExcelProperty("沟通前等级")
    private String beforeCustomerGrade;

    @ExcelProperty("沟通后等级")
    private String afterCustomerGrade;

    @ExcelProperty("沟通经验心得")
    private String trackExperience;

    @ExcelProperty("等级是否变化")
    private Integer gradeChangeFlag;

    @ExcelProperty("是否邀约成功")
    private Integer inviteFlag;

    @ExcelProperty("是否成单")
    private Integer orderFlag;

    @ExcelProperty("创建时间")
    private Date createTime;

}
