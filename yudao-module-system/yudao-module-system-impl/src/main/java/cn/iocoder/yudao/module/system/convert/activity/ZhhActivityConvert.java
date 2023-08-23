package cn.iocoder.yudao.module.system.convert.activity;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.activity.vo.ZhhActivityCreateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.activity.vo.ZhhActivityExcelVO;
import cn.iocoder.yudao.module.system.controller.admin.activity.vo.ZhhActivityRespVO;
import cn.iocoder.yudao.module.system.controller.admin.activity.vo.ZhhActivityUpdateReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.activity.ZhhActivityDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 *  Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface ZhhActivityConvert {

    ZhhActivityConvert INSTANCE = Mappers.getMapper(ZhhActivityConvert.class);

    ZhhActivityDO convert(ZhhActivityCreateReqVO bean);

    ZhhActivityDO convert(ZhhActivityUpdateReqVO bean);

    ZhhActivityRespVO convert(ZhhActivityDO bean);

    List<ZhhActivityRespVO> convertList(List<ZhhActivityDO> list);

    PageResult<ZhhActivityRespVO> convertPage(PageResult<ZhhActivityDO> page);

    List<ZhhActivityExcelVO> convertList02(List<ZhhActivityDO> list);

}
