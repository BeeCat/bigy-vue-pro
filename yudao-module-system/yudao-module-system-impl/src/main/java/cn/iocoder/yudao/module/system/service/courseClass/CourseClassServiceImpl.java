package cn.iocoder.yudao.module.system.service.courseClass;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.courseClass.vo.*;
import cn.iocoder.yudao.module.system.convert.courseClass.CourseClassConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.courseClass.CourseClassDO;
import cn.iocoder.yudao.module.system.dal.dataobject.dict.DictDataDO;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.dal.mysql.courseClass.CourseClassMapper;
import cn.iocoder.yudao.module.system.service.dict.DictDataService;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.COURSE_CLASS_NOT_EXISTS;


/**
 * 课程班级 Service 实现类
 *
 * @author 毕国勇
 */
@Service
@Validated
public class CourseClassServiceImpl implements CourseClassService {

    @Resource
    private CourseClassMapper courseClassMapper;

    @Resource
    private DictDataService dictDataService;

    /**
     *
     */
    @Resource
    private AdminUserService adminUserService;

    @Override
    public Integer createCourseClass(CourseClassCreateReqVO createReqVO) {
        // 插入
        CourseClassDO courseClass = CourseClassConvert.INSTANCE.convert(createReqVO);
        courseClassMapper.insert(courseClass);
        // 返回
        return courseClass.getId();
    }

    @Override
    public void updateCourseClass(CourseClassUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateCourseClassExists(updateReqVO.getId());
        // 更新
        CourseClassDO updateObj = CourseClassConvert.INSTANCE.convert(updateReqVO);
        courseClassMapper.updateById(updateObj);
    }

    @Override
    public void deleteCourseClass(Integer id) {
        // 校验存在
        this.validateCourseClassExists(id);
        // 删除
        courseClassMapper.deleteById(id);
    }

    private void validateCourseClassExists(Integer id) {
        if (courseClassMapper.selectById(id) == null) {
            throw exception(COURSE_CLASS_NOT_EXISTS);
        }
    }

    @Override
    public CourseClassDO getCourseClass(Integer id) {
        return courseClassMapper.selectById(id);
    }

    @Override
    public List<CourseClassDO> getCourseClassList(Collection<Integer> ids) {
        return courseClassMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<CourseClassRespVO> getCourseClassPage(CourseClassPageReqVO pageReqVO) {
        PageResult<CourseClassDO> pageResult = courseClassMapper.selectPage(pageReqVO);
        PageResult<CourseClassRespVO> respVOPageResult = CourseClassConvert.INSTANCE.convertPage(pageResult);
        respVOPageResult.getList().forEach(cc -> {
            String classDicValue = cc.getClassDicValue();
            String classTimeDicValue = cc.getClassTimeDicValue();
            if (classDicValue != null) {
                DictDataDO dicDo = dictDataService.getDictData(Long.parseLong(classDicValue));
                cc.setClassDicDesc(dicDo.getLabel());
            }
            if (classTimeDicValue != null) {
                DictDataDO timeDicDo = dictDataService.getDictData(Long.parseLong(classTimeDicValue));
                cc.setClassTimeDicDesc(timeDicDo.getLabel());
            }
            Integer teacherCode = cc.getTeacherCode();
            if (teacherCode != null) {
                AdminUserDO user = adminUserService.getUser(teacherCode.longValue());
                cc.setTeacherName(user.getNickname());
            }
        });
        return respVOPageResult;
    }

    @Override
    public List<CourseClassDO> getCourseClassList(CourseClassExportReqVO exportReqVO) {
        return courseClassMapper.selectList(exportReqVO);
    }

}
