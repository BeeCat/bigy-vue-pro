package cn.iocoder.yudao.module.system.service.costClassRecord;

import cn.hutool.core.collection.CollectionUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.module.system.controller.admin.costClassRecord.vo.CostClassRecordCreateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.costClassRecord.vo.CostClassRecordExportReqVO;
import cn.iocoder.yudao.module.system.controller.admin.costClassRecord.vo.CostClassRecordPageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.costClassRecord.vo.CostClassRecordUpdateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.courseClass.vo.CourseClassExportReqVO;
import cn.iocoder.yudao.module.system.convert.costClassRecord.CostClassRecordConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.costClassRecord.CostClassRecordDO;
import cn.iocoder.yudao.module.system.dal.dataobject.courseClass.CourseClassDO;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.dal.mysql.costClassRecord.CostClassRecordMapper;
import cn.iocoder.yudao.module.system.service.courseClass.CourseClassService;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import com.google.common.base.Preconditions;
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

    /**
     *
     */
    @Resource
    private AdminUserService adminUserService;


    @Override
    public Integer createCostClassRecord(CostClassRecordCreateReqVO createReqVO) {

        String courseRecordCode = createReqVO.getCourseRecordCode();
        String studentCode = createReqVO.getStudentCode();
        CostClassRecordPageReqVO reqVO = new CostClassRecordPageReqVO();
        reqVO.setCourseRecordCode(courseRecordCode);
        reqVO.setStudentCode(studentCode);
        PageResult<CostClassRecordDO> costClassRecordPage = getCostClassRecordPage(reqVO);
        Preconditions.checkArgument(CollectionUtil.isEmpty(costClassRecordPage.getList()), "请勿重复考勤");

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
