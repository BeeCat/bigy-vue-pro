package cn.iocoder.yudao.module.system.convert.courseClass;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.courseClass.vo.CourseClassCreateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.courseClass.vo.CourseClassExcelVO;
import cn.iocoder.yudao.module.system.controller.admin.courseClass.vo.CourseClassRespVO;
import cn.iocoder.yudao.module.system.controller.admin.courseClass.vo.CourseClassUpdateReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.courseClass.CourseClassDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 课程班级 Convert
 *
 * @author 毕国勇
 */
@Mapper
public interface CourseClassConvert {

    CourseClassConvert INSTANCE = Mappers.getMapper(CourseClassConvert.class);

    CourseClassDO convert(CourseClassCreateReqVO bean);

    CourseClassDO convert(CourseClassUpdateReqVO bean);

    CourseClassRespVO convert(CourseClassDO bean);

    List<CourseClassRespVO> convertList(List<CourseClassDO> list);

    PageResult<CourseClassRespVO> convertPage(PageResult<CourseClassDO> page);

    List<CourseClassExcelVO> convertList02(List<CourseClassDO> list);

}
