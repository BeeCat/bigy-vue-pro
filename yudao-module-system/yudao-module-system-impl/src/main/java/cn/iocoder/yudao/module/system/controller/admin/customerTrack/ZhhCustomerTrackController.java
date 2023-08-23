package cn.iocoder.yudao.module.system.controller.admin.customerTrack;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.system.controller.admin.customerTrack.vo.*;
import cn.iocoder.yudao.module.system.convert.customerTrack.ZhhCustomerTrackConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.customerTrack.ZhhCustomerTrackDO;
import cn.iocoder.yudao.module.system.service.customerTrack.ZhhCustomerTrackService;
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
@RequestMapping("/zhh/customer-track")
@Validated
public class ZhhCustomerTrackController {

    @Resource
    private ZhhCustomerTrackService customerTrackService;

    @PostMapping("/create")
    @ApiOperation("创建")
    @PreAuthorize("@ss.hasPermission('zhh:customer-track:create')")    public CommonResult<Integer> createCustomerTrack(@Valid @RequestBody ZhhCustomerTrackCreateReqVO createReqVO) {
        return success(customerTrackService.createCustomerTrack(createReqVO));
    }

    @PostMapping("/inviteClass")
    @ApiOperation("创建")
    @PreAuthorize("@ss.hasPermission('zhh:customer-track:create')")    public CommonResult<Integer> createCustomerInvite(@Valid @RequestBody ZhhCustomerInviteCreateReqVO createReqVO) {
        return success(customerTrackService.createCustomerInvite(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新")
    @PreAuthorize("@ss.hasPermission('zhh:customer-track:update')")    public CommonResult<Boolean> updateCustomerTrack(@Valid @RequestBody ZhhCustomerTrackUpdateReqVO updateReqVO) {
        customerTrackService.updateCustomerTrack(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermission('zhh:customer-track:delete')")    public CommonResult<Boolean> deleteCustomerTrack(@RequestParam("id") Integer id) {
        customerTrackService.deleteCustomerTrack(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermission('zhh:customer-track:query')")
    public CommonResult<ZhhCustomerTrackRespVO> getCustomerTrack(@RequestParam("id") Integer id) {
        ZhhCustomerTrackDO customerTrack = customerTrackService.getCustomerTrack(id);
        return success(ZhhCustomerTrackConvert.INSTANCE.convert(customerTrack));
    }

    @GetMapping("/list")
    @ApiOperation("获得列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('zhh:customer-track:query')")    public CommonResult<List<ZhhCustomerTrackRespVO>> getCustomerTrackList(@RequestParam("ids") Collection<Integer> ids) {
        List<ZhhCustomerTrackDO> list = customerTrackService.getCustomerTrackList(ids);
        return success(ZhhCustomerTrackConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得分页")
    @PreAuthorize("@ss.hasPermission('zhh:customer-track:query')")    public CommonResult<PageResult<ZhhCustomerTrackRespVO>> getCustomerTrackPage(@Valid ZhhCustomerTrackPageReqVO pageVO) {
        PageResult<ZhhCustomerTrackDO> pageResult = customerTrackService.getCustomerTrackPage(pageVO);
        return success(ZhhCustomerTrackConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出 Excel")
    @PreAuthorize("@ss.hasPermission('zhh:customer-track:export')")    @OperateLog(type = EXPORT)
    public void exportCustomerTrackExcel(@Valid ZhhCustomerTrackExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ZhhCustomerTrackDO> list = customerTrackService.getCustomerTrackList(exportReqVO);
        // 导出 Excel
        List<ZhhCustomerTrackExcelVO> datas = ZhhCustomerTrackConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, ".xls", "数据", ZhhCustomerTrackExcelVO.class, datas);
    }

}
