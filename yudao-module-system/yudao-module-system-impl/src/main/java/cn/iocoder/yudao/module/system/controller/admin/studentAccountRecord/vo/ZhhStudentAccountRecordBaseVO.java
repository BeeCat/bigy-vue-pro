package cn.iocoder.yudao.module.system.controller.admin.studentAccountRecord.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 学生账户 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class ZhhStudentAccountRecordBaseVO {

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "学生账户id")
    private Integer studentAccountId;

    @ApiModelProperty(value = "客户id")
    private Integer customerId;

    @ApiModelProperty(value = "学生id")
    private Integer studentId;

    @ApiModelProperty(value = "", required = true)
    private String coursePackage;

    @ApiModelProperty(value = "来源")
    private String sourceFrom;

    @ApiModelProperty(value = "来源老师")
    private String teacherFrom;

    @ApiModelProperty(value = "来源销售")
    private String saleFrom;

    @ApiModelProperty(value = "课程编码")
    private String classCode;

    @ApiModelProperty(value = "购买课程数")
    private Integer buyClassAmount;

    @ApiModelProperty(value = "剩余课程数")
    private Integer haveClassAmount;

    @ApiModelProperty(value = "消课课程数")
    private Integer costClassAmount;

}
