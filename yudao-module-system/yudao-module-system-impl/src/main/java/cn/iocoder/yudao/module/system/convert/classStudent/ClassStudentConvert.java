package cn.iocoder.yudao.module.system.convert.classStudent;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.classStudent.vo.ClassStudentCreateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.classStudent.vo.ClassStudentExcelVO;
import cn.iocoder.yudao.module.system.controller.admin.classStudent.vo.ClassStudentRespVO;
import cn.iocoder.yudao.module.system.controller.admin.classStudent.vo.ClassStudentUpdateReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.classStudent.ClassStudentDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 班级学生 Convert
 *
 * @author 毕国勇
 */
@Mapper
public interface ClassStudentConvert {

    ClassStudentConvert INSTANCE = Mappers.getMapper(ClassStudentConvert.class);

    ClassStudentDO convert(ClassStudentCreateReqVO bean);

    ClassStudentDO convert(ClassStudentUpdateReqVO bean);

    ClassStudentRespVO convert(ClassStudentDO bean);

    List<ClassStudentRespVO> convertList(List<ClassStudentDO> list);

    PageResult<ClassStudentRespVO> convertPage(PageResult<ClassStudentDO> page);

    List<ClassStudentExcelVO> convertList02(List<ClassStudentDO> list);

}
