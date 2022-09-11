package cn.iocoder.yudao.module.system.dal.dataobject.student;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

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
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 小名
     */
    private String title;
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
     * 爸爸手机号
     */
    private Integer fatherMobile;
    /**
     * 妈妈手机号
     */
    private Integer motherMobile;
    /**
     * 性别
     *
     */
    private Integer sex;

}
