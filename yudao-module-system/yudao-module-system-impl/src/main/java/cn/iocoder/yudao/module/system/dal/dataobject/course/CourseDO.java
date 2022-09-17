package cn.iocoder.yudao.module.system.dal.dataobject.course;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 课程 DO
 *
 * @author 毕国勇
 */
@TableName("zhh_course")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDO extends BaseDO {

    /**
     * 
     */
    @TableId
    private Integer id;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 课程编码
     */
    private String courseCode;
    /**
     * 消课时长:1:1.5小时;2:2小时
     */
    private String courseTime;
    /**
     * 课程排课时间
     */
    private Integer classPlanTime;

}
