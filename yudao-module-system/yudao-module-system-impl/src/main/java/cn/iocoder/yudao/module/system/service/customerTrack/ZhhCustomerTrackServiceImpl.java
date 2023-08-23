package cn.iocoder.yudao.module.system.service.customerTrack;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.classStudent.vo.ClassStudentCreateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.customer.vo.ZhhCustomerUpdateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.customerTrack.vo.*;
import cn.iocoder.yudao.module.system.convert.customerTrack.ZhhCustomerTrackConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.courseClass.CourseClassDO;
import cn.iocoder.yudao.module.system.dal.dataobject.customer.ZhhCustomerDO;
import cn.iocoder.yudao.module.system.dal.dataobject.customerTrack.ZhhCustomerTrackDO;
import cn.iocoder.yudao.module.system.dal.dataobject.student.StudentDO;
import cn.iocoder.yudao.module.system.dal.mysql.customerTrack.ZhhCustomerTrackMapper;
import cn.iocoder.yudao.module.system.service.classStudent.ClassStudentService;
import cn.iocoder.yudao.module.system.service.courseClass.CourseClassService;
import cn.iocoder.yudao.module.system.service.customer.ZhhCustomerService;
import cn.iocoder.yudao.module.system.service.student.StudentService;
import com.google.common.base.Preconditions;
import jodd.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.CUSTOMER_TRACK_NOT_EXISTS;

/**
 *  Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ZhhCustomerTrackServiceImpl implements ZhhCustomerTrackService {

    @Resource
    private ZhhCustomerTrackMapper customerTrackMapper;

    @Resource
    private ZhhCustomerService customerService;

    @Resource
    private CourseClassService courseClassService;

    @Resource
    private ClassStudentService classStudentService;
    
    @Resource
    private StudentService studentService;

    @Override
    public Integer createCustomerTrack(ZhhCustomerTrackCreateReqVO createReqVO) {
        // 插入
        ZhhCustomerTrackDO customerTrack = ZhhCustomerTrackConvert.INSTANCE.convert(createReqVO);

        Integer customerId = customerTrack.getCustomerId();
        ZhhCustomerDO customer = customerService.getCustomer(customerId);
        Preconditions.checkArgument(customer != null);
        String gradeType = customer.getGradeType();
        customerTrack.setBeforeCustomerGrade(gradeType);
        String afterCustomerGrade = customerTrack.getAfterCustomerGrade();
        int gradeTypeChange = StringUtil.equals(gradeType, afterCustomerGrade) ? 0 : 1;
        customerTrack.setGradeChangeFlag(gradeTypeChange);
        if (gradeTypeChange == 1) {
            customer.setGradeType(afterCustomerGrade);
            ZhhCustomerUpdateReqVO customerVo = new ZhhCustomerUpdateReqVO();
            customerVo.setId(customer.getId());
            customerVo.setGradeType(afterCustomerGrade);
            customerService.updateCustomer(customerVo);
        }

        customerTrackMapper.insert(customerTrack);
        // 返回
        return customerTrack.getId();
    }

    @Override
    public void updateCustomerTrack(ZhhCustomerTrackUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateCustomerTrackExists(updateReqVO.getId());
        // 更新
        ZhhCustomerTrackDO updateObj = ZhhCustomerTrackConvert.INSTANCE.convert(updateReqVO);
        customerTrackMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerTrack(Integer id) {
        // 校验存在
        this.validateCustomerTrackExists(id);
        // 删除
        customerTrackMapper.deleteById(id);
    }

    private void validateCustomerTrackExists(Integer id) {
        if (customerTrackMapper.selectById(id) == null) {
            throw exception(CUSTOMER_TRACK_NOT_EXISTS);
        }
    }

    @Override
    public ZhhCustomerTrackDO getCustomerTrack(Integer id) {
        return customerTrackMapper.selectById(id);
    }

    @Override
    public List<ZhhCustomerTrackDO> getCustomerTrackList(Collection<Integer> ids) {
        return customerTrackMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ZhhCustomerTrackDO> getCustomerTrackPage(ZhhCustomerTrackPageReqVO pageReqVO) {
        return customerTrackMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ZhhCustomerTrackDO> getCustomerTrackList(ZhhCustomerTrackExportReqVO exportReqVO) {
        return customerTrackMapper.selectList(exportReqVO);
    }

    @Override
    public Integer createCustomerInvite(ZhhCustomerInviteCreateReqVO createReqVO) {
        Integer customerId = createReqVO.getCustomerId();
        String inviteTeacher = createReqVO.getInviteTeacher();
        String inviteClass = createReqVO.getInviteClass();
        String trackTeacher = createReqVO.getTrackTeacher();

        CourseClassDO courseClass = courseClassService.getCourseClass(Integer.parseInt(inviteClass));
        StudentDO studentDO = studentService.getByCustomer(customerId);

        ClassStudentCreateReqVO classStudentCreateReqVO = new ClassStudentCreateReqVO();
        classStudentCreateReqVO.setClassCode(courseClass.getClassCode());
        classStudentCreateReqVO.setCourseCode(courseClass.getCourseCode());
        classStudentCreateReqVO.setStudentCode(String.valueOf(studentDO.getId()));
        classStudentCreateReqVO.setStudentName(studentDO.getName());
        classStudentCreateReqVO.setStageType("试听");
        classStudentCreateReqVO.setValidateTime(createReqVO.getInviteTime());
        classStudentService.createClassStudent(classStudentCreateReqVO);

        return null;
    }

}
