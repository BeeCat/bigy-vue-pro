package cn.iocoder.yudao.module.system.controller.admin.costClassRecord.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@ApiModel("管理后台 - 消课记录创建 Request VO")
@Data
@ToString(callSuper = true)
public class CostClassHandCreateReqVO {

    private String studentId;

    private String costClassTeacher;


    private Date costCourseDate;

    private String costCourseClass;

    private String costCourseContent;


}
