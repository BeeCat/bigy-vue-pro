package cn.iocoder.yudao.module.system.service.student;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.student.vo.StudentCreateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.student.vo.StudentExportReqVO;
import cn.iocoder.yudao.module.system.controller.admin.student.vo.StudentPageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.student.vo.StudentUpdateReqVO;
import cn.iocoder.yudao.module.system.convert.student.StudentConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.student.StudentDO;
import cn.iocoder.yudao.module.system.dal.mysql.student.StudentMapper;
import jodd.util.StringUtil;
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
    private StudentMapper Mapper;

    @Override
    public Integer create(StudentCreateReqVO createReqVO) {


        StudentExportReqVO vo = new StudentExportReqVO();
        vo.setName(createReqVO.getName());
        List<StudentDO> list = getList(vo);
        if (CollectionUtil.isNotEmpty(list)) {
            Assert.isTrue(StringUtil.isNotEmpty(createReqVO.getTitle()) && !createReqVO.getTitle().equals(list.get(0).getTitle()), "该学员已存在：名字和小名一致");
        }
        // 插入
        StudentDO studentDO = StudentConvert.INSTANCE.convert(createReqVO);
        Mapper.insert(studentDO);
        // 返回
        return studentDO.getId();
    }

    @Override
    public void update(StudentUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateExists(updateReqVO.getId());
        // 更新
        StudentDO updateObj = StudentConvert.INSTANCE.convert(updateReqVO);
        Mapper.updateById(updateObj);
    }

    @Override
    public void delete(Integer id) {
        // 校验存在
        this.validateExists(id);
        // 删除
        Mapper.deleteById(id);
    }

    private void validateExists(Integer id) {
        if (Mapper.selectById(id) == null) {
            throw exception(STUDENT_NOT_EXISTS);
        }
    }

    @Override
    public StudentDO get(Integer id) {
        return Mapper.selectById(id);
    }

    @Override
    public List<StudentDO> getList(Collection<Integer> ids) {
        return Mapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<StudentDO> getPage(StudentPageReqVO pageReqVO) {
        return Mapper.selectPage(pageReqVO);
    }

    @Override
    public List<StudentDO> getList(StudentExportReqVO exportReqVO) {
        return Mapper.selectList(exportReqVO);
    }

    @Override
    public StudentDO getByCustomer(Integer customerId) {
        Assert.isTrue(customerId != null, "客户id为空");
        StudentExportReqVO exportReqVO = new StudentExportReqVO();
        exportReqVO.setCustomerId(customerId);
        List<StudentDO> list = getList(exportReqVO);
        if (CollectionUtil.isNotEmpty(list)) {
            Assert.isTrue(list.size() == 1, "客户与会员数据有问题");
            return list.get(0);
        }
        return null;
    }

}
