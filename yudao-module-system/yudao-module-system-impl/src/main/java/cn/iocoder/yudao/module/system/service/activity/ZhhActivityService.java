package cn.iocoder.yudao.module.system.service.activity;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.activity.vo.ZhhActivityCreateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.activity.vo.ZhhActivityExportReqVO;
import cn.iocoder.yudao.module.system.controller.admin.activity.vo.ZhhActivityPageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.activity.vo.ZhhActivityUpdateReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.activity.ZhhActivityDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 *  Service 接口
 *
 * @author 芋道源码
 */
public interface ZhhActivityService {

    /**
     * 创建
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createActivity(@Valid ZhhActivityCreateReqVO createReqVO);

    /**
     * 更新
     *
     * @param updateReqVO 更新信息
     */
    void updateActivity(@Valid ZhhActivityUpdateReqVO updateReqVO);

    /**
     * 删除
     *
     * @param id 编号
     */
    void deleteActivity(Integer id);

    /**
     * 获得
     *
     * @param id 编号
     * @return 
     */
    ZhhActivityDO getActivity(Integer id);

    /**
     * 获得列表
     *
     * @param ids 编号
     * @return 列表
     */
    List<ZhhActivityDO> getActivityList(Collection<Integer> ids);

    /**
     * 获得分页
     *
     * @param pageReqVO 分页查询
     * @return 分页
     */
    PageResult<ZhhActivityDO> getActivityPage(ZhhActivityPageReqVO pageReqVO);

    /**
     * 获得列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 列表
     */
    List<ZhhActivityDO> getActivityList(ZhhActivityExportReqVO exportReqVO);

}
