package cn.iocoder.yudao.module.system.controller.admin.todoListLog;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.system.controller.admin.todoListLog.vo.*;
import cn.iocoder.yudao.module.system.convert.todoListLog.ZhhTodoListLogConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.todoListLog.ZhhTodoListLogDO;
import cn.iocoder.yudao.module.system.service.todoListLog.ZhhTodoListLogService;
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

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;


@Api(tags = "管理后台 - todolist 记录")
@RestController
@RequestMapping("/zhh/todo-list-log")
@Validated
public class ZhhTodoListLogController {

    @Resource
    private ZhhTodoListLogService todoListLogService;

    @PostMapping("/create")
    @ApiOperation("创建todolist 记录")
    @PreAuthorize("@ss.hasPermission('zhh:todo-list-log:create')")    public CommonResult<Integer> createTodoListLog(@Valid @RequestBody ZhhTodoListLogCreateReqVO createReqVO) {
        return success(todoListLogService.createTodoListLog(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新todolist 记录")
    @PreAuthorize("@ss.hasPermission('zhh:todo-list-log:update')")    public CommonResult<Boolean> updateTodoListLog(@Valid @RequestBody ZhhTodoListLogUpdateReqVO updateReqVO) {
        todoListLogService.updateTodoListLog(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除todolist 记录")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermission('zhh:todo-list-log:delete')")    public CommonResult<Boolean> deleteTodoListLog(@RequestParam("id") Integer id) {
        todoListLogService.deleteTodoListLog(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得todolist 记录")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermission('zhh:todo-list-log:query')")
    public CommonResult<ZhhTodoListLogRespVO> getTodoListLog(@RequestParam("id") Integer id) {
        ZhhTodoListLogDO todoListLog = todoListLogService.getTodoListLog(id);
        return success(ZhhTodoListLogConvert.INSTANCE.convert(todoListLog));
    }

    @GetMapping("/list")
    @ApiOperation("获得todolist 记录列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('zhh:todo-list-log:query')")    public CommonResult<List<ZhhTodoListLogRespVO>> getTodoListLogList(@RequestParam("ids") Collection<Integer> ids) {
        List<ZhhTodoListLogDO> list = todoListLogService.getTodoListLogList(ids);
        return success(ZhhTodoListLogConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得todolist 记录分页")
    @PreAuthorize("@ss.hasPermission('zhh:todo-list-log:query')")    public CommonResult<PageResult<ZhhTodoListLogRespVO>> getTodoListLogPage(@Valid ZhhTodoListLogPageReqVO pageVO) {
        PageResult<ZhhTodoListLogDO> pageResult = todoListLogService.getTodoListLogPage(pageVO);
        return success(ZhhTodoListLogConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出todolist 记录 Excel")
    @PreAuthorize("@ss.hasPermission('zhh:todo-list-log:export')")    @OperateLog(type = EXPORT)
    public void exportTodoListLogExcel(@Valid ZhhTodoListLogExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ZhhTodoListLogDO> list = todoListLogService.getTodoListLogList(exportReqVO);
        // 导出 Excel
        List<ZhhTodoListLogExcelVO> datas = ZhhTodoListLogConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "todolist 记录.xls", "数据", ZhhTodoListLogExcelVO.class, datas);
    }

}
