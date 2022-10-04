package cn.iocoder.yudao.module.system.dal.mysql.classRoom;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.system.controller.admin.classRoom.vo.ClassRoomExportReqVO;
import cn.iocoder.yudao.module.system.controller.admin.classRoom.vo.ClassRoomPageReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.classRoom.ClassRoomDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 课程教室 Mapper
 *
 * @author 毕国勇
 */
@Mapper
public interface ClassRoomMapper extends BaseMapperX<ClassRoomDO> {

    default PageResult<ClassRoomDO> selectPage(ClassRoomPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ClassRoomDO>()
                .likeIfPresent(ClassRoomDO::getName, reqVO.getName())
                .betweenIfPresent(ClassRoomDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(ClassRoomDO::getId));
    }

    default List<ClassRoomDO> selectList(ClassRoomExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ClassRoomDO>()
                .likeIfPresent(ClassRoomDO::getName, reqVO.getName())
                .betweenIfPresent(ClassRoomDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(ClassRoomDO::getId));
    }

}
