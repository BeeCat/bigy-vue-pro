package cn.iocoder.yudao.module.system.convert.todoList;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.todoList.vo.ZhhTodoListCreateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.todoList.vo.ZhhTodoListExcelVO;
import cn.iocoder.yudao.module.system.controller.admin.todoList.vo.ZhhTodoListRespVO;
import cn.iocoder.yudao.module.system.controller.admin.todoList.vo.ZhhTodoListUpdateReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.todoList.ZhhTodoListDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * todolist 待办项 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface ZhhTodoListConvert {

    ZhhTodoListConvert INSTANCE = Mappers.getMapper(ZhhTodoListConvert.class);

    ZhhTodoListDO convert(ZhhTodoListCreateReqVO bean);

    ZhhTodoListDO convert(ZhhTodoListUpdateReqVO bean);

    ZhhTodoListRespVO convert(ZhhTodoListDO bean);

    List<ZhhTodoListRespVO> convertList(List<ZhhTodoListDO> list);

    PageResult<ZhhTodoListRespVO> convertPage(PageResult<ZhhTodoListDO> page);

    List<ZhhTodoListExcelVO> convertList02(List<ZhhTodoListDO> list);

}
