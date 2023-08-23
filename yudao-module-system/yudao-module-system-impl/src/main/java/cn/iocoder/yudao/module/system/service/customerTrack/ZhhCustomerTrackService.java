package cn.iocoder.yudao.module.system.service.customerTrack;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.customerTrack.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.customerTrack.ZhhCustomerTrackDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 *  Service 接口
 *
 * @author 芋道源码
 */
public interface ZhhCustomerTrackService {

    /**
     * 创建
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createCustomerTrack(@Valid ZhhCustomerTrackCreateReqVO createReqVO);

    /**
     * 更新
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerTrack(@Valid ZhhCustomerTrackUpdateReqVO updateReqVO);

    /**
     * 删除
     *
     * @param id 编号
     */
    void deleteCustomerTrack(Integer id);

    /**
     * 获得
     *
     * @param id 编号
     * @return 
     */
    ZhhCustomerTrackDO getCustomerTrack(Integer id);

    /**
     * 获得列表
     *
     * @param ids 编号
     * @return 列表
     */
    List<ZhhCustomerTrackDO> getCustomerTrackList(Collection<Integer> ids);

    /**
     * 获得分页
     *
     * @param pageReqVO 分页查询
     * @return 分页
     */
    PageResult<ZhhCustomerTrackDO> getCustomerTrackPage(ZhhCustomerTrackPageReqVO pageReqVO);

    /**
     * 获得列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 列表
     */
    List<ZhhCustomerTrackDO> getCustomerTrackList(ZhhCustomerTrackExportReqVO exportReqVO);

    Integer createCustomerInvite(ZhhCustomerInviteCreateReqVO createReqVO);
}
