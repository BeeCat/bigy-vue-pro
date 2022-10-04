package cn.iocoder.yudao.module.system.service.course;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.course.vo.*;
import cn.iocoder.yudao.module.system.controller.admin.courseClass.vo.CourseClassCreateReqVO;
import cn.iocoder.yudao.module.system.convert.course.CourseConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.course.CourseDO;
import cn.iocoder.yudao.module.system.dal.dataobject.dict.DictDataDO;
import cn.iocoder.yudao.module.system.dal.mysql.course.CourseMapper;
import cn.iocoder.yudao.module.system.service.courseClass.CourseClassService;
import cn.iocoder.yudao.module.system.service.dict.DictDataService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.COURSE_NOT_EXISTS;


/**
 * 课程 Service 实现类
 *
 * @author 毕国勇
 */
@Service
@Validated
public class CourseServiceImpl implements CourseService {

    @Resource
    private CourseMapper courseMapper;

    @Resource
    private CourseClassService courseClassService;

    /**
     *
     */
    @Resource
    private DictDataService dictDataService;

    @Override
    public Integer createCourse(CourseCreateReqVO createReqVO) {
        // 插入
        CourseDO course = CourseConvert.INSTANCE.convert(createReqVO);
        courseMapper.insert(course);
        // 返回
        return course.getId();
    }

    @Override
    public void updateCourse(CourseUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateCourseExists(updateReqVO.getId());
        // 更新
        CourseDO updateObj = CourseConvert.INSTANCE.convert(updateReqVO);
        courseMapper.updateById(updateObj);
    }

    @Override
    public void deleteCourse(Integer id) {
        // 校验存在
        this.validateCourseExists(id);
        // 删除
        courseMapper.deleteById(id);
    }

    private void validateCourseExists(Integer id) {
        if (courseMapper.selectById(id) == null) {
            throw exception(COURSE_NOT_EXISTS);
        }
    }

    @Override
    public CourseDO getCourse(Integer id) {
        return courseMapper.selectById(id);
    }

    @Override
    public List<CourseDO> getCourseList(Collection<Integer> ids) {
        return courseMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<CourseDO> getCoursePage(CoursePageReqVO pageReqVO) {
        return courseMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CourseDO> getCourseList(CourseExportReqVO exportReqVO) {
        return courseMapper.selectList(exportReqVO);
    }

    @Override
    public void assignCourse(CourseAssignReqVO updateReqVO) {

        CourseClassCreateReqVO classCreateReqVO = new CourseClassCreateReqVO();
        String classTimeDicValue = updateReqVO.getClassTimeDicValue();
        String classDicValue = updateReqVO.getClassDicValue();
        DictDataDO timeDicDo = dictDataService.getDictData(Long.parseLong(classTimeDicValue));
        DictDataDO classDictDo = dictDataService.getDictData(Long.parseLong(classDicValue));
        classCreateReqVO.setName(updateReqVO.getCourseName() + "-" + classDictDo.getLabel() + "-" + timeDicDo.getLabel());
        classCreateReqVO.setClassCode(111);
        classCreateReqVO.setCourseCode(updateReqVO.getId());
        classCreateReqVO.setClassRoomCode(updateReqVO.getClassRoomId());
        classCreateReqVO.setTeacherCode(updateReqVO.getTeacherId());
        classCreateReqVO.setClassDicValue(classDicValue);
        classCreateReqVO.setClassTimeDicValue(classTimeDicValue);

        courseClassService.createCourseClass(classCreateReqVO);
    }

}
