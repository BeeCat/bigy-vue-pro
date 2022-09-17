package cn.iocoder.yudao.module.system.dal.mysql.costClassRecord;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.system.controller.admin.costClassRecord.vo.CostClassRecordExportReqVO;
import cn.iocoder.yudao.module.system.controller.admin.costClassRecord.vo.CostClassRecordPageReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.costClassRecord.CostClassRecordDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 消课记录 Mapper
 *
 * @author 毕国勇
 */
@Mapper
public interface CostClassRecordMapper extends BaseMapperX<CostClassRecordDO> {

    default PageResult<CostClassRecordDO> selectPage(CostClassRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CostClassRecordDO>()
                .likeIfPresent(CostClassRecordDO::getName, reqVO.getName())
                .eqIfPresent(CostClassRecordDO::getStudentCode, reqVO.getStudentCode())
                .eqIfPresent(CostClassRecordDO::getClassCode, reqVO.getClassCode())
                .eqIfPresent(CostClassRecordDO::getCourseCode, reqVO.getCourseCode())
                .eqIfPresent(CostClassRecordDO::getCostTeacherCode, reqVO.getCostTeacherCode())
                .eqIfPresent(CostClassRecordDO::getCourseRecordCode, reqVO.getCourseRecordCode())
                .eqIfPresent(CostClassRecordDO::getCourseContentCode, reqVO.getCourseContentCode())
                .betweenIfPresent(CostClassRecordDO::getCostTime, reqVO.getBeginCostTime(), reqVO.getEndCostTime())
                .betweenIfPresent(CostClassRecordDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(CostClassRecordDO::getId));
    }

    default List<CostClassRecordDO> selectList(CostClassRecordExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CostClassRecordDO>()
                .likeIfPresent(CostClassRecordDO::getName, reqVO.getName())
                .eqIfPresent(CostClassRecordDO::getStudentCode, reqVO.getStudentCode())
                .eqIfPresent(CostClassRecordDO::getClassCode, reqVO.getClassCode())
                .eqIfPresent(CostClassRecordDO::getCourseCode, reqVO.getCourseCode())
                .eqIfPresent(CostClassRecordDO::getCostTeacherCode, reqVO.getCostTeacherCode())
                .eqIfPresent(CostClassRecordDO::getCourseRecordCode, reqVO.getCourseRecordCode())
                .eqIfPresent(CostClassRecordDO::getCourseContentCode, reqVO.getCourseContentCode())
                .betweenIfPresent(CostClassRecordDO::getCostTime, reqVO.getBeginCostTime(), reqVO.getEndCostTime())
                .betweenIfPresent(CostClassRecordDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(CostClassRecordDO::getId));
    }

}
