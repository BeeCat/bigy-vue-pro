package cn.iocoder.yudao.module.system.dal.dataobject.customer;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Date;

/**
 *  DO
 *
 * @author 芋道源码
 */
@TableName("zhh_customer")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZhhCustomerDO extends BaseDO {

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
     * 小名
     */
    private String title;
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
     * 年龄
     */
    private String age;
    /**
     * 生日
     */
    private Date birthDay;
    /**
     * 报名日期
     */
    private Date offerDate;
    /**
     * 学校
     */
    private String school;
    /**
     * 小区
     */
    private String village;
    /**
     * 地址
     */
    private String address;
    /**
     * 爸爸名
     */
    private String fatherName;
    /**
     * 妈妈名
     */
    private String motherName;
    /**
     * 爸爸电话
     */
    private String fatherMobile;
    /**
     * 妈妈电话
     */
    private String motherMobile;
    /**
     * 客户等级
     */
    private String gradeType;
    /**
     * 备注
     */
    private String remark;
    /**
     * 性别
     */
    private Integer sex;

}
