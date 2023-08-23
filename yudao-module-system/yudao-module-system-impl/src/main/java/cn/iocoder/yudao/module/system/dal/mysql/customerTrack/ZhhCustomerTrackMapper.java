package cn.iocoder.yudao.module.system.dal.mysql.customerTrack;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.system.controller.admin.customerTrack.vo.ZhhCustomerTrackExportReqVO;
import cn.iocoder.yudao.module.system.controller.admin.customerTrack.vo.ZhhCustomerTrackPageReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.customerTrack.ZhhCustomerTrackDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *  Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ZhhCustomerTrackMapper extends BaseMapperX<ZhhCustomerTrackDO> {

    default PageResult<ZhhCustomerTrackDO> selectPage(ZhhCustomerTrackPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ZhhCustomerTrackDO>()
                .eqIfPresent(ZhhCustomerTrackDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(ZhhCustomerTrackDO::getTrackTeacher, reqVO.getTrackTeacher())
                .eqIfPresent(ZhhCustomerTrackDO::getTrackContent, reqVO.getTrackContent())
                .eqIfPresent(ZhhCustomerTrackDO::getTrackResult, reqVO.getTrackResult())
                .eqIfPresent(ZhhCustomerTrackDO::getBeforeCustomerGrade, reqVO.getBeforeCustomerGrade())
                .eqIfPresent(ZhhCustomerTrackDO::getAfterCustomerGrade, reqVO.getAfterCustomerGrade())
                .eqIfPresent(ZhhCustomerTrackDO::getTrackExperience, reqVO.getTrackExperience())
                .eqIfPresent(ZhhCustomerTrackDO::getGradeChangeFlag, reqVO.getGradeChangeFlag())
                .eqIfPresent(ZhhCustomerTrackDO::getInviteFlag, reqVO.getInviteFlag())
                .eqIfPresent(ZhhCustomerTrackDO::getOrderFlag, reqVO.getOrderFlag())
                .betweenIfPresent(ZhhCustomerTrackDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(ZhhCustomerTrackDO::getId));
    }

    default List<ZhhCustomerTrackDO> selectList(ZhhCustomerTrackExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ZhhCustomerTrackDO>()
                .eqIfPresent(ZhhCustomerTrackDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(ZhhCustomerTrackDO::getTrackTeacher, reqVO.getTrackTeacher())
                .eqIfPresent(ZhhCustomerTrackDO::getTrackContent, reqVO.getTrackContent())
                .eqIfPresent(ZhhCustomerTrackDO::getTrackResult, reqVO.getTrackResult())
                .eqIfPresent(ZhhCustomerTrackDO::getBeforeCustomerGrade, reqVO.getBeforeCustomerGrade())
                .eqIfPresent(ZhhCustomerTrackDO::getAfterCustomerGrade, reqVO.getAfterCustomerGrade())
                .eqIfPresent(ZhhCustomerTrackDO::getTrackExperience, reqVO.getTrackExperience())
                .eqIfPresent(ZhhCustomerTrackDO::getGradeChangeFlag, reqVO.getGradeChangeFlag())
                .eqIfPresent(ZhhCustomerTrackDO::getInviteFlag, reqVO.getInviteFlag())
                .eqIfPresent(ZhhCustomerTrackDO::getOrderFlag, reqVO.getOrderFlag())
                .betweenIfPresent(ZhhCustomerTrackDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(ZhhCustomerTrackDO::getId));
    }

}
