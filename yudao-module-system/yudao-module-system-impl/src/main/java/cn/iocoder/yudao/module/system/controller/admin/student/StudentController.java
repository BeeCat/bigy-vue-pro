package cn.iocoder.yudao.module.system.controller.admin.student;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.system.controller.admin.student.vo.*;
import cn.iocoder.yudao.module.system.convert.student.StudentConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.student.StudentDO;
import cn.iocoder.yudao.module.system.service.student.StudentService;
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

@Api(tags = "管理后台 - 学员管理")
@RestController
@RequestMapping("/student/")
@Validated
public class StudentController {

    @Resource
    private StudentService Service;

    @PostMapping("/create")
    @ApiOperation("创建学员管理")
    @PreAuthorize("@ss.hasPermission('student::create')")    public CommonResult<Integer> create(@Valid @RequestBody StudentCreateReqVO createReqVO) {
        return success(Service.create(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新学员管理")
    @PreAuthorize("@ss.hasPermission('student::update')")    public CommonResult<Boolean> update(@Valid @RequestBody StudentUpdateReqVO updateReqVO) {
        Service.update(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除学员管理")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermission('student::delete')")    public CommonResult<Boolean> delete(@RequestParam("id") Integer id) {
        Service.delete(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得学员管理")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermission('student::query')")
    public CommonResult<StudentRespVO> get(@RequestParam("id") Integer id) {
        StudentDO student = Service.get(id);
        return success(StudentConvert.INSTANCE.convert(student));
    }

    @GetMapping("/list")
    @ApiOperation("获得学员管理列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('student::query')")    public CommonResult<List<StudentRespVO>> getList(@RequestParam("ids") Collection<Integer> ids) {
        List<StudentDO> list = Service.getList(ids);
        return success(StudentConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得学员管理分页")
    @PreAuthorize("@ss.hasPermission('student::query')")    public CommonResult<PageResult<StudentRespVO>> getPage(@Valid StudentPageReqVO pageVO) {
        PageResult<StudentDO> pageResult = Service.getPage(pageVO);
        return success(StudentConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出学员管理 Excel")
    @PreAuthorize("@ss.hasPermission('student::export')")    @OperateLog(type = EXPORT)
    public void exportExcel(@Valid StudentExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<StudentDO> list = Service.getList(exportReqVO);
        // 导出 Excel
        List<StudentExcelVO> datas = StudentConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "学员管理.xls", "数据", StudentExcelVO.class, datas);
    }

}
