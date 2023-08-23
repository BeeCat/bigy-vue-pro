package cn.iocoder.yudao.module.system.controller.admin.activity;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.system.controller.admin.activity.vo.*;
import cn.iocoder.yudao.module.system.convert.activity.ZhhActivityConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.activity.ZhhActivityDO;
import cn.iocoder.yudao.module.system.service.activity.ZhhActivityService;
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


@Api(tags = "管理后台 - ")
@RestController
@RequestMapping("/zhh/activity")
@Validated
public class ZhhActivityController {

    @Resource
    private ZhhActivityService activityService;

    @PostMapping("/create")
    @ApiOperation("创建")
    @PreAuthorize("@ss.hasPermission('zhh:activity:create')")    public CommonResult<Integer> createActivity(@Valid @RequestBody ZhhActivityCreateReqVO createReqVO) {
        return success(activityService.createActivity(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新")
    @PreAuthorize("@ss.hasPermission('zhh:activity:update')")    public CommonResult<Boolean> updateActivity(@Valid @RequestBody ZhhActivityUpdateReqVO updateReqVO) {
        activityService.updateActivity(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermission('zhh:activity:delete')")    public CommonResult<Boolean> deleteActivity(@RequestParam("id") Integer id) {
        activityService.deleteActivity(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermission('zhh:activity:query')")
    public CommonResult<ZhhActivityRespVO> getActivity(@RequestParam("id") Integer id) {
        ZhhActivityDO activity = activityService.getActivity(id);
        return success(ZhhActivityConvert.INSTANCE.convert(activity));
    }

    @GetMapping("/list")
    @ApiOperation("获得列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('zhh:activity:query')")    public CommonResult<List<ZhhActivityRespVO>> getActivityList(@RequestParam("ids") Collection<Integer> ids) {
        List<ZhhActivityDO> list = activityService.getActivityList(ids);
        return success(ZhhActivityConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得分页")
    @PreAuthorize("@ss.hasPermission('zhh:activity:query')")    public CommonResult<PageResult<ZhhActivityRespVO>> getActivityPage(@Valid ZhhActivityPageReqVO pageVO) {
        PageResult<ZhhActivityDO> pageResult = activityService.getActivityPage(pageVO);
        return success(ZhhActivityConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出 Excel")
    @PreAuthorize("@ss.hasPermission('zhh:activity:export')")    @OperateLog(type = EXPORT)
    public void exportActivityExcel(@Valid ZhhActivityExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ZhhActivityDO> list = activityService.getActivityList(exportReqVO);
        // 导出 Excel
        List<ZhhActivityExcelVO> datas = ZhhActivityConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, ".xls", "数据", ZhhActivityExcelVO.class, datas);
    }

}
