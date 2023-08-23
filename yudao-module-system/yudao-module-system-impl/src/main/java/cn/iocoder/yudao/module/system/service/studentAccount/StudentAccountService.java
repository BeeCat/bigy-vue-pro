package cn.iocoder.yudao.module.system.service.studentAccount;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.studentAccount.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.studentAccount.StudentAccountDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 学生账户 Service 接口
 *
 * @author 毕国勇
 */
public interface StudentAccountService {

    /**
     * 创建学生账户
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createStudentAccount(@Valid StudentAccountCreateReqVO createReqVO);

    /**
     * 更新学生账户
     *
     * @param updateReqVO 更新信息
     */
    void updateStudentAccount(@Valid StudentAccountUpdateReqVO updateReqVO);

    /**
     * 删除学生账户
     *
     * @param id 编号
     */
    void deleteStudentAccount(Integer id);

    /**
     * 获得学生账户
     *
     * @param id 编号
     * @return 学生账户
     */
    StudentAccountDO getStudentAccount(Integer id);

    /**
     * 获得学生账户列表
     *
     * @param ids 编号
     * @return 学生账户列表
     */
    List<StudentAccountDO> getStudentAccountList(Collection<Integer> ids);

    /**
     * 获得学生账户分页
     *
     * @param pageReqVO 分页查询
     * @return 学生账户分页
     */
    PageResult<StudentAccountDO> getStudentAccountPage(StudentAccountPageReqVO pageReqVO);

    /**
     * 获得学生账户列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 学生账户列表
     */
    List<StudentAccountDO> getStudentAccountList(StudentAccountExportReqVO exportReqVO);

    void buyCourse(StudentAccountBuyReqVO accountBuyReqVO);
}
