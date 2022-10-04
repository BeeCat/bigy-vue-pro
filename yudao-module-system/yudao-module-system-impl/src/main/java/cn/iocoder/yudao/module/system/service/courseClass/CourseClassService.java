package cn.iocoder.yudao.module.system.service.courseClass;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.courseClass.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.courseClass.CourseClassDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 课程班级 Service 接口
 *
 * @author 毕国勇
 */
public interface CourseClassService {

    /**
     * 创建课程班级
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createCourseClass(@Valid CourseClassCreateReqVO createReqVO);

    /**
     * 更新课程班级
     *
     * @param updateReqVO 更新信息
     */
    void updateCourseClass(@Valid CourseClassUpdateReqVO updateReqVO);

    /**
     * 删除课程班级
     *
     * @param id 编号
     */
    void deleteCourseClass(Integer id);

    /**
     * 获得课程班级
     *
     * @param id 编号
     * @return 课程班级
     */
    CourseClassDO getCourseClass(Integer id);

    /**
     * 获得课程班级列表
     *
     * @param ids 编号
     * @return 课程班级列表
     */
    List<CourseClassDO> getCourseClassList(Collection<Integer> ids);

    /**
     * 获得课程班级分页
     *
     * @param pageReqVO 分页查询
     * @return 课程班级分页
     */
    PageResult<CourseClassRespVO> getCourseClassPage(CourseClassPageReqVO pageReqVO);

    /**
     * 获得课程班级列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 课程班级列表
     */
    List<CourseClassDO> getCourseClassList(CourseClassExportReqVO exportReqVO);

}
