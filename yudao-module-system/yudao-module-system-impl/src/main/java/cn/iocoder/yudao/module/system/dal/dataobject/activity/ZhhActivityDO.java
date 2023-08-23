package cn.iocoder.yudao.module.system.dal.dataobject.activity;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 *  DO
 *
 * @author 芋道源码
 */
@TableName("zhh_activity")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZhhActivityDO extends BaseDO {

    /**
     * 
     */
    @TableId
    private Integer id;
    /**
     * 活动名称
     */
    private String name;
    /**
     * 活动内容
     */
    private String content;
    /**
     * 活动规则
     */
    private String rule;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;

}
