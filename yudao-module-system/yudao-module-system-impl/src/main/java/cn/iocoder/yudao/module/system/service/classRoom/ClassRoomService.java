package cn.iocoder.yudao.module.system.service.classRoom;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.classRoom.vo.ClassRoomCreateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.classRoom.vo.ClassRoomExportReqVO;
import cn.iocoder.yudao.module.system.controller.admin.classRoom.vo.ClassRoomPageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.classRoom.vo.ClassRoomUpdateReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.classRoom.ClassRoomDO;

import java.util.*;
import javax.validation.*;

/**
 * 课程教室 Service 接口
 *
 * @author 毕国勇
 */
public interface ClassRoomService {

    /**
     * 创建课程教室
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createClassRoom(@Valid ClassRoomCreateReqVO createReqVO);

    /**
     * 更新课程教室
     *
     * @param updateReqVO 更新信息
     */
    void updateClassRoom(@Valid ClassRoomUpdateReqVO updateReqVO);

    /**
     * 删除课程教室
     *
     * @param id 编号
     */
    void deleteClassRoom(Integer id);

    /**
     * 获得课程教室
     *
     * @param id 编号
     * @return 课程教室
     */
    ClassRoomDO getClassRoom(Integer id);

    /**
     * 获得课程教室列表
     *
     * @param ids 编号
     * @return 课程教室列表
     */
    List<ClassRoomDO> getClassRoomList(Collection<Integer> ids);

    /**
     * 获得课程教室分页
     *
     * @param pageReqVO 分页查询
     * @return 课程教室分页
     */
    PageResult<ClassRoomDO> getClassRoomPage(ClassRoomPageReqVO pageReqVO);

    /**
     * 获得课程教室列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 课程教室列表
     */
    List<ClassRoomDO> getClassRoomList(ClassRoomExportReqVO exportReqVO);

}
