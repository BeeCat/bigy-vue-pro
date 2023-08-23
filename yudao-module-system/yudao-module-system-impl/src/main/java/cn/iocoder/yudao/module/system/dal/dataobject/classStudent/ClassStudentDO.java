package cn.iocoder.yudao.module.system.dal.dataobject.classStudent;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Date;

/**
 * 班级学生 DO
 *
 * @author 毕国勇
 */
@TableName("zhh_class_student")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassStudentDO extends BaseDO {

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
     * 班级学生
     */
    private String studentCode;
    /**
     * 班级姓名
     */
    private String studentName;

    /**
     * 阶段类型
     */
    private String stageType;

    /**
     * 失效日期
     */
    private Date validateTime;



}
