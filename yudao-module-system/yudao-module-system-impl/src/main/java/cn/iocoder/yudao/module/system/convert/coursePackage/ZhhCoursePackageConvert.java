package cn.iocoder.yudao.module.system.convert.coursePackage;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.coursePackage.vo.ZhhCoursePackageCreateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.coursePackage.vo.ZhhCoursePackageExcelVO;
import cn.iocoder.yudao.module.system.controller.admin.coursePackage.vo.ZhhCoursePackageRespVO;
import cn.iocoder.yudao.module.system.controller.admin.coursePackage.vo.ZhhCoursePackageUpdateReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.coursePackage.ZhhCoursePackageDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 课程包套餐 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface ZhhCoursePackageConvert {

    ZhhCoursePackageConvert INSTANCE = Mappers.getMapper(ZhhCoursePackageConvert.class);

    ZhhCoursePackageDO convert(ZhhCoursePackageCreateReqVO bean);

    ZhhCoursePackageDO convert(ZhhCoursePackageUpdateReqVO bean);

    ZhhCoursePackageRespVO convert(ZhhCoursePackageDO bean);

    List<ZhhCoursePackageRespVO> convertList(List<ZhhCoursePackageDO> list);

    PageResult<ZhhCoursePackageRespVO> convertPage(PageResult<ZhhCoursePackageDO> page);

    List<ZhhCoursePackageExcelVO> convertList02(List<ZhhCoursePackageDO> list);

}
