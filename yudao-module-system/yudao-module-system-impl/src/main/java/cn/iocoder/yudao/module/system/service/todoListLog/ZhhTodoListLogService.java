package cn.iocoder.yudao.module.system.service.todoListLog;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.todoListLog.vo.ZhhTodoListLogCreateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.todoListLog.vo.ZhhTodoListLogExportReqVO;
import cn.iocoder.yudao.module.system.controller.admin.todoListLog.vo.ZhhTodoListLogPageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.todoListLog.vo.ZhhTodoListLogUpdateReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.todoListLog.ZhhTodoListLogDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * todolist 记录 Service 接口
 *
 * @author 芋道源码
 */
public interface ZhhTodoListLogService {

    /**
     * 创建todolist 记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createTodoListLog(@Valid ZhhTodoListLogCreateReqVO createReqVO);

    /**
     * 更新todolist 记录
     *
     * @param updateReqVO 更新信息
     */
    void updateTodoListLog(@Valid ZhhTodoListLogUpdateReqVO updateReqVO);

    /**
     * 删除todolist 记录
     *
     * @param id 编号
     */
    void deleteTodoListLog(Integer id);

    /**
     * 获得todolist 记录
     *
     * @param id 编号
     * @return todolist 记录
     */
    ZhhTodoListLogDO getTodoListLog(Integer id);

    /**
     * 获得todolist 记录列表
     *
     * @param ids 编号
     * @return todolist 记录列表
     */
    List<ZhhTodoListLogDO> getTodoListLogList(Collection<Integer> ids);

    /**
     * 获得todolist 记录分页
     *
     * @param pageReqVO 分页查询
     * @return todolist 记录分页
     */
    PageResult<ZhhTodoListLogDO> getTodoListLogPage(ZhhTodoListLogPageReqVO pageReqVO);

    /**
     * 获得todolist 记录列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return todolist 记录列表
     */
    List<ZhhTodoListLogDO> getTodoListLogList(ZhhTodoListLogExportReqVO exportReqVO);

}
