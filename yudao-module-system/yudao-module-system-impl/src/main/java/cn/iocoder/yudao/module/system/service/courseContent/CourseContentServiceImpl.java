package cn.iocoder.yudao.module.system.service.courseContent;

import cn.hutool.core.collection.CollectionUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.course.vo.CourseExportReqVO;
import cn.iocoder.yudao.module.system.controller.admin.courseContent.vo.*;
import cn.iocoder.yudao.module.system.convert.courseContent.CourseContentConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.course.CourseDO;
import cn.iocoder.yudao.module.system.dal.dataobject.courseContent.CourseContentDO;
import cn.iocoder.yudao.module.system.dal.mysql.courseContent.CourseContentMapper;
import cn.iocoder.yudao.module.system.service.course.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.COURSE_CONTENT_NOT_EXISTS;

/**
 * 课程体系 Service 实现类
 *
 * @author 毕国勇
 */
@Service
@Validated
public class CourseContentServiceImpl implements CourseContentService {

    @Resource
    private CourseContentMapper courseContentMapper;

    @Resource
    private CourseService courseService;

    @Override
    public Integer createCourseContent(CourseContentCreateReqVO createReqVO) {
        // 插入
        CourseContentDO courseContent = CourseContentConvert.INSTANCE.convert(createReqVO);
        CourseExportReqVO reqVO = new CourseExportReqVO();
        reqVO.setCourseCode(createReqVO.getCourseCode());

        List<CourseDO> courseList = courseService.getCourseList(reqVO);
        if (CollectionUtil.isNotEmpty(courseList)) {
            courseContent.setCourseName(courseList.get(0).getCourseName());
        }
        courseContentMapper.insert(courseContent);
        // 返回
        return courseContent.getId();
    }

    @Override
    public void updateCourseContent(CourseContentUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateCourseContentExists(updateReqVO.getId());
        // 更新
        CourseContentDO updateObj = CourseContentConvert.INSTANCE.convert(updateReqVO);
        CourseExportReqVO reqVO = new CourseExportReqVO();
        reqVO.setCourseCode(updateObj.getCourseCode());

        List<CourseDO> courseList = courseService.getCourseList(reqVO);
        if (CollectionUtil.isNotEmpty(courseList)) {
            updateObj.setCourseName(courseList.get(0).getCourseName());
        }
        courseContentMapper.updateById(updateObj);
    }

    @Override
    public void deleteCourseContent(Integer id) {
        // 校验存在
        this.validateCourseContentExists(id);
        // 删除
        courseContentMapper.deleteById(id);
    }

    private void validateCourseContentExists(Integer id) {
        if (courseContentMapper.selectById(id) == null) {
            throw exception(COURSE_CONTENT_NOT_EXISTS);
        }
    }

    @Override
    public CourseContentDO getCourseContent(Integer id) {
        return courseContentMapper.selectById(id);
    }

    @Override
    public List<CourseContentDO> getCourseContentList(Collection<Integer> ids) {
        return courseContentMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<CourseContentRespVO> getCourseContentPage(CourseContentPageReqVO pageReqVO) {
        PageResult<CourseContentDO> pageResult = courseContentMapper.selectPage(pageReqVO);
        return CourseContentConvert.INSTANCE.convertPage(pageResult);
    }

    @Override
    public List<CourseContentDO> getCourseContentList(CourseContentExportReqVO exportReqVO) {
        return courseContentMapper.selectList(exportReqVO);
    }

}
