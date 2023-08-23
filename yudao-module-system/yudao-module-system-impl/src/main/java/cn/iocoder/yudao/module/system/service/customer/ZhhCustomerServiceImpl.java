package cn.iocoder.yudao.module.system.service.customer;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.NumberUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.customer.vo.*;
import cn.iocoder.yudao.module.system.convert.customer.ZhhCustomerConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.activity.ZhhActivityDO;
import cn.iocoder.yudao.module.system.dal.dataobject.customer.ZhhCustomerDO;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.dal.mysql.customer.ZhhCustomerMapper;
import cn.iocoder.yudao.module.system.service.activity.ZhhActivityService;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import jodd.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.CUSTOMER_NOT_EXISTS;

/**
 *  Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ZhhCustomerServiceImpl implements ZhhCustomerService {

    @Resource
    private ZhhCustomerMapper customerMapper;

    @Resource
    private ZhhActivityService activityService;

    @Resource
    private AdminUserService adminUserService;

    @Override
    public Integer createCustomer(ZhhCustomerCreateReqVO createReqVO) {

        ZhhCustomerExportReqVO vo = new ZhhCustomerExportReqVO();
        vo.setName(createReqVO.getName());
        List<ZhhCustomerDO> list = getCustomerList(vo);
        if (CollectionUtil.isNotEmpty(list)) {
            Assert.isTrue(StringUtil.isNotEmpty(createReqVO.getTitle()) && !createReqVO.getTitle().equals(list.get(0).getTitle()), "该客户已存在：名字和小名一致");
        }
        // 插入
        ZhhCustomerDO customer = ZhhCustomerConvert.INSTANCE.convert(createReqVO);
        customerMapper.insert(customer);
        // 返回
        return customer.getId();
    }

    @Override
    public void updateCustomer(ZhhCustomerUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateCustomerExists(updateReqVO.getId());
        // 更新
        ZhhCustomerDO updateObj = ZhhCustomerConvert.INSTANCE.convert(updateReqVO);
        customerMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomer(Integer id) {
        // 校验存在
        this.validateCustomerExists(id);
        // 删除
        customerMapper.deleteById(id);
    }

    private void validateCustomerExists(Integer id) {
        if (customerMapper.selectById(id) == null) {
            throw exception(CUSTOMER_NOT_EXISTS);
        }
    }

    @Override
    public ZhhCustomerDO getCustomer(Integer id) {
        return customerMapper.selectById(id);
    }

    @Override
    public List<ZhhCustomerDO> getCustomerList(Collection<Integer> ids) {
        return customerMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ZhhCustomerRespVO> getCustomerPage(ZhhCustomerPageReqVO pageReqVO) {
        PageResult<ZhhCustomerDO> pageResult = customerMapper.selectPage(pageReqVO);
        PageResult<ZhhCustomerRespVO> voPageResult = ZhhCustomerConvert.INSTANCE.convertPage(pageResult);
        voPageResult.getList().forEach(it -> {
            String saleFrom = it.getSaleFrom();
            AdminUserDO user = adminUserService.getUser(NumberUtil.parseLong(saleFrom));
            if (user != null) {
                it.setSaleFromName(user.getNickname());
            }
            String sourceFrom = it.getSourceFrom();
            if (NumberUtil.isNumber(sourceFrom)) {
                ZhhActivityDO activity = activityService.getActivity(NumberUtil.parseInt(sourceFrom));
                if (activity != null) {
                    it.setSourceFromName(activity.getName());
                } else {
                    it.setSourceFromName(it.getSourceFromName());
                }
            }
        });
        return voPageResult;
    }

    @Override
    public List<ZhhCustomerDO> getCustomerList(ZhhCustomerExportReqVO exportReqVO) {
        return customerMapper.selectList(exportReqVO);
    }

}
