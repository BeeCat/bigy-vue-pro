package cn.iocoder.yudao.module.system.dal.dataobject.costClassRecord;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 消课记录 DO
 *
 * @author 毕国勇
 */
@TableName("zhh_cost_class_record")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CostClassRecordDO extends BaseDO {

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
    private String studentCode;
    /**
     * 学生id
     */
    private String studentName;
    /**
     * 班级编码
     */
    private String classCode;
    /**
     * 课程编码
     */
    private String courseCode;

    @ApiModelProperty(value = "消课类型：1:到课；0:请假")
    private Integer costClassType;

    /**
     * 消课老师
     */
    private String costTeacherCode;
    /**
     * 消课老师
     */
    private String costTeacherName;
    /**
     * 课程记录编码
     */
    private String courseRecordCode;
    /**
     * 课程内容编码
     */
    private String courseContentCode;
    /**
     * 消课时间
     */
    private Date costTime;

}
