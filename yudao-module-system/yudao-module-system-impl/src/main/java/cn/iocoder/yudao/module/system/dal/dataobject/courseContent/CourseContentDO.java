package cn.iocoder.yudao.module.system.dal.dataobject.courseContent;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 课程体系 DO
 *
 * @author 毕国勇
 */
@TableName("zhh_course_content")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseContentDO extends BaseDO {

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
     * 课程主题
     */
    private String courseSubject;
    /**
     * 消课时长:1:1.5小时;2:2小时
     */
    private String courseTime;

}
