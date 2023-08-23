package cn.iocoder.yudao.module.system.dal.dataobject.customerTrack;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 *  DO
 *
 * @author 芋道源码
 */
@TableName("zhh_customer_track")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZhhCustomerTrackDO extends BaseDO {

    /**
     * 
     */
    @TableId
    private Integer id;
    /**
     * 客户id
     */
    private Integer customerId;
    /**
     * 跟踪老师
     */
    private String trackTeacher;
    /**
     * 跟踪内容
     */
    private String trackContent;
    /**
     * 跟踪结果
     */
    private String trackResult;
    /**
     * 沟通前等级
     */
    private String beforeCustomerGrade;
    /**
     * 沟通后等级
     */
    private String afterCustomerGrade;
    /**
     * 沟通经验心得
     */
    private String trackExperience;
    /**
     * 等级是否变化
     */
    private Integer gradeChangeFlag;
    /**
     * 是否邀约成功
     */
    private Integer inviteFlag;
    /**
     * 是否成单
     */
    private Integer orderFlag;

}
