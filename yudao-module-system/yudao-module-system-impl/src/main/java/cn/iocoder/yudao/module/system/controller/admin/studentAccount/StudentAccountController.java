package cn.iocoder.yudao.module.system.controller.admin.studentAccount;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.system.controller.admin.studentAccount.vo.*;
import cn.iocoder.yudao.module.system.convert.studentAccount.StudentAccountConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.studentAccount.StudentAccountDO;
import cn.iocoder.yudao.module.system.service.studentAccount.StudentAccountService;
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


@Api(tags = "管理后台 - 学生账户")
@RestController
@RequestMapping("/zhh/student-account")
@Validated
@Slf4j
public class StudentAccountController {

    @Resource
    private StudentAccountService studentAccountService;

    @PostMapping("/create")
    @ApiOperation("创建学生账户")
    @PreAuthorize("@ss.hasPermission('zhh:student-account:create')")    public CommonResult<Integer> createStudentAccount(@Valid @RequestBody StudentAccountCreateReqVO createReqVO) {
        try {
            return success(studentAccountService.createStudentAccount(createReqVO));
        } catch (Exception e) {
            log.error("创建消课记录-error", e);
            return error(400, e.getMessage());
        }
    }

    @PutMapping("/update")
    @ApiOperation("更新学生账户")
    @PreAuthorize("@ss.hasPermission('zhh:student-account:update')")    public CommonResult<Boolean> updateStudentAccount(@Valid @RequestBody StudentAccountUpdateReqVO updateReqVO) {
        try {
            studentAccountService.updateStudentAccount(updateReqVO);
        } catch (Exception e) {
            log.error("业务错误", e);
            return error(400, e.getMessage());
        }
        return success(true);
    }

    @PostMapping("/buyCourse")
    @ApiOperation("购课")
    @PreAuthorize("@ss.hasPermission('zhh:student-account:update')")
    public CommonResult<Boolean> buyCourse(@Valid @RequestBody StudentAccountBuyReqVO accountBuyReqVO) {
        try {
            studentAccountService.buyCourse(accountBuyReqVO);
        } catch (Exception e) {
            log.error("业务错误", e);
            return error(400, e.getMessage());
        }
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除学生账户")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermission('zhh:student-account:delete')")    public CommonResult<Boolean> deleteStudentAccount(@RequestParam("id") Integer id) {
        studentAccountService.deleteStudentAccount(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得学生账户")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermission('zhh:student-account:query')")
    public CommonResult<StudentAccountRespVO> getStudentAccount(@RequestParam("id") Integer id) {
        StudentAccountDO studentAccount = studentAccountService.getStudentAccount(id);
        return success(StudentAccountConvert.INSTANCE.convert(studentAccount));
    }

    @GetMapping("/list")
    @ApiOperation("获得学生账户列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('zhh:student-account:query')")    public CommonResult<List<StudentAccountRespVO>> getStudentAccountList(@RequestParam("ids") Collection<Integer> ids) {
        List<StudentAccountDO> list = studentAccountService.getStudentAccountList(ids);
        return success(StudentAccountConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得学生账户分页")
    @PreAuthorize("@ss.hasPermission('zhh:student-account:query')")    public CommonResult<PageResult<StudentAccountRespVO>> getStudentAccountPage(@Valid StudentAccountPageReqVO pageVO) {
        PageResult<StudentAccountDO> pageResult = studentAccountService.getStudentAccountPage(pageVO);
        return success(StudentAccountConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出学生账户 Excel")
    @PreAuthorize("@ss.hasPermission('zhh:student-account:export')")    @OperateLog(type = EXPORT)
    public void exportStudentAccountExcel(@Valid StudentAccountExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<StudentAccountDO> list = studentAccountService.getStudentAccountList(exportReqVO);
        // 导出 Excel
        List<StudentAccountExcelVO> datas = StudentAccountConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "学生账户.xls", "数据", StudentAccountExcelVO.class, datas);
    }

}
