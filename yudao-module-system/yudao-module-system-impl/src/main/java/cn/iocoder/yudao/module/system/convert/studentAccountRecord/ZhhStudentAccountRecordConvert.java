package cn.iocoder.yudao.module.system.convert.studentAccountRecord;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.studentAccountRecord.vo.ZhhStudentAccountRecordCreateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.studentAccountRecord.vo.ZhhStudentAccountRecordExcelVO;
import cn.iocoder.yudao.module.system.controller.admin.studentAccountRecord.vo.ZhhStudentAccountRecordRespVO;
import cn.iocoder.yudao.module.system.controller.admin.studentAccountRecord.vo.ZhhStudentAccountRecordUpdateReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.studentAccountRecord.ZhhStudentAccountRecordDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * 学生账户 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface ZhhStudentAccountRecordConvert {

    ZhhStudentAccountRecordConvert INSTANCE = Mappers.getMapper(ZhhStudentAccountRecordConvert.class);

    ZhhStudentAccountRecordDO convert(ZhhStudentAccountRecordCreateReqVO bean);

    ZhhStudentAccountRecordDO convert(ZhhStudentAccountRecordUpdateReqVO bean);

    ZhhStudentAccountRecordRespVO convert(ZhhStudentAccountRecordDO bean);

    List<ZhhStudentAccountRecordRespVO> convertList(List<ZhhStudentAccountRecordDO> list);

    PageResult<ZhhStudentAccountRecordRespVO> convertPage(PageResult<ZhhStudentAccountRecordDO> page);

    List<ZhhStudentAccountRecordExcelVO> convertList02(List<ZhhStudentAccountRecordDO> list);

}
