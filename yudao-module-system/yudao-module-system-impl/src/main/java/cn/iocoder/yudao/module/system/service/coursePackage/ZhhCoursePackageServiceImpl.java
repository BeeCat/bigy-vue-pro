package cn.iocoder.yudao.module.system.service.coursePackage;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.coursePackage.vo.ZhhCoursePackageCreateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.coursePackage.vo.ZhhCoursePackageExportReqVO;
import cn.iocoder.yudao.module.system.controller.admin.coursePackage.vo.ZhhCoursePackagePageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.coursePackage.vo.ZhhCoursePackageUpdateReqVO;
import cn.iocoder.yudao.module.system.convert.coursePackage.ZhhCoursePackageConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.coursePackage.ZhhCoursePackageDO;
import cn.iocoder.yudao.module.system.dal.mysql.coursePackage.ZhhCoursePackageMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.COURSE_PACKAGE_NOT_EXISTS;

/**
 * 课程包套餐 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ZhhCoursePackageServiceImpl implements ZhhCoursePackageService {

    @Resource
    private ZhhCoursePackageMapper coursePackageMapper;

    @Override
    public Integer createCoursePackage(ZhhCoursePackageCreateReqVO createReqVO) {
        // 插入
        ZhhCoursePackageDO coursePackage = ZhhCoursePackageConvert.INSTANCE.convert(createReqVO);
        coursePackageMapper.insert(coursePackage);
        // 返回
        return coursePackage.getId();
    }

    @Override
    public void updateCoursePackage(ZhhCoursePackageUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateCoursePackageExists(updateReqVO.getId());
        // 更新
        ZhhCoursePackageDO updateObj = ZhhCoursePackageConvert.INSTANCE.convert(updateReqVO);
        coursePackageMapper.updateById(updateObj);
    }

    @Override
    public void deleteCoursePackage(Integer id) {
        // 校验存在
        this.validateCoursePackageExists(id);
        // 删除
        coursePackageMapper.deleteById(id);
    }

    private void validateCoursePackageExists(Integer id) {
        if (coursePackageMapper.selectById(id) == null) {
            throw exception(COURSE_PACKAGE_NOT_EXISTS);
        }
    }

    @Override
    public ZhhCoursePackageDO getCoursePackage(Integer id) {
        return coursePackageMapper.selectById(id);
    }

    @Override
    public List<ZhhCoursePackageDO> getCoursePackageList(Collection<Integer> ids) {
        return coursePackageMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ZhhCoursePackageDO> getCoursePackagePage(ZhhCoursePackagePageReqVO pageReqVO) {
        return coursePackageMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ZhhCoursePackageDO> getCoursePackageList(ZhhCoursePackageExportReqVO exportReqVO) {
        return coursePackageMapper.selectList(exportReqVO);
    }

}
