package cn.iocoder.yudao.module.system.service.classStudent;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.classStudent.vo.*;
import cn.iocoder.yudao.module.system.controller.admin.courseClass.vo.CourseClassRespVO;
import cn.iocoder.yudao.module.system.convert.classStudent.ClassStudentConvert;
import cn.iocoder.yudao.module.system.convert.courseClass.CourseClassConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.classStudent.ClassStudentDO;
import cn.iocoder.yudao.module.system.dal.dataobject.student.StudentDO;
import cn.iocoder.yudao.module.system.dal.mysql.classStudent.ClassStudentMapper;
import cn.iocoder.yudao.module.system.service.student.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.CLASS_STUDENT_NOT_EXISTS;


/**
 * 班级学生 Service 实现类
 *
 * @author 毕国勇
 */
@Service
@Validated
public class ClassStudentServiceImpl implements ClassStudentService {

    @Resource
    private ClassStudentMapper classStudentMapper;

    @Resource
    private StudentService studentService;

    @Override
    public Integer createClassStudent(ClassStudentCreateReqVO createReqVO) {
        // 插入
        ClassStudentDO classStudent = ClassStudentConvert.INSTANCE.convert(createReqVO);
        classStudentMapper.insert(classStudent);
        // 返回
        return classStudent.getId();
    }

    @Override
    public void updateClassStudent(ClassStudentUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateClassStudentExists(updateReqVO.getId());
        // 更新
        ClassStudentDO updateObj = ClassStudentConvert.INSTANCE.convert(updateReqVO);
        classStudentMapper.updateById(updateObj);
    }

    @Override
    public void deleteClassStudent(Integer id) {
        // 校验存在
        this.validateClassStudentExists(id);
        // 删除
        classStudentMapper.deleteById(id);
    }

    private void validateClassStudentExists(Integer id) {
        if (classStudentMapper.selectById(id) == null) {
            throw exception(CLASS_STUDENT_NOT_EXISTS);
        }
    }

    @Override
    public ClassStudentDO getClassStudent(Integer id) {
        return classStudentMapper.selectById(id);
    }

    @Override
    public List<ClassStudentDO> getClassStudentList(Collection<Integer> ids) {
        return classStudentMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ClassStudentRespVO> getClassStudentPage(ClassStudentPageReqVO pageReqVO) {
        PageResult<ClassStudentDO> classStudentDOPageResult = classStudentMapper.selectPage(pageReqVO);
        PageResult<ClassStudentRespVO> respVOPageResult = ClassStudentConvert.INSTANCE.convertPage(classStudentDOPageResult);
        respVOPageResult.getList().forEach(classStudentRespVO -> {
            String studentCode = classStudentRespVO.getStudentCode();
            StudentDO studentDO = studentService.get(Integer.parseInt(studentCode));
            classStudentRespVO.setStudentName(studentDO.getName());
            classStudentRespVO.setStudentAge(studentDO.getAge());
            classStudentRespVO.setStudentSex(studentDO.getSex() == 1 ? "男" : "女");
            classStudentRespVO.setStudentAddress(studentDO.getAddress());
        });
        return respVOPageResult;
    }

    @Override
    public List<ClassStudentDO> getClassStudentList(ClassStudentExportReqVO exportReqVO) {
        return classStudentMapper.selectList(exportReqVO);
    }

}
