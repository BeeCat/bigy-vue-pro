package cn.iocoder.yudao.module.system.dal.mysql.courseContent;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.system.controller.admin.courseContent.vo.CourseContentExportReqVO;
import cn.iocoder.yudao.module.system.controller.admin.courseContent.vo.CourseContentPageReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.courseContent.CourseContentDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 课程体系 Mapper
 *
 * @author 毕国勇
 */
@Mapper
public interface CourseContentMapper extends BaseMapperX<CourseContentDO> {

    default PageResult<CourseContentDO> selectPage(CourseContentPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CourseContentDO>()
                .likeIfPresent(CourseContentDO::getCourseName, reqVO.getCourseName())
                .eqIfPresent(CourseContentDO::getCourseCode, reqVO.getCourseCode())
                .eqIfPresent(CourseContentDO::getCourseSubject, reqVO.getCourseSubject())
                .betweenIfPresent(CourseContentDO::getCourseTime, reqVO.getBeginCourseTime(), reqVO.getEndCourseTime())
                .betweenIfPresent(CourseContentDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(CourseContentDO::getId));
    }

    default List<CourseContentDO> selectList(CourseContentExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CourseContentDO>()
                .likeIfPresent(CourseContentDO::getCourseName, reqVO.getCourseName())
                .eqIfPresent(CourseContentDO::getCourseCode, reqVO.getCourseCode())
                .eqIfPresent(CourseContentDO::getCourseSubject, reqVO.getCourseSubject())
                .betweenIfPresent(CourseContentDO::getCourseTime, reqVO.getBeginCourseTime(), reqVO.getEndCourseTime())
                .betweenIfPresent(CourseContentDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(CourseContentDO::getId));
    }

}
