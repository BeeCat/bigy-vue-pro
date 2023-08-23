package cn.iocoder.yudao.module.system.service.customer;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.customer.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.customer.ZhhCustomerDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 *  Service 接口
 *
 * @author 芋道源码
 */
public interface ZhhCustomerService {

    /**
     * 创建
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createCustomer(@Valid ZhhCustomerCreateReqVO createReqVO);

    /**
     * 更新
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomer(@Valid ZhhCustomerUpdateReqVO updateReqVO);

    /**
     * 删除
     *
     * @param id 编号
     */
    void deleteCustomer(Integer id);

    /**
     * 获得
     *
     * @param id 编号
     * @return 
     */
    ZhhCustomerDO getCustomer(Integer id);

    /**
     * 获得列表
     *
     * @param ids 编号
     * @return 列表
     */
    List<ZhhCustomerDO> getCustomerList(Collection<Integer> ids);

    /**
     * 获得分页
     *
     * @param pageReqVO 分页查询
     * @return 分页
     */
    PageResult<ZhhCustomerRespVO> getCustomerPage(ZhhCustomerPageReqVO pageReqVO);

    /**
     * 获得列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 列表
     */
    List<ZhhCustomerDO> getCustomerList(ZhhCustomerExportReqVO exportReqVO);

}
