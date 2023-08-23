package cn.iocoder.yudao.module.system.controller.admin.studentAccountRecord;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.system.controller.admin.studentAccountRecord.vo.*;
import cn.iocoder.yudao.module.system.convert.studentAccountRecord.ZhhStudentAccountRecordConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.studentAccountRecord.ZhhStudentAccountRecordDO;
import cn.iocoder.yudao.module.system.service.studentAccountRecord.ZhhStudentAccountRecordService;
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

@Api(tags = "管理后台 - 学生账户")
@RestController
@RequestMapping("/zhh/student-account-record")
@Validated
public class ZhhStudentAccountRecordController {

    @Resource
    private ZhhStudentAccountRecordService studentAccountRecordService;

    @PostMapping("/create")
    @ApiOperation("创建学生账户")
    @PreAuthorize("@ss.hasPermission('zhh:student-account-record:create')")    public CommonResult<Integer> createStudentAccountRecord(@Valid @RequestBody ZhhStudentAccountRecordCreateReqVO createReqVO) {
        return success(studentAccountRecordService.createStudentAccountRecord(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新学生账户")
    @PreAuthorize("@ss.hasPermission('zhh:student-account-record:update')")    public CommonResult<Boolean> updateStudentAccountRecord(@Valid @RequestBody ZhhStudentAccountRecordUpdateReqVO updateReqVO) {
        studentAccountRecordService.updateStudentAccountRecord(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除学生账户")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermission('zhh:student-account-record:delete')")    public CommonResult<Boolean> deleteStudentAccountRecord(@RequestParam("id") Integer id) {
        studentAccountRecordService.deleteStudentAccountRecord(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得学生账户")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermission('zhh:student-account-record:query')")
    public CommonResult<ZhhStudentAccountRecordRespVO> getStudentAccountRecord(@RequestParam("id") Integer id) {
        ZhhStudentAccountRecordDO studentAccountRecord = studentAccountRecordService.getStudentAccountRecord(id);
        return success(ZhhStudentAccountRecordConvert.INSTANCE.convert(studentAccountRecord));
    }

    @GetMapping("/list")
    @ApiOperation("获得学生账户列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('zhh:student-account-record:query')")    public CommonResult<List<ZhhStudentAccountRecordRespVO>> getStudentAccountRecordList(@RequestParam("ids") Collection<Integer> ids) {
        List<ZhhStudentAccountRecordDO> list = studentAccountRecordService.getStudentAccountRecordList(ids);
        return success(ZhhStudentAccountRecordConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得学生账户分页")
    @PreAuthorize("@ss.hasPermission('zhh:student-account-record:query')")    public CommonResult<PageResult<ZhhStudentAccountRecordRespVO>> getStudentAccountRecordPage(@Valid ZhhStudentAccountRecordPageReqVO pageVO) {
        PageResult<ZhhStudentAccountRecordDO> pageResult = studentAccountRecordService.getStudentAccountRecordPage(pageVO);
        return success(ZhhStudentAccountRecordConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出学生账户 Excel")
    @PreAuthorize("@ss.hasPermission('zhh:student-account-record:export')")    @OperateLog(type = EXPORT)
    public void exportStudentAccountRecordExcel(@Valid ZhhStudentAccountRecordExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ZhhStudentAccountRecordDO> list = studentAccountRecordService.getStudentAccountRecordList(exportReqVO);
        // 导出 Excel
        List<ZhhStudentAccountRecordExcelVO> datas = ZhhStudentAccountRecordConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "学生账户.xls", "数据", ZhhStudentAccountRecordExcelVO.class, datas);
    }

}
