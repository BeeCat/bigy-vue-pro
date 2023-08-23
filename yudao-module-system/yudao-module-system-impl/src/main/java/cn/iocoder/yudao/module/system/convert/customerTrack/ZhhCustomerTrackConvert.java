package cn.iocoder.yudao.module.system.convert.customerTrack;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.customerTrack.vo.ZhhCustomerTrackCreateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.customerTrack.vo.ZhhCustomerTrackExcelVO;
import cn.iocoder.yudao.module.system.controller.admin.customerTrack.vo.ZhhCustomerTrackRespVO;
import cn.iocoder.yudao.module.system.controller.admin.customerTrack.vo.ZhhCustomerTrackUpdateReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.customerTrack.ZhhCustomerTrackDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 *  Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface ZhhCustomerTrackConvert {

    ZhhCustomerTrackConvert INSTANCE = Mappers.getMapper(ZhhCustomerTrackConvert.class);

    ZhhCustomerTrackDO convert(ZhhCustomerTrackCreateReqVO bean);

    ZhhCustomerTrackDO convert(ZhhCustomerTrackUpdateReqVO bean);

    ZhhCustomerTrackRespVO convert(ZhhCustomerTrackDO bean);

    List<ZhhCustomerTrackRespVO> convertList(List<ZhhCustomerTrackDO> list);

    PageResult<ZhhCustomerTrackRespVO> convertPage(PageResult<ZhhCustomerTrackDO> page);

    List<ZhhCustomerTrackExcelVO> convertList02(List<ZhhCustomerTrackDO> list);

}
