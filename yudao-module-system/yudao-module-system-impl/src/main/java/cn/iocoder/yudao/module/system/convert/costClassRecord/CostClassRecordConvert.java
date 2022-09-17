package cn.iocoder.yudao.module.system.convert.costClassRecord;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.costClassRecord.vo.CostClassRecordCreateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.costClassRecord.vo.CostClassRecordExcelVO;
import cn.iocoder.yudao.module.system.controller.admin.costClassRecord.vo.CostClassRecordRespVO;
import cn.iocoder.yudao.module.system.controller.admin.costClassRecord.vo.CostClassRecordUpdateReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.costClassRecord.CostClassRecordDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 消课记录 Convert
 *
 * @author 毕国勇
 */
@Mapper
public interface CostClassRecordConvert {

    CostClassRecordConvert INSTANCE = Mappers.getMapper(CostClassRecordConvert.class);

    CostClassRecordDO convert(CostClassRecordCreateReqVO bean);

    CostClassRecordDO convert(CostClassRecordUpdateReqVO bean);

    CostClassRecordRespVO convert(CostClassRecordDO bean);

    List<CostClassRecordRespVO> convertList(List<CostClassRecordDO> list);

    PageResult<CostClassRecordRespVO> convertPage(PageResult<CostClassRecordDO> page);

    List<CostClassRecordExcelVO> convertList02(List<CostClassRecordDO> list);

}
