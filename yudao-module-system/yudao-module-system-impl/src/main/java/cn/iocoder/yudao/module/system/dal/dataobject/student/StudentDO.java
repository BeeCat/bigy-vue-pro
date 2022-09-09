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
     * 
     */
    @TableId
    private Integer id;
    /**
     * 
     */
    private String name;
    /**
     * 
     */
    private Integer age;
    /**
     * 
     */
    private Date birthDay;
    /**
     * 
     */
    private Date enterDate;
    /**
     * 
     */
    private String address;
    /**
     * 
     */
    private String fatherName;
    /**
     * 
     */
    private String motherName;
    /**
     * 
     */
    private Integer fatherMobile;
    /**
     * 
     */
    private Integer motherMobile;
    /**
     * 
     */
    private Integer sex;

}
