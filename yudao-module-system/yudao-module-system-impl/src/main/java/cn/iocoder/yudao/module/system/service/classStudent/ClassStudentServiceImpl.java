package cn.iocoder.yudao.module.system.service.classStudent;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.classStudent.vo.*;
import cn.iocoder.yudao.module.system.controller.admin.studentAccount.vo.StudentAccountExportReqVO;
import cn.iocoder.yudao.module.system.controller.admin.studentAccount.vo.StudentAccountPageReqVO;
import cn.iocoder.yudao.module.system.convert.classStudent.ClassStudentConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.classStudent.ClassStudentDO;
import cn.iocoder.yudao.module.system.dal.dataobject.student.StudentDO;
import cn.iocoder.yudao.module.system.dal.dataobject.studentAccount.StudentAccountDO;
import cn.iocoder.yudao.module.system.dal.mysql.classStudent.ClassStudentMapper;
import cn.iocoder.yudao.module.system.service.student.StudentService;
import cn.iocoder.yudao.module.system.service.studentAccount.StudentAccountService;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
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

    @Resource
    private StudentAccountService studentAccountService;

    @Override
    public Integer createClassStudent(ClassStudentCreateReqVO createReqVO) {
        // 插入
        ClassStudentDO classStudent = ClassStudentConvert.INSTANCE.convert(createReqVO);
        String classCode = classStudent.getClassCode();
        String studentCode = classStudent.getStudentCode();
        ClassStudentPageReqVO reqVO = new ClassStudentPageReqVO();
        reqVO.setClassCode(classCode);
        reqVO.setStudentCode(studentCode);
        PageResult<ClassStudentRespVO> classStudentPage = getClassStudentPage(reqVO);
        Preconditions.checkArgument(CollectionUtil.isEmpty(classStudentPage.getList()), "班级已有该学生");

        synchronized (studentCode.intern()) {
            StudentAccountExportReqVO studentAccountPageReqVO = new StudentAccountExportReqVO();
            studentAccountPageReqVO.setStudentId(Integer.parseInt(classStudent.getStudentCode()));
            studentAccountPageReqVO.setClassCode(classStudent.getCourseCode());
            List<StudentAccountDO> studentAccountList = studentAccountService.getStudentAccountList(studentAccountPageReqVO);
            Assert.isTrue(CollectionUtil.isNotEmpty(studentAccountList), "学员该课程不足");
            StudentAccountDO studentAccountDO = studentAccountList.get(0);
            Integer costClassAmount = studentAccountDO.getCostClassAmount() == null ? 0 : studentAccountDO.getCostClassAmount();
            Assert.isTrue(studentAccountDO.getBuyClassAmount().intValue() > costClassAmount.intValue(), "无剩余课时喽");

            classStudentMapper.insert(classStudent);
        }
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
        List<ClassStudentRespVO> list = respVOPageResult.getList();
        for (int i = 0; i < list.size(); i++) {
            ClassStudentRespVO classStudentRespVO = list.get(i);
            String studentCode = classStudentRespVO.getStudentCode();
            if ("试听".equals(classStudentRespVO.getStageType())) {
                if (DateUtil.compare(classStudentRespVO.getValidateTime(), new Date()) >= 0) {
                    continue;
                }
            }
            StudentDO studentDO = studentService.get(Integer.parseInt(studentCode));
            if (studentDO != null) {
                classStudentRespVO.setStudentName(studentDO.getName());
                classStudentRespVO.setStudentAddress(studentDO.getAddress());
            }
        }
        return respVOPageResult;
    }

    @Override
    public List<ClassStudentDO> getClassStudentList(ClassStudentExportReqVO exportReqVO) {
        return classStudentMapper.selectList(exportReqVO);
    }

    @Override
    public PageResult<ClassStudentRespVO> getClassStudentAccountPage(ClassStudentPageReqVO pageVO) {
        PageResult<ClassStudentDO> classStudentDOPageResult = classStudentMapper.selectPage(pageVO);
        PageResult<ClassStudentRespVO> respVOPageResult = ClassStudentConvert.INSTANCE.convertPage(classStudentDOPageResult);
        List<ClassStudentRespVO> list = respVOPageResult.getList();

        list.stream().forEach(classStudentRespVO -> {

            StudentAccountPageReqVO accountPageReqVO = new StudentAccountPageReqVO();
            accountPageReqVO.setStudentId(Integer.parseInt(classStudentRespVO.getStudentCode()));
            accountPageReqVO.setClassCode(classStudentRespVO.getCourseCode());
            PageResult<StudentAccountDO> studentAccountList = studentAccountService.getStudentAccountPage(accountPageReqVO);
            if (CollectionUtil.isNotEmpty(studentAccountList.getList())) {
                StudentAccountDO studentAccountDO = studentAccountList.getList().get(0);
                if (studentAccountDO == null) {
                    classStudentRespVO.setHaveClassAmount(0);
                } else {
                    int buy = studentAccountDO.getBuyClassAmount() == null ? 0 : studentAccountDO.getBuyClassAmount();
                    int cost = studentAccountDO.getCostClassAmount() == null ? 0 : studentAccountDO.getCostClassAmount();
                    classStudentRespVO.setHaveClassAmount(buy - cost);
                    classStudentRespVO.setCostClassAmount(studentAccountDO.getCostClassAmount());
                }
            } else {
                classStudentRespVO.setHaveClassAmount(0);
            }
            String studentCode = classStudentRespVO.getStudentCode();
            StudentDO studentDO = studentService.get(Integer.parseInt(studentCode));
            if (studentDO != null) {
                classStudentRespVO.setStudentName(studentDO.getName());
                classStudentRespVO.setStudentAge(studentDO.getAge());
                classStudentRespVO.setStudentSex(studentDO.getSex() == 1 ? "男" : "女");
                classStudentRespVO.setStudentAddress(studentDO.getAddress());
            }
        });
        return respVOPageResult;
    }

}
