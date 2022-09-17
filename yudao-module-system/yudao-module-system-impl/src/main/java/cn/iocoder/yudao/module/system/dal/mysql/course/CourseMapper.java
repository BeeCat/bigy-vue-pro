package cn.iocoder.yudao.module.system.dal.mysql.course;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.system.controller.admin.course.vo.CourseExportReqVO;
import cn.iocoder.yudao.module.system.controller.admin.course.vo.CoursePageReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.course.CourseDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 课程 Mapper
 *
 * @author 毕国勇
 */
@Mapper
public interface CourseMapper extends BaseMapperX<CourseDO> {

    default PageResult<CourseDO> selectPage(CoursePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CourseDO>()
                .likeIfPresent(CourseDO::getCourseName, reqVO.getCourseName())
                .eqIfPresent(CourseDO::getCourseCode, reqVO.getCourseCode())
                .betweenIfPresent(CourseDO::getCourseTime, reqVO.getBeginCourseTime(), reqVO.getEndCourseTime())
                .betweenIfPresent(CourseDO::getClassPlanTime, reqVO.getBeginClassPlanTime(), reqVO.getEndClassPlanTime())
                .betweenIfPresent(CourseDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(CourseDO::getId));
    }

    default List<CourseDO> selectList(CourseExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CourseDO>()
                .likeIfPresent(CourseDO::getCourseName, reqVO.getCourseName())
                .eqIfPresent(CourseDO::getCourseCode, reqVO.getCourseCode())
                .betweenIfPresent(CourseDO::getCourseTime, reqVO.getBeginCourseTime(), reqVO.getEndCourseTime())
                .betweenIfPresent(CourseDO::getClassPlanTime, reqVO.getBeginClassPlanTime(), reqVO.getEndClassPlanTime())
                .betweenIfPresent(CourseDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(CourseDO::getId));
    }

}
