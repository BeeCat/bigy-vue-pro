package cn.iocoder.yudao.module.system.service.studentAccount;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.student.vo.StudentCreateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.student.vo.StudentUpdateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.studentAccount.vo.*;
import cn.iocoder.yudao.module.system.controller.admin.studentAccountRecord.vo.ZhhStudentAccountRecordCreateReqVO;
import cn.iocoder.yudao.module.system.convert.studentAccount.StudentAccountConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.courseClass.CourseClassDO;
import cn.iocoder.yudao.module.system.dal.dataobject.coursePackage.ZhhCoursePackageDO;
import cn.iocoder.yudao.module.system.dal.dataobject.customer.ZhhCustomerDO;
import cn.iocoder.yudao.module.system.dal.dataobject.student.StudentDO;
import cn.iocoder.yudao.module.system.dal.dataobject.studentAccount.StudentAccountDO;
import cn.iocoder.yudao.module.system.dal.mysql.studentAccount.StudentAccountMapper;
import cn.iocoder.yudao.module.system.service.courseClass.CourseClassService;
import cn.iocoder.yudao.module.system.service.coursePackage.ZhhCoursePackageService;
import cn.iocoder.yudao.module.system.service.customer.ZhhCustomerService;
import cn.iocoder.yudao.module.system.service.student.StudentService;
import cn.iocoder.yudao.module.system.service.studentAccountRecord.ZhhStudentAccountRecordService;
import com.google.common.base.Preconditions;
import jodd.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.STUDENT_ACCOUNT_NOT_EXISTS;

/**
 * 学生账户 Service 实现类
 *
 * @author 毕国勇
 */
@Service
@Validated
public class StudentAccountServiceImpl implements StudentAccountService {

    @Resource
    private StudentService studentService;

    @Resource
    private StudentAccountMapper studentAccountMapper;

    @Resource
    private ZhhCustomerService customerService;

    @Resource
    private CourseClassService courseClassService;

    @Resource
    private ZhhCoursePackageService coursePackageService;

    @Resource
    private ZhhStudentAccountRecordService studentAccountRecordService;

    @Override
    public Integer createStudentAccount(StudentAccountCreateReqVO createReqVO) {
        // 插入
        StudentAccountDO studentAccount = StudentAccountConvert.INSTANCE.convert(createReqVO);

        StudentAccountExportReqVO reqVO = new StudentAccountExportReqVO();
        reqVO.setStudentId(studentAccount.getStudentId());
        reqVO.setClassCode(studentAccount.getClassCode());
        List<StudentAccountDO> studentAccountList = getStudentAccountList(reqVO);

        Assert.isTrue(CollectionUtil.isEmpty(studentAccountList), "已经存在");

        StudentDO studentDO = studentService.get(createReqVO.getStudentId());


        if (studentDO != null) {
            studentAccount.setName(studentDO.getName());
        }
        Integer buyClassAmount = studentAccount.getBuyClassAmount();
        Integer costClassAmount = studentAccount.getCostClassAmount();
        Integer haveClassAmount = studentAccount.getHaveClassAmount();

        Preconditions.checkArgument(buyClassAmount.intValue() == haveClassAmount.intValue() + costClassAmount.intValue(),"课程计算有误");
        studentAccountMapper.insert(studentAccount);
        // 返回
        return studentAccount.getId();
    }

    @Override
    public void updateStudentAccount(StudentAccountUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateStudentAccountExists(updateReqVO.getId());
        // 更新
        StudentAccountDO updateObj = StudentAccountConvert.INSTANCE.convert(updateReqVO);
        Integer buyClassAmount = updateObj.getBuyClassAmount() == null ? 0 : updateObj.getBuyClassAmount();
        Integer costClassAmount = updateObj.getCostClassAmount() == null ? 0 : updateObj.getCostClassAmount();
        Integer haveClassAmount = updateObj.getHaveClassAmount() == null ? 0 : updateObj.getHaveClassAmount();

        Preconditions.checkArgument(buyClassAmount.intValue() == haveClassAmount.intValue() + costClassAmount.intValue(),"课程计算有误");

        studentAccountMapper.updateById(updateObj);
    }

    @Override
    public void deleteStudentAccount(Integer id) {
        // 校验存在
        this.validateStudentAccountExists(id);
        // 删除
        studentAccountMapper.deleteById(id);
    }

    private void validateStudentAccountExists(Integer id) {
        if (studentAccountMapper.selectById(id) == null) {
            throw exception(STUDENT_ACCOUNT_NOT_EXISTS);
        }
    }

    @Override
    public StudentAccountDO getStudentAccount(Integer id) {
        return studentAccountMapper.selectById(id);
    }

    @Override
    public List<StudentAccountDO> getStudentAccountList(Collection<Integer> ids) {
        return studentAccountMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<StudentAccountDO> getStudentAccountPage(StudentAccountPageReqVO pageReqVO) {
        return studentAccountMapper.selectPage(pageReqVO);
    }

    @Override
    public List<StudentAccountDO> getStudentAccountList(StudentAccountExportReqVO exportReqVO) {
        return studentAccountMapper.selectList(exportReqVO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void buyCourse(StudentAccountBuyReqVO accountBuyReqVO) {

        // 获取客户
        String strCustomerId = accountBuyReqVO.getCustomerId();
        Assert.isTrue(StringUtil.isNotEmpty(strCustomerId), "参数错误");
        int customerId = Integer.parseInt(strCustomerId);
        ZhhCustomerDO customer = customerService.getCustomer(customerId);
        Assert.isTrue(customer != null, "客户不存在");

        String coursePackage = accountBuyReqVO.getCoursePackage();
        Assert.isTrue(StringUtil.isNotEmpty(coursePackage), "课程包不存在");
        ZhhCoursePackageDO coursePackageDO = coursePackageService.getCoursePackage(Integer.parseInt(coursePackage));
        Assert.isTrue(coursePackageDO != null, "课包存在");

        // 获取学生
        StudentDO studentDO = studentService.getByCustomer(customerId);
        Integer studentId = null;
        if (studentDO == null) {
            StudentCreateReqVO reqVO = getStudentCreateReqVO(customer);
            reqVO.setStageType(coursePackageDO.getStudentStageType());
            studentId = studentService.create(reqVO);
            studentDO = studentService.getByCustomer(customerId);
        } else {
            if (studentDO.getCustomerId() != null) {
                Assert.isTrue(studentDO.getCustomerId().intValue() == customerId, "客户不一致");
            }
            StudentUpdateReqVO updateReqVO = new StudentUpdateReqVO();
            updateReqVO.setId(studentDO.getId());
            updateReqVO.setCustomerId(customerId);
            if (!coursePackageDO.getStudentStageType().equals(studentDO.getStageType())) {
                if (studentDO.getStageType().equals("试听")) {
                    updateReqVO.setStageType(coursePackageDO.getStudentStageType());
                }
            }
            studentId = updateReqVO.getId();
            studentService.update(updateReqVO);
        }
        String courseCode = accountBuyReqVO.getCourse();
        Assert.isTrue(StringUtil.isNotEmpty(courseCode), "班级不存在");
        Assert.isTrue(studentId != null, "studentId不存在");
        StudentAccountDO studentAccountDO = getByStudentId(studentId, courseCode);

        // 学员账号
        StudentAccountCreateReqVO accountCreateReqVO = new StudentAccountCreateReqVO();
        accountCreateReqVO.setStudentId(studentId);
        accountCreateReqVO.setClassCode(courseCode);
        accountCreateReqVO.setName(studentDO.getName());
        Integer buyClassAmount = accountBuyReqVO.getBuyCourseNum();
        Integer haveClassAmount = accountBuyReqVO.getBuyCourseNum();
        Integer studentAccount = null;
        if (studentAccountDO != null) {
            buyClassAmount += studentAccountDO.getBuyClassAmount();
            haveClassAmount += studentAccountDO.getHaveClassAmount();

            StudentAccountUpdateReqVO updateReqVO = new StudentAccountUpdateReqVO();
            updateReqVO.setBuyClassAmount(buyClassAmount);
            updateReqVO.setHaveClassAmount(haveClassAmount);
            updateReqVO.setCostClassAmount(studentAccountDO.getCostClassAmount());
            updateReqVO.setId(studentAccountDO.getId());
            updateStudentAccount(updateReqVO);
        } else {
            accountCreateReqVO.setBuyClassAmount(buyClassAmount);
            accountCreateReqVO.setHaveClassAmount(haveClassAmount);
            accountCreateReqVO.setCostClassAmount(0);
            studentAccount = createStudentAccount(accountCreateReqVO);
        }

        CourseClassDO courseClass = courseClassService.getCourseClass(Integer.parseInt(accountBuyReqVO.getCourseClass()));


        // 学员账号记录
        ZhhStudentAccountRecordCreateReqVO studentAccountRecordCreateReqVO = new ZhhStudentAccountRecordCreateReqVO();
        studentAccountRecordCreateReqVO.setStudentAccountId(studentAccount);
        studentAccountRecordCreateReqVO.setStudentId(studentId);
        studentAccountRecordCreateReqVO.setName(studentDO.getName());
        studentAccountRecordCreateReqVO.setCoursePackage(accountBuyReqVO.getCoursePackage());
        studentAccountRecordCreateReqVO.setClassCode(courseClass.getClassCode());
        studentAccountRecordCreateReqVO.setBuyClassAmount(accountBuyReqVO.getBuyCourseNum());
        studentAccountRecordCreateReqVO.setHaveClassAmount(accountCreateReqVO.getHaveClassAmount());
        studentAccountRecordCreateReqVO.setCostClassAmount(accountCreateReqVO.getCostClassAmount());
        studentAccountRecordCreateReqVO.setCustomerId(customerId);
        studentAccountRecordCreateReqVO.setSaleFrom(accountBuyReqVO.getTrackTeacher());
        studentAccountRecordCreateReqVO.setTeacherFrom(accountBuyReqVO.getClassTeacher());
        studentAccountRecordCreateReqVO.setSourceFrom(accountBuyReqVO.getSourceFrom());
        studentAccountRecordService.createStudentAccountRecord(studentAccountRecordCreateReqVO);


    }

    private StudentAccountDO getByStudentId(Integer studentId, String classCode) {
        StudentAccountExportReqVO exportReqVO = new StudentAccountExportReqVO();
        exportReqVO.setStudentId(studentId);
        exportReqVO.setClassCode(classCode);
        List<StudentAccountDO> list = getStudentAccountList(exportReqVO);
        if (CollectionUtil.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    private StudentCreateReqVO getStudentCreateReqVO(ZhhCustomerDO customer) {
        StudentCreateReqVO reqVO = new StudentCreateReqVO();
        reqVO.setAddress(customer.getAddress());
        reqVO.setAge(customer.getAge());
        reqVO.setBirthDay(customer.getBirthDay());
        reqVO.setFatherMobile(customer.getFatherMobile());
        reqVO.setFatherName(customer.getFatherName());
        reqVO.setMotherMobile(customer.getMotherMobile());
        reqVO.setMotherName(customer.getMotherName());
        reqVO.setName(customer.getName());
        reqVO.setTitle(customer.getTitle());
        reqVO.setSex(customer.getSex());

        reqVO.setCustomerId(customer.getId());
        return reqVO;
    }

}
