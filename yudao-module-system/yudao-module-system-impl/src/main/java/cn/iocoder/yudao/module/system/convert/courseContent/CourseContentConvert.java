package cn.iocoder.yudao.module.system.convert.courseContent;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.courseContent.vo.CourseContentCreateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.courseContent.vo.CourseContentExcelVO;
import cn.iocoder.yudao.module.system.controller.admin.courseContent.vo.CourseContentRespVO;
import cn.iocoder.yudao.module.system.controller.admin.courseContent.vo.CourseContentUpdateReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.courseContent.CourseContentDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 课程体系 Convert
 *
 * @author 毕国勇
 */
@Mapper
public interface CourseContentConvert {

    CourseContentConvert INSTANCE = Mappers.getMapper(CourseContentConvert.class);

    CourseContentDO convert(CourseContentCreateReqVO bean);

    CourseContentDO convert(CourseContentUpdateReqVO bean);

    CourseContentRespVO convert(CourseContentDO bean);

    List<CourseContentRespVO> convertList(List<CourseContentDO> list);

    PageResult<CourseContentRespVO> convertPage(PageResult<CourseContentDO> page);

    List<CourseContentExcelVO> convertList02(List<CourseContentDO> list);

}
