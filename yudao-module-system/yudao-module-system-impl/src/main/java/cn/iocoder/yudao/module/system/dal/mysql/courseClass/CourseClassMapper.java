package cn.iocoder.yudao.module.system.dal.mysql.courseClass;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.system.controller.admin.courseClass.vo.CourseClassExportReqVO;
import cn.iocoder.yudao.module.system.controller.admin.courseClass.vo.CourseClassPageReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.courseClass.CourseClassDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 课程班级 Mapper
 *
 * @author 毕国勇
 */
@Mapper
public interface CourseClassMapper extends BaseMapperX<CourseClassDO> {

    default PageResult<CourseClassDO> selectPage(CourseClassPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CourseClassDO>()
                .likeIfPresent(CourseClassDO::getName, reqVO.getName())
                .eqIfPresent(CourseClassDO::getClassCode, reqVO.getClassCode())
                .eqIfPresent(CourseClassDO::getCourseCode, reqVO.getCourseCode())
                .eqIfPresent(CourseClassDO::getTeacherCode, reqVO.getTeacherCode())
                .betweenIfPresent(CourseClassDO::getClassPlanTime, reqVO.getBeginClassPlanTime(), reqVO.getEndClassPlanTime())
                .eqIfPresent(CourseClassDO::getClassRoomCode, reqVO.getClassRoomCode())
                .eqIfPresent(CourseClassDO::getClassDicValue, reqVO.getClassDicValue())
                .eqIfPresent(CourseClassDO::getClassTimeDicValue, reqVO.getClassTimeDicValue())
                .betweenIfPresent(CourseClassDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(CourseClassDO::getId));
    }

    default List<CourseClassDO> selectList(CourseClassExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CourseClassDO>()
                .likeIfPresent(CourseClassDO::getName, reqVO.getName())
                .eqIfPresent(CourseClassDO::getClassCode, reqVO.getClassCode())
                .eqIfPresent(CourseClassDO::getCourseCode, reqVO.getCourseCode())
                .eqIfPresent(CourseClassDO::getTeacherCode, reqVO.getTeacherCode())
                .betweenIfPresent(CourseClassDO::getClassPlanTime, reqVO.getBeginClassPlanTime(), reqVO.getEndClassPlanTime())
                .eqIfPresent(CourseClassDO::getClassRoomCode, reqVO.getClassRoomCode())
                .eqIfPresent(CourseClassDO::getClassTimeDicValue, reqVO.getClassTimeDicValue())
                .eqIfPresent(CourseClassDO::getClassDicValue, reqVO.getClassDicValue())
                .betweenIfPresent(CourseClassDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(CourseClassDO::getId));
    }

}
