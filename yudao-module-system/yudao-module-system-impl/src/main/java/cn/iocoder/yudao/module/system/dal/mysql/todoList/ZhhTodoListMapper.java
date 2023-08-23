package cn.iocoder.yudao.module.system.dal.mysql.todoList;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.system.controller.admin.todoList.vo.ZhhTodoListExportReqVO;
import cn.iocoder.yudao.module.system.controller.admin.todoList.vo.ZhhTodoListPageReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.todoList.ZhhTodoListDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * todolist 待办项 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ZhhTodoListMapper extends BaseMapperX<ZhhTodoListDO> {

    default PageResult<ZhhTodoListDO> selectPage(ZhhTodoListPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ZhhTodoListDO>()
                .likeIfPresent(ZhhTodoListDO::getTaskName, reqVO.getTaskName())
                .eqIfPresent(ZhhTodoListDO::getTaskContent, reqVO.getTaskContent())
                .eqIfPresent(ZhhTodoListDO::getTaskPriority, reqVO.getTaskPriority())
                .eqIfPresent(ZhhTodoListDO::getTaskImportant, reqVO.getTaskImportant())
                .eqIfPresent(ZhhTodoListDO::getAssignee, reqVO.getAssignee())
                .betweenIfPresent(ZhhTodoListDO::getAssignTime, reqVO.getBeginAssignTime(), reqVO.getEndAssignTime())
                .betweenIfPresent(ZhhTodoListDO::getDeadTime, reqVO.getBeginDeadTime(), reqVO.getEndDeadTime())
                .eqIfPresent(ZhhTodoListDO::getReceiver, reqVO.getReceiver())
                .betweenIfPresent(ZhhTodoListDO::getDoStartTime, reqVO.getBeginDoStartTime(), reqVO.getEndDoStartTime())
                .betweenIfPresent(ZhhTodoListDO::getDoEndTime, reqVO.getBeginDoEndTime(), reqVO.getEndDoEndTime())
                .betweenIfPresent(ZhhTodoListDO::getDoCostTime, reqVO.getBeginDoCostTime(), reqVO.getEndDoCostTime())
                .eqIfPresent(ZhhTodoListDO::getDoMind, reqVO.getDoMind())
                .eqIfPresent(ZhhTodoListDO::getOwnerPart, reqVO.getOwnerPart())
                .eqIfPresent(ZhhTodoListDO::getOwnerProject, reqVO.getOwnerProject())
                .eqIfPresent(ZhhTodoListDO::getSuccessFlag, reqVO.getSuccessFlag())
                .eqIfPresent(ZhhTodoListDO::getCheckConfirm, reqVO.getCheckConfirm())
                .eqIfPresent(ZhhTodoListDO::getReviewer, reqVO.getReviewer())
                .eqIfPresent(ZhhTodoListDO::getReviewResult, reqVO.getReviewResult())
                .betweenIfPresent(ZhhTodoListDO::getReviewTime, reqVO.getBeginReviewTime(), reqVO.getEndReviewTime())
                .eqIfPresent(ZhhTodoListDO::getReviewMind, reqVO.getReviewMind())
                .betweenIfPresent(ZhhTodoListDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(ZhhTodoListDO::getId));
    }

    default List<ZhhTodoListDO> selectList(ZhhTodoListExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ZhhTodoListDO>()
                .likeIfPresent(ZhhTodoListDO::getTaskName, reqVO.getTaskName())
                .eqIfPresent(ZhhTodoListDO::getTaskContent, reqVO.getTaskContent())
                .eqIfPresent(ZhhTodoListDO::getTaskPriority, reqVO.getTaskPriority())
                .eqIfPresent(ZhhTodoListDO::getTaskImportant, reqVO.getTaskImportant())
                .eqIfPresent(ZhhTodoListDO::getAssignee, reqVO.getAssignee())
                .betweenIfPresent(ZhhTodoListDO::getAssignTime, reqVO.getBeginAssignTime(), reqVO.getEndAssignTime())
                .betweenIfPresent(ZhhTodoListDO::getDeadTime, reqVO.getBeginDeadTime(), reqVO.getEndDeadTime())
                .eqIfPresent(ZhhTodoListDO::getReceiver, reqVO.getReceiver())
                .betweenIfPresent(ZhhTodoListDO::getDoStartTime, reqVO.getBeginDoStartTime(), reqVO.getEndDoStartTime())
                .betweenIfPresent(ZhhTodoListDO::getDoEndTime, reqVO.getBeginDoEndTime(), reqVO.getEndDoEndTime())
                .betweenIfPresent(ZhhTodoListDO::getDoCostTime, reqVO.getBeginDoCostTime(), reqVO.getEndDoCostTime())
                .eqIfPresent(ZhhTodoListDO::getDoMind, reqVO.getDoMind())
                .eqIfPresent(ZhhTodoListDO::getOwnerPart, reqVO.getOwnerPart())
                .eqIfPresent(ZhhTodoListDO::getOwnerProject, reqVO.getOwnerProject())
                .eqIfPresent(ZhhTodoListDO::getSuccessFlag, reqVO.getSuccessFlag())
                .eqIfPresent(ZhhTodoListDO::getCheckConfirm, reqVO.getCheckConfirm())
                .eqIfPresent(ZhhTodoListDO::getReviewer, reqVO.getReviewer())
                .eqIfPresent(ZhhTodoListDO::getReviewResult, reqVO.getReviewResult())
                .betweenIfPresent(ZhhTodoListDO::getReviewTime, reqVO.getBeginReviewTime(), reqVO.getEndReviewTime())
                .eqIfPresent(ZhhTodoListDO::getReviewMind, reqVO.getReviewMind())
                .betweenIfPresent(ZhhTodoListDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(ZhhTodoListDO::getId));
    }

}
