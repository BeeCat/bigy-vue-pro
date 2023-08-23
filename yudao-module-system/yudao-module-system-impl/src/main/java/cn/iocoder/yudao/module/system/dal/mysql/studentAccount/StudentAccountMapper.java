package cn.iocoder.yudao.module.system.dal.mysql.studentAccount;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.system.controller.admin.studentAccount.vo.StudentAccountExportReqVO;
import cn.iocoder.yudao.module.system.controller.admin.studentAccount.vo.StudentAccountPageReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.studentAccount.StudentAccountDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 学生账户 Mapper
 *
 * @author 毕国勇
 */
@Mapper
public interface StudentAccountMapper extends BaseMapperX<StudentAccountDO> {

    default PageResult<StudentAccountDO> selectPage(StudentAccountPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<StudentAccountDO>()
                .likeIfPresent(StudentAccountDO::getName, reqVO.getName())
                .eqIfPresent(StudentAccountDO::getStudentId, reqVO.getStudentId())
                .eqIfPresent(StudentAccountDO::getClassCode, reqVO.getClassCode())
                .eqIfPresent(StudentAccountDO::getBuyClassAmount, reqVO.getBuyClassAmount())
                .eqIfPresent(StudentAccountDO::getHaveClassAmount, reqVO.getHaveClassAmount())
                .eqIfPresent(StudentAccountDO::getCostClassAmount, reqVO.getCostClassAmount())
                .betweenIfPresent(StudentAccountDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(StudentAccountDO::getId));
    }

    default List<StudentAccountDO> selectList(StudentAccountExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<StudentAccountDO>()
                .likeIfPresent(StudentAccountDO::getName, reqVO.getName())
                .eqIfPresent(StudentAccountDO::getStudentId, reqVO.getStudentId())
                .eqIfPresent(StudentAccountDO::getClassCode, reqVO.getClassCode())
                .eqIfPresent(StudentAccountDO::getBuyClassAmount, reqVO.getBuyClassAmount())
                .eqIfPresent(StudentAccountDO::getHaveClassAmount, reqVO.getHaveClassAmount())
                .eqIfPresent(StudentAccountDO::getCostClassAmount, reqVO.getCostClassAmount())
                .betweenIfPresent(StudentAccountDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(StudentAccountDO::getId));
    }

}
