package cn.iocoder.yudao.module.system.dal.dataobject.courseClass;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 课程班级 DO
 *
 * @author 毕国勇
 */
@TableName("zhh_course_class")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseClassDO extends BaseDO {

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
     * 班级编码
     */
    private String classCode;
    /**
     * 课程编码
     */
    private String courseCode;
    /**
     * 消课老师
     */
    private String teacherCode;
    /**
     * 课程排课时间
     */
    private String classPlanTime;
    /**
     * 课程老师
     */
    private String classRoomCode;
    /**
     * 课程周几
     */
    private String classDicValue;
    /**
     * 课程时间
     */
    private String classTimeDicValue;

}
