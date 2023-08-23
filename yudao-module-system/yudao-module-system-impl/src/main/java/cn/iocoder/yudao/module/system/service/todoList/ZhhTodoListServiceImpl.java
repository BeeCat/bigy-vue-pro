package cn.iocoder.yudao.module.system.service.todoList;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.todoList.vo.*;
import cn.iocoder.yudao.module.system.controller.admin.todoListLog.vo.ZhhTodoListLogCreateReqVO;
import cn.iocoder.yudao.module.system.convert.todoList.ZhhTodoListConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.todoList.ZhhTodoListDO;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.dal.mysql.todoList.ZhhTodoListMapper;
import cn.iocoder.yudao.module.system.service.todoListLog.ZhhTodoListLogService;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.TODOLIST_NOT_EXISTS;

/**
 * todolist 待办项 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ZhhTodoListServiceImpl implements ZhhTodoListService {

    @Resource
    private ZhhTodoListMapper todoListMapper;

    @Resource
    private ZhhTodoListLogService todoListLogService;

    /**
     *
     */
    @Resource
    private AdminUserService adminUserService;

    @Override
    public Integer createTodoList(ZhhTodoListCreateReqVO createReqVO) {
        // 插入
        ZhhTodoListDO todoList = ZhhTodoListConvert.INSTANCE.convert(createReqVO);
        todoList.setSuccessFlag(1);
        todoListMapper.insert(todoList);

        ZhhTodoListLogCreateReqVO log = new ZhhTodoListLogCreateReqVO();
        log.setTodoListId(todoList.getId());
        log.setTodoOperator(createReqVO.getAssignee());
        log.setTodoResult("");
        todoListLogService.createTodoListLog(log);
        // 返回
        return todoList.getId();
    }

    @Override
    public void updateTodoList(ZhhTodoListUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateTodoListExists(updateReqVO.getId());
        // 更新
        ZhhTodoListDO updateObj = ZhhTodoListConvert.INSTANCE.convert(updateReqVO);
        todoListMapper.updateById(updateObj);
    }

    @Override
    public void deleteTodoList(Integer id) {
        // 校验存在
        this.validateTodoListExists(id);
        // 删除
        todoListMapper.deleteById(id);
    }

    private void validateTodoListExists(Integer id) {
        if (todoListMapper.selectById(id) == null) {
            throw exception(TODOLIST_NOT_EXISTS);
        }
    }

    @Override
    public ZhhTodoListDO getTodoList(Integer id) {
        return todoListMapper.selectById(id);
    }

    @Override
    public List<ZhhTodoListDO> getTodoListList(Collection<Integer> ids) {
        return todoListMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ZhhTodoListRespVO> getTodoListPage(ZhhTodoListPageReqVO pageReqVO) {
        PageResult<ZhhTodoListDO> pageResult = todoListMapper.selectPage(pageReqVO);
        PageResult<ZhhTodoListRespVO> respVOPageResult = ZhhTodoListConvert.INSTANCE.convertPage(pageResult);
        respVOPageResult.getList().forEach(cc -> {

            String assignee = cc.getAssignee();
            if (assignee != null) {
                AdminUserDO user = adminUserService.getUser(Long.parseLong(assignee));
                cc.setAssigneeName(user.getNickname());
            }
            String receiver = cc.getReceiver();
            if (receiver != null) {
                AdminUserDO user = adminUserService.getUser(Long.parseLong(receiver));
                cc.setReceiverName(user.getNickname());
            }
            String reviewer = cc.getReviewer();
            if (reviewer != null) {
                AdminUserDO user = adminUserService.getUser(Long.parseLong(reviewer));
                cc.setReviewerName(user.getNickname());
            }
        });
        return respVOPageResult;
    }

    @Override
    public List<ZhhTodoListDO> getTodoListList(ZhhTodoListExportReqVO exportReqVO) {
        return todoListMapper.selectList(exportReqVO);
    }

}
