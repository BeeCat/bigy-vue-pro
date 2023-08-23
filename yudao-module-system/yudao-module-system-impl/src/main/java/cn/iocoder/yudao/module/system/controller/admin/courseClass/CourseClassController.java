package cn.iocoder.yudao.module.system.controller.admin.courseClass;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.system.controller.admin.courseClass.vo.*;
import cn.iocoder.yudao.module.system.convert.courseClass.CourseClassConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.courseClass.CourseClassDO;
import cn.iocoder.yudao.module.system.service.courseClass.CourseClassService;
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

@Api(tags = "管理后台 - 课程班级")
@RestController
@RequestMapping("/education/course-class")
@Validated
public class CourseClassController {

    @Resource
    private CourseClassService courseClassService;

    @PostMapping("/create")
    @ApiOperation("创建课程班级")
    @PreAuthorize("@ss.hasPermission('education:course-class:create')")    public CommonResult<Integer> createCourseClass(@Valid @RequestBody CourseClassCreateReqVO createReqVO) {
        return success(courseClassService.createCourseClass(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新课程班级")
    @PreAuthorize("@ss.hasPermission('education:course-class:update')")    public CommonResult<Boolean> updateCourseClass(@Valid @RequestBody CourseClassUpdateReqVO updateReqVO) {
        courseClassService.updateCourseClass(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除课程班级")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermission('education:course-class:delete')")    public CommonResult<Boolean> deleteCourseClass(@RequestParam("id") Integer id) {
        courseClassService.deleteCourseClass(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得课程班级")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermission('education:course-class:query')")
    public CommonResult<CourseClassRespVO> getCourseClass(@RequestParam("id") Integer id) {
        CourseClassDO courseClass = courseClassService.getCourseClass(id);
        return success(CourseClassConvert.INSTANCE.convert(courseClass));
    }

    @GetMapping("/list")
    @ApiOperation("获得课程班级列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('education:course-class:query')")    public CommonResult<List<CourseClassRespVO>> getCourseClassList(@RequestParam("ids") Collection<Integer> ids) {
        List<CourseClassDO> list = courseClassService.getCourseClassList(ids);
        return success(CourseClassConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得课程班级分页")
    @PreAuthorize("@ss.hasPermission('education:course-class:query')")
    public CommonResult<PageResult<CourseClassRespVO>> getCourseClassPage(@Valid CourseClassPageReqVO pageVO) {
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        Set<Long> loginUserRoleIds = SecurityFrameworkUtils.getLoginUserRoleIds();
        boolean adminFlag = loginUserRoleIds.contains(1L) || loginUserRoleIds.contains(108L);
        if (!adminFlag) {
            pageVO.setTeacherCode(loginUserId.toString());
        }
        PageResult<CourseClassRespVO> pageResult = courseClassService.getCourseClassPage(pageVO);
        return success(pageResult);
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出课程班级 Excel")
    @PreAuthorize("@ss.hasPermission('education:course-class:export')")    @OperateLog(type = EXPORT)
    public void exportCourseClassExcel(@Valid CourseClassExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<CourseClassDO> list = courseClassService.getCourseClassList(exportReqVO);
        // 导出 Excel
        List<CourseClassExcelVO> datas = CourseClassConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "课程班级.xls", "数据", CourseClassExcelVO.class, datas);
    }

}
