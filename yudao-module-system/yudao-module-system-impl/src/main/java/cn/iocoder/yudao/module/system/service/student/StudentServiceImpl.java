package cn.iocoder.yudao.module.system.service.student;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.student.vo.StudentCreateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.student.vo.StudentExportReqVO;
import cn.iocoder.yudao.module.system.controller.admin.student.vo.StudentPageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.student.vo.StudentUpdateReqVO;
import cn.iocoder.yudao.module.system.convert.student.StudentConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.student.StudentDO;
import cn.iocoder.yudao.module.system.dal.mysql.student.StudentMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.STUDENT_NOT_EXISTS;

/**
 * 学员管理 Service 实现类
 *
 * @author 毕国勇
 */
@Service
@Validated
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Override
    public Integer createStudent(StudentCreateReqVO createReqVO) {
        // 插入
        StudentDO student = StudentConvert.INSTANCE.convert(createReqVO);
        studentMapper.insert(student);
        // 返回
        return student.getId();
    }

    @Override
    public void updateStudent(StudentUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateStudentExists(updateReqVO.getId());
        // 更新
        StudentDO updateObj = StudentConvert.INSTANCE.convert(updateReqVO);
        studentMapper.updateById(updateObj);
    }

    @Override
    public void deleteStudent(Integer id) {
        // 校验存在
        this.validateStudentExists(id);
        // 删除
        studentMapper.deleteById(id);
    }

    private void validateStudentExists(Integer id) {
        if (studentMapper.selectById(id) == null) {
            throw exception(STUDENT_NOT_EXISTS);
        }
    }

    @Override
    public StudentDO getStudent(Integer id) {
        return studentMapper.selectById(id);
    }

    @Override
    public List<StudentDO> getStudentList(Collection<Integer> ids) {
        return studentMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<StudentDO> getStudentPage(StudentPageReqVO pageReqVO) {
        return studentMapper.selectPage(pageReqVO);
    }

    @Override
    public List<StudentDO> getStudentList(StudentExportReqVO exportReqVO) {
        return studentMapper.selectList(exportReqVO);
    }

}
