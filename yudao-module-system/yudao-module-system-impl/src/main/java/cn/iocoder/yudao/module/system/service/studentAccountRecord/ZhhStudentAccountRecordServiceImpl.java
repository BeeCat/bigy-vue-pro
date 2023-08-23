package cn.iocoder.yudao.module.system.service.studentAccountRecord;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.studentAccountRecord.vo.ZhhStudentAccountRecordCreateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.studentAccountRecord.vo.ZhhStudentAccountRecordExportReqVO;
import cn.iocoder.yudao.module.system.controller.admin.studentAccountRecord.vo.ZhhStudentAccountRecordPageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.studentAccountRecord.vo.ZhhStudentAccountRecordUpdateReqVO;
import cn.iocoder.yudao.module.system.convert.studentAccountRecord.ZhhStudentAccountRecordConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.studentAccountRecord.ZhhStudentAccountRecordDO;
import cn.iocoder.yudao.module.system.dal.mysql.studentAccountRecord.ZhhStudentAccountRecordMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.STUDENT_ACCOUNT_RECORD_NOT_EXISTS;

/**
 * 学生账户 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ZhhStudentAccountRecordServiceImpl implements ZhhStudentAccountRecordService {

    @Resource
    private ZhhStudentAccountRecordMapper studentAccountRecordMapper;

    @Override
    public Integer createStudentAccountRecord(ZhhStudentAccountRecordCreateReqVO createReqVO) {
        // 插入
        ZhhStudentAccountRecordDO studentAccountRecord = ZhhStudentAccountRecordConvert.INSTANCE.convert(createReqVO);
        studentAccountRecordMapper.insert(studentAccountRecord);
        // 返回
        return studentAccountRecord.getId();
    }

    @Override
    public void updateStudentAccountRecord(ZhhStudentAccountRecordUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateStudentAccountRecordExists(updateReqVO.getId());
        // 更新
        ZhhStudentAccountRecordDO updateObj = ZhhStudentAccountRecordConvert.INSTANCE.convert(updateReqVO);
        studentAccountRecordMapper.updateById(updateObj);
    }

    @Override
    public void deleteStudentAccountRecord(Integer id) {
        // 校验存在
        this.validateStudentAccountRecordExists(id);
        // 删除
        studentAccountRecordMapper.deleteById(id);
    }

    private void validateStudentAccountRecordExists(Integer id) {
        if (studentAccountRecordMapper.selectById(id) == null) {
            throw exception(STUDENT_ACCOUNT_RECORD_NOT_EXISTS);
        }
    }

    @Override
    public ZhhStudentAccountRecordDO getStudentAccountRecord(Integer id) {
        return studentAccountRecordMapper.selectById(id);
    }

    @Override
    public List<ZhhStudentAccountRecordDO> getStudentAccountRecordList(Collection<Integer> ids) {
        return studentAccountRecordMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ZhhStudentAccountRecordDO> getStudentAccountRecordPage(ZhhStudentAccountRecordPageReqVO pageReqVO) {
        return studentAccountRecordMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ZhhStudentAccountRecordDO> getStudentAccountRecordList(ZhhStudentAccountRecordExportReqVO exportReqVO) {
        return studentAccountRecordMapper.selectList(exportReqVO);
    }

}
