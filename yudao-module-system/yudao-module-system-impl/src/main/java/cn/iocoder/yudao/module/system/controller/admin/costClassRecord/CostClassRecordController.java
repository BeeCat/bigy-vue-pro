package cn.iocoder.yudao.module.system.controller.admin.costClassRecord;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.system.controller.admin.costClassRecord.vo.*;
import cn.iocoder.yudao.module.system.convert.costClassRecord.CostClassRecordConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.costClassRecord.CostClassRecordDO;
import cn.iocoder.yudao.module.system.service.costClassRecord.CostClassRecordService;
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

@Api(tags = "管理后台 - 消课记录")
@RestController
@RequestMapping("/zhh/cost-class-record")
@Validated
@Slf4j
public class CostClassRecordController {

    @Resource
    private CostClassRecordService costClassRecordService;

    @PostMapping("/create")
    @ApiOperation("创建消课记录")
    @PreAuthorize("@ss.hasPermission('zhh:cost-class-record:create')")    public CommonResult<Integer> createCostClassRecord(@Valid @RequestBody CostClassRecordCreateReqVO createReqVO) {
        try {
            Integer costClassRecord = costClassRecordService.createCostClassRecord(createReqVO);
            return success(costClassRecord);
        } catch (Exception e) {
            log.error("创建消课记录-error", e);
            return error(400, e.getMessage());
        }
    }

    @PutMapping("/update")
    @ApiOperation("更新消课记录")
    @PreAuthorize("@ss.hasPermission('zhh:cost-class-record:update')")    public CommonResult<Boolean> updateCostClassRecord(@Valid @RequestBody CostClassRecordUpdateReqVO updateReqVO) {
        costClassRecordService.updateCostClassRecord(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除消课记录")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermission('zhh:cost-class-record:delete')")    public CommonResult<Boolean> deleteCostClassRecord(@RequestParam("id") Integer id) {
        costClassRecordService.deleteCostClassRecord(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得消课记录")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermission('zhh:cost-class-record:query')")
    public CommonResult<CostClassRecordRespVO> getCostClassRecord(@RequestParam("id") Integer id) {
        CostClassRecordDO costClassRecord = costClassRecordService.getCostClassRecord(id);
        return success(CostClassRecordConvert.INSTANCE.convert(costClassRecord));
    }

    @GetMapping("/list")
    @ApiOperation("获得消课记录列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('zhh:cost-class-record:query')")    public CommonResult<List<CostClassRecordRespVO>> getCostClassRecordList(@RequestParam("ids") Collection<Integer> ids) {
        List<CostClassRecordDO> list = costClassRecordService.getCostClassRecordList(ids);
        return success(CostClassRecordConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得消课记录分页")
    @PreAuthorize("@ss.hasPermission('zhh:cost-class-record:query')")    public CommonResult<PageResult<CostClassRecordRespVO>> getCostClassRecordPage(@Valid CostClassRecordPageReqVO pageVO) {
        PageResult<CostClassRecordDO> pageResult = costClassRecordService.getCostClassRecordPage(pageVO);
        return success(CostClassRecordConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出消课记录 Excel")
    @PreAuthorize("@ss.hasPermission('zhh:cost-class-record:export')")    @OperateLog(type = EXPORT)
    public void exportCostClassRecordExcel(@Valid CostClassRecordExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<CostClassRecordDO> list = costClassRecordService.getCostClassRecordList(exportReqVO);
        // 导出 Excel
        List<CostClassRecordExcelVO> datas = CostClassRecordConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "消课记录.xls", "数据", CostClassRecordExcelVO.class, datas);
    }

}
