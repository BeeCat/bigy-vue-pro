package cn.iocoder.yudao.module.system.controller.admin.student;

import cn.iocoder.yudao.module.system.controller.admin.student.vo.*;
import cn.iocoder.yudao.module.system.convert.student.StudentConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.student.StudentDO;
import cn.iocoder.yudao.module.system.service.student.StudentService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.*;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.*;

@Api(tags = "管理后台 - 学员管理")
@RestController
@RequestMapping("/zhh/student")
@Validated
public class StudentController {

    @Resource
    private StudentService studentService;

    @PostMapping("/create")
    @ApiOperation("创建学员管理")
    @PreAuthorize("@ss.hasPermission('zhh:student:create')")    public CommonResult<Integer> createStudent(@Valid @RequestBody StudentCreateReqVO createReqVO) {
        return success(studentService.createStudent(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新学员管理")
    @PreAuthorize("@ss.hasPermission('zhh:student:update')")    public CommonResult<Boolean> updateStudent(@Valid @RequestBody StudentUpdateReqVO updateReqVO) {
        studentService.updateStudent(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除学员管理")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermission('zhh:student:delete')")    public CommonResult<Boolean> deleteStudent(@RequestParam("id") Integer id) {
        studentService.deleteStudent(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得学员管理")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermission('zhh:student:query')")
    public CommonResult<StudentRespVO> getStudent(@RequestParam("id") Integer id) {
        StudentDO student = studentService.getStudent(id);
        return success(StudentConvert.INSTANCE.convert(student));
    }

    @GetMapping("/list")
    @ApiOperation("获得学员管理列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('zhh:student:query')")    public CommonResult<List<StudentRespVO>> getStudentList(@RequestParam("ids") Collection<Integer> ids) {
        List<StudentDO> list = studentService.getStudentList(ids);
        return success(StudentConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得学员管理分页")
    @PreAuthorize("@ss.hasPermission('zhh:student:query')")    public CommonResult<PageResult<StudentRespVO>> getStudentPage(@Valid StudentPageReqVO pageVO) {
        PageResult<StudentDO> pageResult = studentService.getStudentPage(pageVO);
        return success(StudentConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出学员管理 Excel")
    @PreAuthorize("@ss.hasPermission('zhh:student:export')")    @OperateLog(type = EXPORT)
    public void exportStudentExcel(@Valid StudentExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<StudentDO> list = studentService.getStudentList(exportReqVO);
        // 导出 Excel
        List<StudentExcelVO> datas = StudentConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "学员管理.xls", "数据", StudentExcelVO.class, datas);
    }

}
