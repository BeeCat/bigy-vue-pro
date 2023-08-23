package cn.iocoder.yudao.module.system.controller.admin.customerTrack.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class ZhhCustomerTrackBaseVO {

    @ApiModelProperty(value = "客户id")
    private Integer customerId;

    @ApiModelProperty(value = "跟踪老师")
    private String trackTeacher;

    @ApiModelProperty(value = "跟踪内容")
    private String trackContent;

    @ApiModelProperty(value = "跟踪结果")
    private String trackResult;

    @ApiModelProperty(value = "沟通前等级")
    private String beforeCustomerGrade;

    @ApiModelProperty(value = "沟通后等级")
    private String afterCustomerGrade;

    @ApiModelProperty(value = "沟通经验心得")
    private String trackExperience;

    @ApiModelProperty(value = "等级是否变化")
    private Integer gradeChangeFlag;

    @ApiModelProperty(value = "是否邀约成功")
    private Integer inviteFlag;

    @ApiModelProperty(value = "是否成单")
    private Integer orderFlag;

}
