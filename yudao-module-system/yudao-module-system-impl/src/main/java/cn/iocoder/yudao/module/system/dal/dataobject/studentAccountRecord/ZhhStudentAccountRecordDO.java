package cn.iocoder.yudao.module.system.dal.dataobject.studentAccountRecord;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 学生账户 DO
 *
 * @author 芋道源码
 */
@TableName("zhh_student_account_record")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZhhStudentAccountRecordDO extends BaseDO {

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
     * 学生账户id
     */
    private Integer studentAccountId;
    /**
     * 客户id
     */
    private Integer customerId;
    /**
     * 学生id
     */
    private Integer studentId;

    private String coursePackage;

    /**
     * 来源
     */
    private String sourceFrom;
    /**
     * 来源老师
     */
    private String teacherFrom;
    /**
     * 来源销售
     */
    private String saleFrom;
    /**
     * 课程编码
     */
    private String classCode;
    /**
     * 购买课程数
     */
    private Integer buyClassAmount;
    /**
     * 剩余课程数
     */
    private Integer haveClassAmount;
    /**
     * 消课课程数
     */
    private Integer costClassAmount;

}
