package cn.iocoder.yudao.module.system.controller.admin.activity.vo;

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
public class ZhhActivityExcelVO {

    @ExcelProperty("")
    private Integer id;

    @ExcelProperty("活动名称")
    private String name;

    @ExcelProperty("活动内容")
    private String content;

    @ExcelProperty("活动规则")
    private String rule;

    @ExcelProperty("开始时间")
    private String startTime;

    @ExcelProperty("结束时间")
    private String endTime;

    @ExcelProperty("创建时间")
    private Date createTime;

}
