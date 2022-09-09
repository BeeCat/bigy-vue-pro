package cn.iocoder.yudao.module.system.convert.student;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.system.controller.admin.student.vo.StudentCreateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.student.vo.StudentExcelVO;
import cn.iocoder.yudao.module.system.controller.admin.student.vo.StudentRespVO;
import cn.iocoder.yudao.module.system.controller.admin.student.vo.StudentUpdateReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.student.StudentDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 学员管理 Convert
 *
 * @author 毕国勇
 */
@Mapper
public interface StudentConvert {

    StudentConvert INSTANCE = Mappers.getMapper(StudentConvert.class);

    StudentDO convert(StudentCreateReqVO bean);

    StudentDO convert(StudentUpdateReqVO bean);

    StudentRespVO convert(StudentDO bean);

    List<StudentRespVO> convertList(List<StudentDO> list);

    PageResult<StudentRespVO> convertPage(PageResult<StudentDO> page);

    List<StudentExcelVO> convertList02(List<StudentDO> list);

}
