package cn.iocoder.yudao.module.system.service.coursePackage;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.coursePackage.vo.ZhhCoursePackageCreateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.coursePackage.vo.ZhhCoursePackageExportReqVO;
import cn.iocoder.yudao.module.system.controller.admin.coursePackage.vo.ZhhCoursePackagePageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.coursePackage.vo.ZhhCoursePackageUpdateReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.coursePackage.ZhhCoursePackageDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 课程包套餐 Service 接口
 *
 * @author 芋道源码
 */
public interface ZhhCoursePackageService {

    /**
     * 创建课程包套餐
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createCoursePackage(@Valid ZhhCoursePackageCreateReqVO createReqVO);

    /**
     * 更新课程包套餐
     *
     * @param updateReqVO 更新信息
     */
    void updateCoursePackage(@Valid ZhhCoursePackageUpdateReqVO updateReqVO);

    /**
     * 删除课程包套餐
     *
     * @param id 编号
     */
    void deleteCoursePackage(Integer id);

    /**
     * 获得课程包套餐
     *
     * @param id 编号
     * @return 课程包套餐
     */
    ZhhCoursePackageDO getCoursePackage(Integer id);

    /**
     * 获得课程包套餐列表
     *
     * @param ids 编号
     * @return 课程包套餐列表
     */
    List<ZhhCoursePackageDO> getCoursePackageList(Collection<Integer> ids);

    /**
     * 获得课程包套餐分页
     *
     * @param pageReqVO 分页查询
     * @return 课程包套餐分页
     */
    PageResult<ZhhCoursePackageDO> getCoursePackagePage(ZhhCoursePackagePageReqVO pageReqVO);

    /**
     * 获得课程包套餐列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 课程包套餐列表
     */
    List<ZhhCoursePackageDO> getCoursePackageList(ZhhCoursePackageExportReqVO exportReqVO);

}
