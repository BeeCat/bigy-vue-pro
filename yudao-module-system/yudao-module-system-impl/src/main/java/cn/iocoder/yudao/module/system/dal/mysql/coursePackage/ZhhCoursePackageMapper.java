package cn.iocoder.yudao.module.system.dal.mysql.coursePackage;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.system.controller.admin.coursePackage.vo.ZhhCoursePackageExportReqVO;
import cn.iocoder.yudao.module.system.controller.admin.coursePackage.vo.ZhhCoursePackagePageReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.coursePackage.ZhhCoursePackageDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 课程包套餐 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ZhhCoursePackageMapper extends BaseMapperX<ZhhCoursePackageDO> {

    default PageResult<ZhhCoursePackageDO> selectPage(ZhhCoursePackagePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ZhhCoursePackageDO>()
                .likeIfPresent(ZhhCoursePackageDO::getName, reqVO.getName())
                .eqIfPresent(ZhhCoursePackageDO::getSalePrice, reqVO.getSalePrice())
                .eqIfPresent(ZhhCoursePackageDO::getCourseAmount, reqVO.getCourseAmount())
                .betweenIfPresent(ZhhCoursePackageDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(ZhhCoursePackageDO::getId));
    }

    default List<ZhhCoursePackageDO> selectList(ZhhCoursePackageExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ZhhCoursePackageDO>()
                .likeIfPresent(ZhhCoursePackageDO::getName, reqVO.getName())
                .eqIfPresent(ZhhCoursePackageDO::getSalePrice, reqVO.getSalePrice())
                .eqIfPresent(ZhhCoursePackageDO::getCourseAmount, reqVO.getCourseAmount())
                .betweenIfPresent(ZhhCoursePackageDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(ZhhCoursePackageDO::getId));
    }

}
