package cn.iocoder.yudao.module.system.dal.mysql.activity;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.system.controller.admin.activity.vo.ZhhActivityExportReqVO;
import cn.iocoder.yudao.module.system.controller.admin.activity.vo.ZhhActivityPageReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.activity.ZhhActivityDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *  Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ZhhActivityMapper extends BaseMapperX<ZhhActivityDO> {

    default PageResult<ZhhActivityDO> selectPage(ZhhActivityPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ZhhActivityDO>()
                .likeIfPresent(ZhhActivityDO::getName, reqVO.getName())
                .eqIfPresent(ZhhActivityDO::getContent, reqVO.getContent())
                .eqIfPresent(ZhhActivityDO::getRule, reqVO.getRule())
                .betweenIfPresent(ZhhActivityDO::getStartTime, reqVO.getBeginStartTime(), reqVO.getEndStartTime())
                .betweenIfPresent(ZhhActivityDO::getEndTime, reqVO.getBeginEndTime(), reqVO.getEndEndTime())
                .betweenIfPresent(ZhhActivityDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(ZhhActivityDO::getId));
    }

    default List<ZhhActivityDO> selectList(ZhhActivityExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ZhhActivityDO>()
                .likeIfPresent(ZhhActivityDO::getName, reqVO.getName())
                .eqIfPresent(ZhhActivityDO::getContent, reqVO.getContent())
                .eqIfPresent(ZhhActivityDO::getRule, reqVO.getRule())
                .betweenIfPresent(ZhhActivityDO::getStartTime, reqVO.getBeginStartTime(), reqVO.getEndStartTime())
                .betweenIfPresent(ZhhActivityDO::getEndTime, reqVO.getBeginEndTime(), reqVO.getEndEndTime())
                .betweenIfPresent(ZhhActivityDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(ZhhActivityDO::getId));
    }

}
