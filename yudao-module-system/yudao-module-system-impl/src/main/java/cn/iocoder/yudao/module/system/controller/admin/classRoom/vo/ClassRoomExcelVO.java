package cn.iocoder.yudao.module.system.controller.admin.classRoom.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 课程教室 Excel VO
 *
 * @author 毕国勇
 */
@Data
public class ClassRoomExcelVO {

    @ExcelProperty("")
    private Integer id;

    @ExcelProperty("教室名称")
    private String name;

    @ExcelProperty("创建时间")
    private Date createTime;

}
