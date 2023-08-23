package cn.iocoder.yudao.module.system.convert.studentAccount;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.studentAccount.vo.StudentAccountCreateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.studentAccount.vo.StudentAccountExcelVO;
import cn.iocoder.yudao.module.system.controller.admin.studentAccount.vo.StudentAccountRespVO;
import cn.iocoder.yudao.module.system.controller.admin.studentAccount.vo.StudentAccountUpdateReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.studentAccount.StudentAccountDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 学生账户 Convert
 *
 * @author 毕国勇
 */
@Mapper
public interface StudentAccountConvert {

    StudentAccountConvert INSTANCE = Mappers.getMapper(StudentAccountConvert.class);

    StudentAccountDO convert(StudentAccountCreateReqVO bean);

    StudentAccountDO convert(StudentAccountUpdateReqVO bean);

    StudentAccountRespVO convert(StudentAccountDO bean);

    List<StudentAccountRespVO> convertList(List<StudentAccountDO> list);

    PageResult<StudentAccountRespVO> convertPage(PageResult<StudentAccountDO> page);

    List<StudentAccountExcelVO> convertList02(List<StudentAccountDO> list);

}
