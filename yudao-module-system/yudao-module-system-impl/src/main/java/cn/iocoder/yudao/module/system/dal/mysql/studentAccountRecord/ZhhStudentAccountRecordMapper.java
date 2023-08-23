package cn.iocoder.yudao.module.system.dal.mysql.studentAccountRecord;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.system.controller.admin.studentAccountRecord.vo.ZhhStudentAccountRecordExportReqVO;
import cn.iocoder.yudao.module.system.controller.admin.studentAccountRecord.vo.ZhhStudentAccountRecordPageReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.studentAccountRecord.ZhhStudentAccountRecordDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 学生账户 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ZhhStudentAccountRecordMapper extends BaseMapperX<ZhhStudentAccountRecordDO> {

    default PageResult<ZhhStudentAccountRecordDO> selectPage(ZhhStudentAccountRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ZhhStudentAccountRecordDO>()
                .likeIfPresent(ZhhStudentAccountRecordDO::getName, reqVO.getName())
                .eqIfPresent(ZhhStudentAccountRecordDO::getStudentAccountId, reqVO.getStudentAccountId())
                .eqIfPresent(ZhhStudentAccountRecordDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(ZhhStudentAccountRecordDO::getStudentId, reqVO.getStudentId())
                .eqIfPresent(ZhhStudentAccountRecordDO::getSourceFrom, reqVO.getSourceFrom())
                .eqIfPresent(ZhhStudentAccountRecordDO::getTeacherFrom, reqVO.getTeacherFrom())
                .eqIfPresent(ZhhStudentAccountRecordDO::getSaleFrom, reqVO.getSaleFrom())
                .eqIfPresent(ZhhStudentAccountRecordDO::getClassCode, reqVO.getClassCode())
                .eqIfPresent(ZhhStudentAccountRecordDO::getBuyClassAmount, reqVO.getBuyClassAmount())
                .eqIfPresent(ZhhStudentAccountRecordDO::getHaveClassAmount, reqVO.getHaveClassAmount())
                .eqIfPresent(ZhhStudentAccountRecordDO::getCostClassAmount, reqVO.getCostClassAmount())
                .betweenIfPresent(ZhhStudentAccountRecordDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(ZhhStudentAccountRecordDO::getId));
    }

    default List<ZhhStudentAccountRecordDO> selectList(ZhhStudentAccountRecordExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ZhhStudentAccountRecordDO>()
                .likeIfPresent(ZhhStudentAccountRecordDO::getName, reqVO.getName())
                .eqIfPresent(ZhhStudentAccountRecordDO::getStudentAccountId, reqVO.getStudentAccountId())
                .eqIfPresent(ZhhStudentAccountRecordDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(ZhhStudentAccountRecordDO::getStudentId, reqVO.getStudentId())
                .eqIfPresent(ZhhStudentAccountRecordDO::getSourceFrom, reqVO.getSourceFrom())
                .eqIfPresent(ZhhStudentAccountRecordDO::getTeacherFrom, reqVO.getTeacherFrom())
                .eqIfPresent(ZhhStudentAccountRecordDO::getSaleFrom, reqVO.getSaleFrom())
                .eqIfPresent(ZhhStudentAccountRecordDO::getClassCode, reqVO.getClassCode())
                .eqIfPresent(ZhhStudentAccountRecordDO::getBuyClassAmount, reqVO.getBuyClassAmount())
                .eqIfPresent(ZhhStudentAccountRecordDO::getHaveClassAmount, reqVO.getHaveClassAmount())
                .eqIfPresent(ZhhStudentAccountRecordDO::getCostClassAmount, reqVO.getCostClassAmount())
                .betweenIfPresent(ZhhStudentAccountRecordDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(ZhhStudentAccountRecordDO::getId));
    }

}
