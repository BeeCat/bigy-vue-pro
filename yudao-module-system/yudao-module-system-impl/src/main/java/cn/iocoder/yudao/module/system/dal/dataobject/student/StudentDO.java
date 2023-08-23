package cn.iocoder.yudao.module.system.dal.dataobject.student;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

/**
 * 学员管理 DO
 *
 * @author 毕国勇
 */
@TableName("zhh_student")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Integer id;

    @ApiModelProperty(value = "客户id")
    private Integer customerId;

    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private String age;
    /**
     * 小名
     */
    private String title;
    /**
     * 阶段类型: 试听；正式
     */
    private String stageType;
    /**
     * 生日
     */
    private Date birthDay;

    /**
     * 地址
     */
    private String address;
    /**
     * 报名日期
     */
    private Date offerDate;
    /**
     * 爸爸姓名
     */
    private String fatherName;
    /**
     * 妈妈姓名
     */
    private String motherName;
    /**
     * 家长手机号1
     */
    private String fatherMobile;
    /**
     * 家长手机号2
     */
    private String motherMobile;
    /**
     * 性别
     *
     */
    private Integer sex;

}
