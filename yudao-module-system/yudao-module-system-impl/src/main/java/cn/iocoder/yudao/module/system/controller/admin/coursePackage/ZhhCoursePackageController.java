package cn.iocoder.yudao.module.system.controller.admin.coursePackage;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.system.controller.admin.coursePackage.vo.*;
import cn.iocoder.yudao.module.system.convert.coursePackage.ZhhCoursePackageConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.coursePackage.ZhhCoursePackageDO;
import cn.iocoder.yudao.module.system.service.coursePackage.ZhhCoursePackageService;
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


@Api(tags = "管理后台 - 课程包套餐")
@RestController
@RequestMapping("/zhh/course-package")
@Validated
public class ZhhCoursePackageController {

    @Resource
    private ZhhCoursePackageService coursePackageService;

    @PostMapping("/create")
    @ApiOperation("创建课程包套餐")
    @PreAuthorize("@ss.hasPermission('zhh:course-package:create')")    public CommonResult<Integer> createCoursePackage(@Valid @RequestBody ZhhCoursePackageCreateReqVO createReqVO) {
        return success(coursePackageService.createCoursePackage(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新课程包套餐")
    @PreAuthorize("@ss.hasPermission('zhh:course-package:update')")    public CommonResult<Boolean> updateCoursePackage(@Valid @RequestBody ZhhCoursePackageUpdateReqVO updateReqVO) {
        coursePackageService.updateCoursePackage(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除课程包套餐")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermission('zhh:course-package:delete')")    public CommonResult<Boolean> deleteCoursePackage(@RequestParam("id") Integer id) {
        coursePackageService.deleteCoursePackage(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得课程包套餐")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermission('zhh:course-package:query')")
    public CommonResult<ZhhCoursePackageRespVO> getCoursePackage(@RequestParam("id") Integer id) {
        ZhhCoursePackageDO coursePackage = coursePackageService.getCoursePackage(id);
        return success(ZhhCoursePackageConvert.INSTANCE.convert(coursePackage));
    }

    @GetMapping("/list")
    @ApiOperation("获得课程包套餐列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('zhh:course-package:query')")    public CommonResult<List<ZhhCoursePackageRespVO>> getCoursePackageList(@RequestParam("ids") Collection<Integer> ids) {
        List<ZhhCoursePackageDO> list = coursePackageService.getCoursePackageList(ids);
        return success(ZhhCoursePackageConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得课程包套餐分页")
    @PreAuthorize("@ss.hasPermission('zhh:course-package:query')")    public CommonResult<PageResult<ZhhCoursePackageRespVO>> getCoursePackagePage(@Valid ZhhCoursePackagePageReqVO pageVO) {
        PageResult<ZhhCoursePackageDO> pageResult = coursePackageService.getCoursePackagePage(pageVO);
        return success(ZhhCoursePackageConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出课程包套餐 Excel")
    @PreAuthorize("@ss.hasPermission('zhh:course-package:export')")    @OperateLog(type = EXPORT)
    public void exportCoursePackageExcel(@Valid ZhhCoursePackageExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ZhhCoursePackageDO> list = coursePackageService.getCoursePackageList(exportReqVO);
        // 导出 Excel
        List<ZhhCoursePackageExcelVO> datas = ZhhCoursePackageConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "课程包套餐.xls", "数据", ZhhCoursePackageExcelVO.class, datas);
    }

}
