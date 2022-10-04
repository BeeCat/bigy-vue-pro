package cn.iocoder.yudao.module.system.controller.admin.course.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@ApiModel("管理后台 - 课程更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CourseAssignReqVO extends CourseBaseVO {

    @ApiModelProperty(value = "id", required = true)
    @NotNull(message = "不能为空")
    private Integer id;


    @ApiModelProperty(value = "classRoomId", required = true)
    @NotNull(message = "不能为空")
    private Integer classRoomId;


    @ApiModelProperty(value = "teacherId", required = true)
    @NotNull(message = "不能为空")
    private Integer teacherId;

    /**
     * 时间
     */
    @ApiModelProperty(value = "classTimeDicValue", required = true)
    @NotNull(message = "不能为空")
    private String classTimeDicValue;

    /**
     * 周几
     */
    @ApiModelProperty(value = "classDicValue", required = true)
    @NotNull(message = "不能为空")
    private String classDicValue;



}
