package cn.iocoder.yudao.module.system.dal.dataobject.studentAccount;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 学生账户 DO
 *
 * @author 毕国勇
 */
@TableName("zhh_student_account")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentAccountDO extends BaseDO {

    /**
     * 
     */
    @TableId
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 学生id
     */
    private Integer studentId;
    /**
     * 课程编码
     */
    private String classCode;
    /**
     * 购买课程数
     */
    private Integer buyClassAmount;

    /**
     * 剩余课程
     */
    private Integer haveClassAmount;

    /**
     * 消课课程数
     */
    private Integer costClassAmount;

}
