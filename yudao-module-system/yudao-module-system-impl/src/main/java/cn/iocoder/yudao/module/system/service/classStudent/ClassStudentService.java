package cn.iocoder.yudao.module.system.service.classStudent;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.classStudent.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.classStudent.ClassStudentDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 班级学生 Service 接口
 *
 * @author 毕国勇
 */
public interface ClassStudentService {

    /**
     * 创建班级学生
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createClassStudent(@Valid ClassStudentCreateReqVO createReqVO);

    /**
     * 更新班级学生
     *
     * @param updateReqVO 更新信息
     */
    void updateClassStudent(@Valid ClassStudentUpdateReqVO updateReqVO);

    /**
     * 删除班级学生
     *
     * @param id 编号
     */
    void deleteClassStudent(Integer id);

    /**
     * 获得班级学生
     *
     * @param id 编号
     * @return 班级学生
     */
    ClassStudentDO getClassStudent(Integer id);

    /**
     * 获得班级学生列表
     *
     * @param ids 编号
     * @return 班级学生列表
     */
    List<ClassStudentDO> getClassStudentList(Collection<Integer> ids);

    /**
     * 获得班级学生分页
     *
     * @param pageReqVO 分页查询
     * @return 班级学生分页
     */
    PageResult<ClassStudentRespVO> getClassStudentPage(ClassStudentPageReqVO pageReqVO);

    /**
     * 获得班级学生列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 班级学生列表
     */
    List<ClassStudentDO> getClassStudentList(ClassStudentExportReqVO exportReqVO);

    PageResult<ClassStudentRespVO> getClassStudentAccountPage(ClassStudentPageReqVO pageVO);
}
