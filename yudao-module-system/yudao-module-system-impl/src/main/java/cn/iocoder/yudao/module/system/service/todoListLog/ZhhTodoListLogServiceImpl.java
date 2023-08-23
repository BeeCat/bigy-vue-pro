package cn.iocoder.yudao.module.system.service.todoListLog;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.todoListLog.vo.ZhhTodoListLogCreateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.todoListLog.vo.ZhhTodoListLogExportReqVO;
import cn.iocoder.yudao.module.system.controller.admin.todoListLog.vo.ZhhTodoListLogPageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.todoListLog.vo.ZhhTodoListLogUpdateReqVO;
import cn.iocoder.yudao.module.system.convert.todoListLog.ZhhTodoListLogConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.todoListLog.ZhhTodoListLogDO;
import cn.iocoder.yudao.module.system.dal.mysql.todoListLog.ZhhTodoListLogMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.TODO_LIST_LOG_NOT_EXISTS;

/**
 * todolist 记录 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ZhhTodoListLogServiceImpl implements ZhhTodoListLogService {

    @Resource
    private ZhhTodoListLogMapper todoListLogMapper;

    @Override
    public Integer createTodoListLog(ZhhTodoListLogCreateReqVO createReqVO) {
        // 插入
        ZhhTodoListLogDO todoListLog = ZhhTodoListLogConvert.INSTANCE.convert(createReqVO);
        todoListLogMapper.insert(todoListLog);
        // 返回
        return todoListLog.getId();
    }

    @Override
    public void updateTodoListLog(ZhhTodoListLogUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateTodoListLogExists(updateReqVO.getId());
        // 更新
        ZhhTodoListLogDO updateObj = ZhhTodoListLogConvert.INSTANCE.convert(updateReqVO);
        todoListLogMapper.updateById(updateObj);
    }

    @Override
    public void deleteTodoListLog(Integer id) {
        // 校验存在
        this.validateTodoListLogExists(id);
        // 删除
        todoListLogMapper.deleteById(id);
    }

    private void validateTodoListLogExists(Integer id) {
        if (todoListLogMapper.selectById(id) == null) {
            throw exception(TODO_LIST_LOG_NOT_EXISTS);
        }
    }

    @Override
    public ZhhTodoListLogDO getTodoListLog(Integer id) {
        return todoListLogMapper.selectById(id);
    }

    @Override
    public List<ZhhTodoListLogDO> getTodoListLogList(Collection<Integer> ids) {
        return todoListLogMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ZhhTodoListLogDO> getTodoListLogPage(ZhhTodoListLogPageReqVO pageReqVO) {
        return todoListLogMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ZhhTodoListLogDO> getTodoListLogList(ZhhTodoListLogExportReqVO exportReqVO) {
        return todoListLogMapper.selectList(exportReqVO);
    }

}
