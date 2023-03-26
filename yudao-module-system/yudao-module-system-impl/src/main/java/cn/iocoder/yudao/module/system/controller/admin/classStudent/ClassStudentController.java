package cn.iocoder.yudao.module.system.controller.admin.classStudent;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.system.controller.admin.classStudent.vo.*;
import cn.iocoder.yudao.module.system.controller.admin.courseClass.vo.CourseClassRespVO;
import cn.iocoder.yudao.module.system.convert.classStudent.ClassStudentConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.classStudent.ClassStudentDO;
import cn.iocoder.yudao.module.system.service.classStudent.ClassStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.error;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Slf4j
@Api(tags = "管理后台 - 班级学生")
@RestController
@RequestMapping("/zhh/class-student")
@Validated
public class ClassStudentController {

    @Resource
    private ClassStudentService classStudentService;

    @PostMapping("/create")
    @ApiOperation("创建班级学生")
    @PreAuthorize("@ss.hasPermission('zhh:class-student:create')")    public CommonResult<Integer> createClassStudent(@Valid @RequestBody ClassStudentCreateReqVO createReqVO) {
        try {
            Integer classStudent = classStudentService.createClassStudent(createReqVO);
            return success(classStudent);
        } catch (Exception e) {
            log.error("创建消课记录-error", e);
            return error(400, e.getMessage());
        }
    }

    @PutMapping("/update")
    @ApiOperation("更新班级学生")
    @PreAuthorize("@ss.hasPermission('zhh:class-student:update')")    public CommonResult<Boolean> updateClassStudent(@Valid @RequestBody ClassStudentUpdateReqVO updateReqVO) {
        classStudentService.updateClassStudent(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除班级学生")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermission('zhh:class-student:delete')")    public CommonResult<Boolean> deleteClassStudent(@RequestParam("id") Integer id) {
        classStudentService.deleteClassStudent(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得班级学生")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermission('zhh:class-student:query')")
    public CommonResult<ClassStudentRespVO> getClassStudent(@RequestParam("id") Integer id) {
        ClassStudentDO classStudent = classStudentService.getClassStudent(id);
        return success(ClassStudentConvert.INSTANCE.convert(classStudent));
    }

    @GetMapping("/list")
    @ApiOperation("获得班级学生列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('zhh:class-student:query')")    public CommonResult<List<ClassStudentRespVO>> getClassStudentList(@RequestParam("ids") Collection<Integer> ids) {
        List<ClassStudentDO> list = classStudentService.getClassStudentList(ids);
        return success(ClassStudentConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得班级学生分页")
    @PreAuthorize("@ss.hasPermission('zhh:class-student:query')")    public CommonResult<PageResult<ClassStudentRespVO>> getClassStudentPage(@Valid ClassStudentPageReqVO pageVO) {
        PageResult<ClassStudentRespVO> pageResult = classStudentService.getClassStudentPage(pageVO);
        return success(pageResult);
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出班级学生 Excel")
    @PreAuthorize("@ss.hasPermission('zhh:class-student:export')")    @OperateLog(type = EXPORT)
    public void exportClassStudentExcel(@Valid ClassStudentExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ClassStudentDO> list = classStudentService.getClassStudentList(exportReqVO);
        // 导出 Excel
        List<ClassStudentExcelVO> datas = ClassStudentConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "班级学生.xls", "数据", ClassStudentExcelVO.class, datas);
    }

}
