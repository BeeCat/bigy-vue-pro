package cn.iocoder.yudao.module.system.convert.todoListLog;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.todoListLog.vo.ZhhTodoListLogCreateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.todoListLog.vo.ZhhTodoListLogExcelVO;
import cn.iocoder.yudao.module.system.controller.admin.todoListLog.vo.ZhhTodoListLogRespVO;
import cn.iocoder.yudao.module.system.controller.admin.todoListLog.vo.ZhhTodoListLogUpdateReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.todoListLog.ZhhTodoListLogDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * todolist 记录 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface ZhhTodoListLogConvert {

    ZhhTodoListLogConvert INSTANCE = Mappers.getMapper(ZhhTodoListLogConvert.class);

    ZhhTodoListLogDO convert(ZhhTodoListLogCreateReqVO bean);

    ZhhTodoListLogDO convert(ZhhTodoListLogUpdateReqVO bean);

    ZhhTodoListLogRespVO convert(ZhhTodoListLogDO bean);

    List<ZhhTodoListLogRespVO> convertList(List<ZhhTodoListLogDO> list);

    PageResult<ZhhTodoListLogRespVO> convertPage(PageResult<ZhhTodoListLogDO> page);

    List<ZhhTodoListLogExcelVO> convertList02(List<ZhhTodoListLogDO> list);

}
