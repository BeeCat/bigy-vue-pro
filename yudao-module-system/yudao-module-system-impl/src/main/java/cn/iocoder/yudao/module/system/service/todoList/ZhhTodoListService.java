package cn.iocoder.yudao.module.system.service.todoList;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.todoList.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.todoList.ZhhTodoListDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * todolist 待办项 Service 接口
 *
 * @author 芋道源码
 */
public interface ZhhTodoListService {

    /**
     * 创建todolist 待办项
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createTodoList(@Valid ZhhTodoListCreateReqVO createReqVO);

    /**
     * 更新todolist 待办项
     *
     * @param updateReqVO 更新信息
     */
    void updateTodoList(@Valid ZhhTodoListUpdateReqVO updateReqVO);

    /**
     * 删除todolist 待办项
     *
     * @param id 编号
     */
    void deleteTodoList(Integer id);

    /**
     * 获得todolist 待办项
     *
     * @param id 编号
     * @return todolist 待办项
     */
    ZhhTodoListDO getTodoList(Integer id);

    /**
     * 获得todolist 待办项列表
     *
     * @param ids 编号
     * @return todolist 待办项列表
     */
    List<ZhhTodoListDO> getTodoListList(Collection<Integer> ids);

    /**
     * 获得todolist 待办项分页
     *
     * @param pageReqVO 分页查询
     * @return todolist 待办项分页
     */
    PageResult<ZhhTodoListRespVO> getTodoListPage(ZhhTodoListPageReqVO pageReqVO);

    /**
     * 获得todolist 待办项列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return todolist 待办项列表
     */
    List<ZhhTodoListDO> getTodoListList(ZhhTodoListExportReqVO exportReqVO);

}
