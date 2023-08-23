package cn.iocoder.yudao.module.system.controller.admin.classStudent.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@ApiModel("管理后台 - 班级学生 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ClassStudentRespVO extends ClassStudentBaseVO {

    @ApiModelProperty(value = "", required = true)
    private Integer id;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

    /**
     * 剩余课程数
     */
    @ApiModelProperty(value = "剩余课程数", required = true)
    private Integer haveClassAmount;

    @ApiModelProperty(value = "消费课程数", required = true)
    private Integer costClassAmount;

}
