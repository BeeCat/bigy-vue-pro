package cn.iocoder.yudao.module.system.dal.dataobject.coursePackage;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 课程包套餐 DO
 *
 * @author 芋道源码
 */
@TableName("zhh_course_package")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZhhCoursePackageDO extends BaseDO {

    /**
     * 
     */
    @TableId
    private Integer id;

    /**
     * 套餐名
     */
    private String name;

    /**
     * 套餐金额
     */
    private Integer salePrice;
    /**
     * 套餐课程数
     */
    private String courseAmount;

    /**
     *
     */
    private String packageContent;

    private String studentStageType;

    /**
     *
     */
    private String packageRule;

}
