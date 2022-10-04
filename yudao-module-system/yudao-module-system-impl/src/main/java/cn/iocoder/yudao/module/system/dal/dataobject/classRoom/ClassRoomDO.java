package cn.iocoder.yudao.module.system.dal.dataobject.classRoom;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 课程教室 DO
 *
 * @author 毕国勇
 */
@TableName("zhh_class_room")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassRoomDO extends BaseDO {

    /**
     * 
     */
    @TableId
    private Integer id;
    /**
     * 教室名称
     */
    private String name;

}
