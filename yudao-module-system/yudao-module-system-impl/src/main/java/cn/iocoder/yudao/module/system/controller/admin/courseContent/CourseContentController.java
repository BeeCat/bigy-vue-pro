package cn.iocoder.yudao.module.system.controller.admin.courseContent;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.system.controller.admin.courseContent.vo.*;
import cn.iocoder.yudao.module.system.convert.courseContent.CourseContentConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.courseContent.CourseContentDO;
import cn.iocoder.yudao.module.system.service.courseContent.CourseContentService;
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

@Api(tags = "管理后台 - 课程体系")
@RestController
@RequestMapping("/zhh/course-content")
@Validated
public class CourseContentController {

    @Resource
    private CourseContentService courseContentService;

    @PostMapping("/create")
    @ApiOperation("创建课程体系")
    @PreAuthorize("@ss.hasPermission('zhh:course-content:create')")    public CommonResult<Integer> createCourseContent(@Valid @RequestBody CourseContentCreateReqVO createReqVO) {
        return success(courseContentService.createCourseContent(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新课程体系")
    @PreAuthorize("@ss.hasPermission('zhh:course-content:update')")    public CommonResult<Boolean> updateCourseContent(@Valid @RequestBody CourseContentUpdateReqVO updateReqVO) {
        courseContentService.updateCourseContent(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除课程体系")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermission('zhh:course-content:delete')")    public CommonResult<Boolean> deleteCourseContent(@RequestParam("id") Integer id) {
        courseContentService.deleteCourseContent(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得课程体系")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermission('zhh:course-content:query')")
    public CommonResult<CourseContentRespVO> getCourseContent(@RequestParam("id") Integer id) {
        CourseContentDO courseContent = courseContentService.getCourseContent(id);
        return success(CourseContentConvert.INSTANCE.convert(courseContent));
    }

    @GetMapping("/list")
    @ApiOperation("获得课程体系列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('zhh:course-content:query')")    public CommonResult<List<CourseContentRespVO>> getCourseContentList(@RequestParam("ids") Collection<Integer> ids) {
        List<CourseContentDO> list = courseContentService.getCourseContentList(ids);
        return success(CourseContentConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得课程体系分页")
    @PreAuthorize("@ss.hasPermission('zhh:course-content:query')")    public CommonResult<PageResult<CourseContentRespVO>> getCourseContentPage(@Valid CourseContentPageReqVO pageVO) {
        PageResult<CourseContentRespVO> pageResult = courseContentService.getCourseContentPage(pageVO);
        return success(pageResult);
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出课程体系 Excel")
    @PreAuthorize("@ss.hasPermission('zhh:course-content:export')")    @OperateLog(type = EXPORT)
    public void exportCourseContentExcel(@Valid CourseContentExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<CourseContentDO> list = courseContentService.getCourseContentList(exportReqVO);
        // 导出 Excel
        List<CourseContentExcelVO> datas = CourseContentConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "课程体系.xls", "数据", CourseContentExcelVO.class, datas);
    }

}
