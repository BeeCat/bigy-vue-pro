package cn.iocoder.yudao.module.system.service.activity;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.activity.vo.ZhhActivityCreateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.activity.vo.ZhhActivityExportReqVO;
import cn.iocoder.yudao.module.system.controller.admin.activity.vo.ZhhActivityPageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.activity.vo.ZhhActivityUpdateReqVO;
import cn.iocoder.yudao.module.system.convert.activity.ZhhActivityConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.activity.ZhhActivityDO;
import cn.iocoder.yudao.module.system.dal.mysql.activity.ZhhActivityMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.ACTIVITY_NOT_EXISTS;

/**
 *  Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ZhhActivityServiceImpl implements ZhhActivityService {

    @Resource
    private ZhhActivityMapper activityMapper;

    @Override
    public Integer createActivity(ZhhActivityCreateReqVO createReqVO) {
        // 插入
        ZhhActivityDO activity = ZhhActivityConvert.INSTANCE.convert(createReqVO);
        activityMapper.insert(activity);
        // 返回
        return activity.getId();
    }

    @Override
    public void updateActivity(ZhhActivityUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateActivityExists(updateReqVO.getId());
        // 更新
        ZhhActivityDO updateObj = ZhhActivityConvert.INSTANCE.convert(updateReqVO);
        activityMapper.updateById(updateObj);
    }

    @Override
    public void deleteActivity(Integer id) {
        // 校验存在
        this.validateActivityExists(id);
        // 删除
        activityMapper.deleteById(id);
    }

    private void validateActivityExists(Integer id) {
        if (activityMapper.selectById(id) == null) {
            throw exception(ACTIVITY_NOT_EXISTS);
        }
    }

    @Override
    public ZhhActivityDO getActivity(Integer id) {
        return activityMapper.selectById(id);
    }

    @Override
    public List<ZhhActivityDO> getActivityList(Collection<Integer> ids) {
        return activityMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ZhhActivityDO> getActivityPage(ZhhActivityPageReqVO pageReqVO) {
        return activityMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ZhhActivityDO> getActivityList(ZhhActivityExportReqVO exportReqVO) {
        return activityMapper.selectList(exportReqVO);
    }

}
