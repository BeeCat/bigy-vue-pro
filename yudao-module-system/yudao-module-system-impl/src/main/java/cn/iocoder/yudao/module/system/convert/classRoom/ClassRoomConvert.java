package cn.iocoder.yudao.module.system.convert.classRoom;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.classRoom.vo.ClassRoomCreateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.classRoom.vo.ClassRoomExcelVO;
import cn.iocoder.yudao.module.system.controller.admin.classRoom.vo.ClassRoomRespVO;
import cn.iocoder.yudao.module.system.controller.admin.classRoom.vo.ClassRoomUpdateReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.classRoom.ClassRoomDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 课程教室 Convert
 *
 * @author 毕国勇
 */
@Mapper
public interface ClassRoomConvert {

    ClassRoomConvert INSTANCE = Mappers.getMapper(ClassRoomConvert.class);

    ClassRoomDO convert(ClassRoomCreateReqVO bean);

    ClassRoomDO convert(ClassRoomUpdateReqVO bean);

    ClassRoomRespVO convert(ClassRoomDO bean);

    List<ClassRoomRespVO> convertList(List<ClassRoomDO> list);

    PageResult<ClassRoomRespVO> convertPage(PageResult<ClassRoomDO> page);

    List<ClassRoomExcelVO> convertList02(List<ClassRoomDO> list);

}
