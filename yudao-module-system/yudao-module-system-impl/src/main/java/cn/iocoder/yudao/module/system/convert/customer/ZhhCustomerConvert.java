package cn.iocoder.yudao.module.system.convert.customer;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.customer.vo.ZhhCustomerCreateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.customer.vo.ZhhCustomerExcelVO;
import cn.iocoder.yudao.module.system.controller.admin.customer.vo.ZhhCustomerRespVO;
import cn.iocoder.yudao.module.system.controller.admin.customer.vo.ZhhCustomerUpdateReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.customer.ZhhCustomerDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 *  Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface ZhhCustomerConvert {

    ZhhCustomerConvert INSTANCE = Mappers.getMapper(ZhhCustomerConvert.class);

    ZhhCustomerDO convert(ZhhCustomerCreateReqVO bean);

    ZhhCustomerDO convert(ZhhCustomerUpdateReqVO bean);

    ZhhCustomerRespVO convert(ZhhCustomerDO bean);

    List<ZhhCustomerRespVO> convertList(List<ZhhCustomerDO> list);

    PageResult<ZhhCustomerRespVO> convertPage(PageResult<ZhhCustomerDO> page);

    List<ZhhCustomerExcelVO> convertList02(List<ZhhCustomerDO> list);

}
