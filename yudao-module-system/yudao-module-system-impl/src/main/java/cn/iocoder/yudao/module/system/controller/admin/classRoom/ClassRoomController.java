package cn.iocoder.yudao.module.system.controller.admin.classRoom;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.system.controller.admin.classRoom.vo.*;
import cn.iocoder.yudao.module.system.convert.classRoom.ClassRoomConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.classRoom.ClassRoomDO;
import cn.iocoder.yudao.module.system.service.classRoom.ClassRoomService;
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

@Api(tags = "管理后台 - 课程教室")
@RestController
@RequestMapping("/zhh/class-room")
@Validated
public class ClassRoomController {

    @Resource
    private ClassRoomService classRoomService;

    @PostMapping("/create")
    @ApiOperation("创建课程教室")
    @PreAuthorize("@ss.hasPermission('zhh:class-room:create')")    public CommonResult<Integer> createClassRoom(@Valid @RequestBody ClassRoomCreateReqVO createReqVO) {
        return success(classRoomService.createClassRoom(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新课程教室")
    @PreAuthorize("@ss.hasPermission('zhh:class-room:update')")    public CommonResult<Boolean> updateClassRoom(@Valid @RequestBody ClassRoomUpdateReqVO updateReqVO) {
        classRoomService.updateClassRoom(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除课程教室")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermission('zhh:class-room:delete')")    public CommonResult<Boolean> deleteClassRoom(@RequestParam("id") Integer id) {
        classRoomService.deleteClassRoom(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得课程教室")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermission('zhh:class-room:query')")
    public CommonResult<ClassRoomRespVO> getClassRoom(@RequestParam("id") Integer id) {
        ClassRoomDO classRoom = classRoomService.getClassRoom(id);
        return success(ClassRoomConvert.INSTANCE.convert(classRoom));
    }

    @GetMapping("/list")
    @ApiOperation("获得课程教室列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('zhh:class-room:query')")    public CommonResult<List<ClassRoomRespVO>> getClassRoomList(@RequestParam("ids") Collection<Integer> ids) {
        List<ClassRoomDO> list = classRoomService.getClassRoomList(ids);
        return success(ClassRoomConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得课程教室分页")
    @PreAuthorize("@ss.hasPermission('zhh:class-room:query')")    public CommonResult<PageResult<ClassRoomRespVO>> getClassRoomPage(@Valid ClassRoomPageReqVO pageVO) {
        PageResult<ClassRoomDO> pageResult = classRoomService.getClassRoomPage(pageVO);
        return success(ClassRoomConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出课程教室 Excel")
    @PreAuthorize("@ss.hasPermission('zhh:class-room:export')")    @OperateLog(type = EXPORT)
    public void exportClassRoomExcel(@Valid ClassRoomExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ClassRoomDO> list = classRoomService.getClassRoomList(exportReqVO);
        // 导出 Excel
        List<ClassRoomExcelVO> datas = ClassRoomConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "课程教室.xls", "数据", ClassRoomExcelVO.class, datas);
    }

}
