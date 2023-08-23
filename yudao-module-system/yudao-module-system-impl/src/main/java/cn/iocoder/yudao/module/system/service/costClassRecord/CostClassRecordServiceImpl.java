package cn.iocoder.yudao.module.system.service.costClassRecord;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.costClassRecord.vo.*;
import cn.iocoder.yudao.module.system.controller.admin.courseClass.vo.CourseClassExportReqVO;
import cn.iocoder.yudao.module.system.controller.admin.studentAccount.vo.StudentAccountExportReqVO;
import cn.iocoder.yudao.module.system.controller.admin.studentAccount.vo.StudentAccountUpdateReqVO;
import cn.iocoder.yudao.module.system.convert.costClassRecord.CostClassRecordConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.costClassRecord.CostClassRecordDO;
import cn.iocoder.yudao.module.system.dal.dataobject.courseClass.CourseClassDO;
import cn.iocoder.yudao.module.system.dal.dataobject.student.StudentDO;
import cn.iocoder.yudao.module.system.dal.dataobject.studentAccount.StudentAccountDO;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.dal.mysql.costClassRecord.CostClassRecordMapper;
import cn.iocoder.yudao.module.system.service.courseClass.CourseClassService;
import cn.iocoder.yudao.module.system.service.student.StudentService;
import cn.iocoder.yudao.module.system.service.studentAccount.StudentAccountService;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import com.google.common.base.Preconditions;
import jodd.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.COST_CLASS_RECORD_NOT_EXISTS;

/**
 * 消课记录 Service 实现类
 *
 * @author 毕国勇
 */
@Service
@Validated
public class CostClassRecordServiceImpl implements CostClassRecordService {

    @Resource
    private CostClassRecordMapper costClassRecordMapper;

    @Resource
    private CourseClassService courseClassService;

    @Resource
    private StudentAccountService studentAccountService;

    @Resource
    private StudentService studentService;

    /**
     *
     */
    @Resource
    private AdminUserService adminUserService;


    @Override
    public Integer createCostClassRecord(CostClassRecordCreateReqVO createReqVO) {

        String courseRecordCode = createReqVO.getCourseRecordCode();
        String studentCode = createReqVO.getStudentCode();
        String courseContentCode = createReqVO.getCourseContentCode();
        Assert.isTrue(StringUtil.isNotEmpty(courseContentCode), "请选择课程内容");
        CostClassRecordPageReqVO reqVO = new CostClassRecordPageReqVO();
        reqVO.setCourseRecordCode(courseRecordCode);
        reqVO.setStudentCode(studentCode);
        PageResult<CostClassRecordDO> costClassRecordPage = getCostClassRecordPage(reqVO);
        Preconditions.checkArgument(CollectionUtil.isEmpty(costClassRecordPage.getList()), "请勿重复考勤");

        if (createReqVO.getCostClassType() == 1 || createReqVO.getHandFlag() != 1) {
            costCourse(createReqVO, studentCode);
        }

        // 插入
        CostClassRecordDO costClassRecord = CostClassRecordConvert.INSTANCE.convert(createReqVO);
        String classCode = costClassRecord.getClassCode();
        CourseClassExportReqVO vo = new CourseClassExportReqVO();
        vo.setClassCode(classCode);
        List<CourseClassDO> courseClassList = courseClassService.getCourseClassList(vo);
        CourseClassDO courseClass = courseClassList.get(0);
        String teacherCode = courseClass.getTeacherCode();
        AdminUserDO user = adminUserService.getUser(Long.parseLong(teacherCode));

        costClassRecord.setCostClassType(createReqVO.getCostClassType());
        costClassRecord.setCostTeacherCode(teacherCode);
        costClassRecord.setCostTeacherName(user.getNickname());
        costClassRecord.setCostTime(new Date());
        costClassRecordMapper.insert(costClassRecord);
        // 返回
        return costClassRecord.getId();
    }

    private void costCourse(CostClassRecordCreateReqVO createReqVO, String studentCode) {
        synchronized (studentCode.intern()) {
            StudentAccountExportReqVO studentAccountPageReqVO = new StudentAccountExportReqVO();
            studentAccountPageReqVO.setStudentId(Integer.parseInt(createReqVO.getStudentCode()));
            studentAccountPageReqVO.setClassCode(createReqVO.getCourseCode());
            List<StudentAccountDO> studentAccountList = studentAccountService.getStudentAccountList(studentAccountPageReqVO);
            Assert.isTrue(CollectionUtil.isNotEmpty(studentAccountList), "学员该课程不足");
            StudentAccountDO studentAccountDO = studentAccountList.get(0);
            Integer costClassAmount = studentAccountDO.getCostClassAmount() == null ? 0 : studentAccountDO.getCostClassAmount();
            Assert.isTrue(studentAccountDO.getBuyClassAmount().intValue() > costClassAmount.intValue(), "无剩余课时喽");
            studentAccountDO.setCostClassAmount(costClassAmount);

            StudentAccountUpdateReqVO updateReqVO = new StudentAccountUpdateReqVO();
            updateReqVO.setId(studentAccountDO.getId());
            updateReqVO.setHaveClassAmount(studentAccountDO.getHaveClassAmount()-1);
            updateReqVO.setCostClassAmount(studentAccountDO.getCostClassAmount()+1);
            updateReqVO.setBuyClassAmount(studentAccountDO.getBuyClassAmount());
            studentAccountService.updateStudentAccount(updateReqVO);

        }
    }

    public static void main(String[] args) {
        Date costCourseDate = DateUtil.parseDate("2023-5-13");
        String format = DateUtil.format(costCourseDate, "yyyy-MM-dd");
        String[] arrDate = format.split("-");
        System.out.println(arrDate[0]);
        System.out.println(arrDate[1]);
        System.out.println(arrDate[2]);
        System.out.println(DateUtil.dayOfWeek(costCourseDate));
    }
    @Override
    public Integer handCostClass(CostClassHandCreateReqVO createReqVO) {
        String costCourseClass = createReqVO.getCostCourseClass();
        String studentId = createReqVO.getStudentId();
        String teacherCode = createReqVO.getCostClassTeacher();


        StudentDO studentDO = studentService.get(Integer.parseInt(studentId));
        Assert.isTrue(studentDO != null, "学员不存在");

        AdminUserDO user = adminUserService.getUser(Long.parseLong(teacherCode));
        Assert.isTrue(user != null, "老师不存在");


        CourseClassDO courseClassDO = courseClassService.getCourseClass(Integer.parseInt(costCourseClass));
        Assert.isTrue(courseClassDO != null, "所选班级不存在");

        Date costCourseDate = createReqVO.getCostCourseDate();
        String format = DateUtil.format(costCourseDate, "yyyy-MM-dd");
        int dayOfWeek = DateUtil.dayOfWeek(costCourseDate);
        String[] arrDate = format.split("-");
        String courseRecordCode = arrDate[0] + arrDate[1] + arrDate[2] + dayOfWeek + courseClassDO.getTeacherCode() + courseClassDO.getClassDicValue() + courseClassDO.getClassTimeDicValue();


        CostClassRecordCreateReqVO costClassReqVO = new CostClassRecordCreateReqVO();
        costClassReqVO.setCostClassType(0);
        costClassReqVO.setHandFlag(1);
        costClassReqVO.setClassCode(courseClassDO.getClassCode());
        costClassReqVO.setCostTeacherCode(user.getId().toString());
        costClassReqVO.setCostTeacherName(user.getNickname());
        costClassReqVO.setCourseContentCode(createReqVO.getCostCourseContent());
        costClassReqVO.setStudentCode(studentDO.getId().toString());
        costClassReqVO.setStudentName(studentDO.getName());
        costClassReqVO.setCourseCode(courseClassDO.getCourseCode());
        costClassReqVO.setCourseRecordCode(courseRecordCode);

        createCostClassRecord(costClassReqVO);

        return null;
    }

    @Override
    public void updateCostClassRecord(CostClassRecordUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateCostClassRecordExists(updateReqVO.getId());
        // 更新
        CostClassRecordDO updateObj = CostClassRecordConvert.INSTANCE.convert(updateReqVO);
        costClassRecordMapper.updateById(updateObj);
    }

    @Override
    public void deleteCostClassRecord(Integer id) {
        // 校验存在
        this.validateCostClassRecordExists(id);
        // 删除
        costClassRecordMapper.deleteById(id);
    }

    private void validateCostClassRecordExists(Integer id) {
        if (costClassRecordMapper.selectById(id) == null) {
            throw exception(COST_CLASS_RECORD_NOT_EXISTS);
        }
    }

    @Override
    public CostClassRecordDO getCostClassRecord(Integer id) {
        return costClassRecordMapper.selectById(id);
    }

    @Override
    public List<CostClassRecordDO> getCostClassRecordList(Collection<Integer> ids) {
        return costClassRecordMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<CostClassRecordDO> getCostClassRecordPage(CostClassRecordPageReqVO pageReqVO) {
        return costClassRecordMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CostClassRecordDO> getCostClassRecordList(CostClassRecordExportReqVO exportReqVO) {
        return costClassRecordMapper.selectList(exportReqVO);
    }

}
