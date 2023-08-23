package cn.iocoder.yudao.module.system.controller.admin.todoList;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.system.controller.admin.todoList.vo.*;
import cn.iocoder.yudao.module.system.convert.todoList.ZhhTodoListConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.todoList.ZhhTodoListDO;
import cn.iocoder.yudao.module.system.service.todoList.ZhhTodoListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Api(tags = "管理后台 - todolist 待办项")
@RestController
@RequestMapping("/zhh/todo-list")
@Validated
public class ZhhTodoListController {

    @Resource
    private ZhhTodoListService todoListService;

    @PostMapping("/create")
    @ApiOperation("创建todolist 待办项")
    @PreAuthorize("@ss.hasPermission('zhh:todo-list:create')")    public CommonResult<Integer> createTodoList(@Valid @RequestBody ZhhTodoListCreateReqVO createReqVO) {
        return success(todoListService.createTodoList(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新todolist 待办项")
    @PreAuthorize("@ss.hasPermission('zhh:todo-list:update')")    public CommonResult<Boolean> updateTodoList(@Valid @RequestBody ZhhTodoListUpdateReqVO updateReqVO) {
        todoListService.updateTodoList(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除todolist 待办项")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermission('zhh:todo-list:delete')")    public CommonResult<Boolean> deleteTodoList(@RequestParam("id") Integer id) {
        todoListService.deleteTodoList(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得todolist 待办项")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermission('zhh:todo-list:query')")
    public CommonResult<ZhhTodoListRespVO> getTodoList(@RequestParam("id") Integer id) {
        ZhhTodoListDO todoList = todoListService.getTodoList(id);
        return success(ZhhTodoListConvert.INSTANCE.convert(todoList));
    }

    @GetMapping("/list")
    @ApiOperation("获得todolist 待办项列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('zhh:todo-list:query')")    public CommonResult<List<ZhhTodoListRespVO>> getTodoListList(@RequestParam("ids") Collection<Integer> ids) {
        List<ZhhTodoListDO> list = todoListService.getTodoListList(ids);
        return success(ZhhTodoListConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得todolist 待办项分页")
    @PreAuthorize("@ss.hasPermission('zhh:todo-list:query')")    public CommonResult<PageResult<ZhhTodoListRespVO>> getTodoListPage(@Valid ZhhTodoListPageReqVO pageVO) {
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        Set<Long> loginUserRoleIds = SecurityFrameworkUtils.getLoginUserRoleIds();
        boolean adminFlag = loginUserRoleIds.contains(1L) || loginUserRoleIds.contains(108L);
        if (!adminFlag) {
            pageVO.setReceiver(loginUserId.toString());
        }
        PageResult<ZhhTodoListRespVO> pageResult = todoListService.getTodoListPage(pageVO);
        return success(pageResult);
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出todolist 待办项 Excel")
    @PreAuthorize("@ss.hasPermission('zhh:todo-list:export')")    @OperateLog(type = EXPORT)
    public void exportTodoListExcel(@Valid ZhhTodoListExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ZhhTodoListDO> list = todoListService.getTodoListList(exportReqVO);
        // 导出 Excel
        List<ZhhTodoListExcelVO> datas = ZhhTodoListConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "todolist 待办项.xls", "数据", ZhhTodoListExcelVO.class, datas);
    }

}
