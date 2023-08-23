package cn.iocoder.yudao.module.system.dal.mysql.todoListLog;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.system.controller.admin.todoListLog.vo.ZhhTodoListLogExportReqVO;
import cn.iocoder.yudao.module.system.controller.admin.todoListLog.vo.ZhhTodoListLogPageReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.todoListLog.ZhhTodoListLogDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * todolist 记录 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ZhhTodoListLogMapper extends BaseMapperX<ZhhTodoListLogDO> {

    default PageResult<ZhhTodoListLogDO> selectPage(ZhhTodoListLogPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ZhhTodoListLogDO>()
                .eqIfPresent(ZhhTodoListLogDO::getTodoListId, reqVO.getTodoListId())
                .eqIfPresent(ZhhTodoListLogDO::getTodoListStatus, reqVO.getTodoListStatus())
                .eqIfPresent(ZhhTodoListLogDO::getTodoOperator, reqVO.getTodoOperator())
                .eqIfPresent(ZhhTodoListLogDO::getTodoResult, reqVO.getTodoResult())
                .eqIfPresent(ZhhTodoListLogDO::getTodoMind, reqVO.getTodoMind())
                .betweenIfPresent(ZhhTodoListLogDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(ZhhTodoListLogDO::getId));
    }

    default List<ZhhTodoListLogDO> selectList(ZhhTodoListLogExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ZhhTodoListLogDO>()
                .eqIfPresent(ZhhTodoListLogDO::getTodoListId, reqVO.getTodoListId())
                .eqIfPresent(ZhhTodoListLogDO::getTodoListStatus, reqVO.getTodoListStatus())
                .eqIfPresent(ZhhTodoListLogDO::getTodoOperator, reqVO.getTodoOperator())
                .eqIfPresent(ZhhTodoListLogDO::getTodoResult, reqVO.getTodoResult())
                .eqIfPresent(ZhhTodoListLogDO::getTodoMind, reqVO.getTodoMind())
                .betweenIfPresent(ZhhTodoListLogDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(ZhhTodoListLogDO::getId));
    }

}
