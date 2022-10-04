package cn.iocoder.yudao.module.system.service.classRoom;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.classRoom.vo.ClassRoomCreateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.classRoom.vo.ClassRoomExportReqVO;
import cn.iocoder.yudao.module.system.controller.admin.classRoom.vo.ClassRoomPageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.classRoom.vo.ClassRoomUpdateReqVO;
import cn.iocoder.yudao.module.system.convert.classRoom.ClassRoomConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.classRoom.ClassRoomDO;
import cn.iocoder.yudao.module.system.dal.mysql.classRoom.ClassRoomMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.CLASS_ROOM_NOT_EXISTS;


/**
 * 课程教室 Service 实现类
 *
 * @author 毕国勇
 */
@Service
@Validated
public class ClassRoomServiceImpl implements ClassRoomService {

    @Resource
    private ClassRoomMapper classRoomMapper;

    @Override
    public Integer createClassRoom(ClassRoomCreateReqVO createReqVO) {
        // 插入
        ClassRoomDO classRoom = ClassRoomConvert.INSTANCE.convert(createReqVO);
        classRoomMapper.insert(classRoom);
        // 返回
        return classRoom.getId();
    }

    @Override
    public void updateClassRoom(ClassRoomUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateClassRoomExists(updateReqVO.getId());
        // 更新
        ClassRoomDO updateObj = ClassRoomConvert.INSTANCE.convert(updateReqVO);
        classRoomMapper.updateById(updateObj);
    }

    @Override
    public void deleteClassRoom(Integer id) {
        // 校验存在
        this.validateClassRoomExists(id);
        // 删除
        classRoomMapper.deleteById(id);
    }

    private void validateClassRoomExists(Integer id) {
        if (classRoomMapper.selectById(id) == null) {
            throw exception(CLASS_ROOM_NOT_EXISTS);
        }
    }

    @Override
    public ClassRoomDO getClassRoom(Integer id) {
        return classRoomMapper.selectById(id);
    }

    @Override
    public List<ClassRoomDO> getClassRoomList(Collection<Integer> ids) {
        return classRoomMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ClassRoomDO> getClassRoomPage(ClassRoomPageReqVO pageReqVO) {
        return classRoomMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ClassRoomDO> getClassRoomList(ClassRoomExportReqVO exportReqVO) {
        return classRoomMapper.selectList(exportReqVO);
    }

}
