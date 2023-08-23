package cn.iocoder.yudao.module.system.controller.admin.customer;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.system.controller.admin.customer.vo.*;
import cn.iocoder.yudao.module.system.convert.customer.ZhhCustomerConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.customer.ZhhCustomerDO;
import cn.iocoder.yudao.module.system.service.customer.ZhhCustomerService;
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

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.error;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;


@Api(tags = "管理后台 - ")
@RestController
@RequestMapping("/zhh/customer")
@Validated
public class ZhhCustomerController {

    @Resource
    private ZhhCustomerService customerService;

    @PostMapping("/create")
    @ApiOperation("创建")
    @PreAuthorize("@ss.hasPermission('zhh:customer:create')")    public CommonResult<Integer> createCustomer(@Valid @RequestBody ZhhCustomerCreateReqVO createReqVO) {

        try {
            Integer customer = customerService.createCustomer(createReqVO);
            return success(customer);
        } catch (IllegalArgumentException exception) {
            return error(400, exception.getMessage());
        } catch (Exception exception) {
            return error(400, "系统错误");
        }

    }

    @PutMapping("/update")
    @ApiOperation("更新")
    @PreAuthorize("@ss.hasPermission('zhh:customer:update')")    public CommonResult<Boolean> updateCustomer(@Valid @RequestBody ZhhCustomerUpdateReqVO updateReqVO) {
        customerService.updateCustomer(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermission('zhh:customer:delete')")    public CommonResult<Boolean> deleteCustomer(@RequestParam("id") Integer id) {
        customerService.deleteCustomer(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermission('zhh:customer:query')")
    public CommonResult<ZhhCustomerRespVO> getCustomer(@RequestParam("id") Integer id) {
        ZhhCustomerDO customer = customerService.getCustomer(id);
        return success(ZhhCustomerConvert.INSTANCE.convert(customer));
    }

    @GetMapping("/list")
    @ApiOperation("获得列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('zhh:customer:query')")    public CommonResult<List<ZhhCustomerRespVO>> getCustomerList(@RequestParam("ids") Collection<Integer> ids) {
        List<ZhhCustomerDO> list = customerService.getCustomerList(ids);
        return success(ZhhCustomerConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得分页")
    @PreAuthorize("@ss.hasPermission('zhh:customer:query')")    public CommonResult<PageResult<ZhhCustomerRespVO>> getCustomerPage(@Valid ZhhCustomerPageReqVO pageVO) {
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        Set<Long> loginUserRoleIds = SecurityFrameworkUtils.getLoginUserRoleIds();
        boolean adminFlag = loginUserRoleIds.contains(1L) || loginUserRoleIds.contains(108L);
        if (!adminFlag) {
            pageVO.setSaleFrom(loginUserId.toString());
        }
        PageResult<ZhhCustomerRespVO> pageResult = customerService.getCustomerPage(pageVO);
        return success(pageResult);
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出 Excel")
    @PreAuthorize("@ss.hasPermission('zhh:customer:export')")    @OperateLog(type = EXPORT)
    public void exportCustomerExcel(@Valid ZhhCustomerExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ZhhCustomerDO> list = customerService.getCustomerList(exportReqVO);
        // 导出 Excel
        List<ZhhCustomerExcelVO> datas = ZhhCustomerConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, ".xls", "数据", ZhhCustomerExcelVO.class, datas);
    }

}
