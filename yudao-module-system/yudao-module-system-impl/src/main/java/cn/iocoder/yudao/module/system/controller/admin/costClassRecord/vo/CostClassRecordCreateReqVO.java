package cn.iocoder.yudao.module.system.controller.admin.costClassRecord.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel("管理后台 - 消课记录创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CostClassRecordCreateReqVO extends CostClassRecordBaseVO {

    private int handFlag;

}
