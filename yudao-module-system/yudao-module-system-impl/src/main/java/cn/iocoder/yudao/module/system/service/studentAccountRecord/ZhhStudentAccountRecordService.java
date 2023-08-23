package cn.iocoder.yudao.module.system.service.studentAccountRecord;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.studentAccountRecord.vo.ZhhStudentAccountRecordCreateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.studentAccountRecord.vo.ZhhStudentAccountRecordExportReqVO;
import cn.iocoder.yudao.module.system.controller.admin.studentAccountRecord.vo.ZhhStudentAccountRecordPageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.studentAccountRecord.vo.ZhhStudentAccountRecordUpdateReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.studentAccountRecord.ZhhStudentAccountRecordDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 学生账户 Service 接口
 *
 * @author 芋道源码
 */
public interface ZhhStudentAccountRecordService {

    /**
     * 创建学生账户
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createStudentAccountRecord(@Valid ZhhStudentAccountRecordCreateReqVO createReqVO);

    /**
     * 更新学生账户
     *
     * @param updateReqVO 更新信息
     */
    void updateStudentAccountRecord(@Valid ZhhStudentAccountRecordUpdateReqVO updateReqVO);

    /**
     * 删除学生账户
     *
     * @param id 编号
     */
    void deleteStudentAccountRecord(Integer id);

    /**
     * 获得学生账户
     *
     * @param id 编号
     * @return 学生账户
     */
    ZhhStudentAccountRecordDO getStudentAccountRecord(Integer id);

    /**
     * 获得学生账户列表
     *
     * @param ids 编号
     * @return 学生账户列表
     */
    List<ZhhStudentAccountRecordDO> getStudentAccountRecordList(Collection<Integer> ids);

    /**
     * 获得学生账户分页
     *
     * @param pageReqVO 分页查询
     * @return 学生账户分页
     */
    PageResult<ZhhStudentAccountRecordDO> getStudentAccountRecordPage(ZhhStudentAccountRecordPageReqVO pageReqVO);

    /**
     * 获得学生账户列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 学生账户列表
     */
    List<ZhhStudentAccountRecordDO> getStudentAccountRecordList(ZhhStudentAccountRecordExportReqVO exportReqVO);

}
