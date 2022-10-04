package cn.iocoder.yudao.module.system.controller.admin.classRoom.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 课程教室 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class ClassRoomBaseVO {

    @ApiModelProperty(value = "教室名称")
    private String name;

}
