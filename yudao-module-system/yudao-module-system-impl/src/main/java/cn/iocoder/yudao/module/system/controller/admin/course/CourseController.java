package cn.iocoder.yudao.module.system.controller.admin.course;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.system.controller.admin.course.vo.*;
import cn.iocoder.yudao.module.system.convert.course.CourseConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.course.CourseDO;
import cn.iocoder.yudao.module.system.service.course.CourseService;
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

@Api(tags = "管理后台 - 课程")
@RestController
@RequestMapping("/education/course")
@Validated
public class CourseController {

    @Resource
    private CourseService courseService;

    @PostMapping("/create")
    @ApiOperation("创建课程")
    @PreAuthorize("@ss.hasPermission('education:course:create')")    public CommonResult<Integer> createCourse(@Valid @RequestBody CourseCreateReqVO createReqVO) {
        return success(courseService.createCourse(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新课程")
    @PreAuthorize("@ss.hasPermission('education:course:update')")    public CommonResult<Boolean> updateCourse(@Valid @RequestBody CourseUpdateReqVO updateReqVO) {
        courseService.updateCourse(updateReqVO);
        return success(true);
    }

    @PutMapping("/assign")
    @ApiOperation("更新课程")
    @PreAuthorize("@ss.hasPermission('education:course:update')")    public CommonResult<Boolean> assignCourse(@Valid @RequestBody CourseAssignReqVO updateReqVO) {
        courseService.assignCourse(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除课程")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermission('education:course:delete')")    public CommonResult<Boolean> deleteCourse(@RequestParam("id") Integer id) {
        courseService.deleteCourse(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得课程")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermission('education:course:query')")
    public CommonResult<CourseRespVO> getCourse(@RequestParam("id") Integer id) {
        CourseDO course = courseService.getCourse(id);
        return success(CourseConvert.INSTANCE.convert(course));
    }

    @GetMapping("/list")
    @ApiOperation("获得课程列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('education:course:query')")    public CommonResult<List<CourseRespVO>> getCourseList(@RequestParam("ids") Collection<Integer> ids) {
        List<CourseDO> list = courseService.getCourseList(ids);
        return success(CourseConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得课程分页")
    @PreAuthorize("@ss.hasPermission('education:course:query')")    public CommonResult<PageResult<CourseRespVO>> getCoursePage(@Valid CoursePageReqVO pageVO) {
        PageResult<CourseDO> pageResult = courseService.getCoursePage(pageVO);
        return success(CourseConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出课程 Excel")
    @PreAuthorize("@ss.hasPermission('education:course:export')")    @OperateLog(type = EXPORT)
    public void exportCourseExcel(@Valid CourseExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<CourseDO> list = courseService.getCourseList(exportReqVO);
        // 导出 Excel
        List<CourseExcelVO> datas = CourseConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "课程.xls", "数据", CourseExcelVO.class, datas);
    }

}
