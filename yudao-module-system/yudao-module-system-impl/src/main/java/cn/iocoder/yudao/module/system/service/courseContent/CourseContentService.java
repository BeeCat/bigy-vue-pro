package cn.iocoder.yudao.module.system.service.courseContent;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.courseContent.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.courseContent.CourseContentDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 课程体系 Service 接口
 *
 * @author 毕国勇
 */
public interface CourseContentService {

    /**
     * 创建课程体系
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createCourseContent(@Valid CourseContentCreateReqVO createReqVO);

    /**
     * 更新课程体系
     *
     * @param updateReqVO 更新信息
     */
    void updateCourseContent(@Valid CourseContentUpdateReqVO updateReqVO);

    /**
     * 删除课程体系
     *
     * @param id 编号
     */
    void deleteCourseContent(Integer id);

    /**
     * 获得课程体系
     *
     * @param id 编号
     * @return 课程体系
     */
    CourseContentDO getCourseContent(Integer id);

    /**
     * 获得课程体系列表
     *
     * @param ids 编号
     * @return 课程体系列表
     */
    List<CourseContentDO> getCourseContentList(Collection<Integer> ids);

    /**
     * 获得课程体系分页
     *
     * @param pageReqVO 分页查询
     * @return 课程体系分页
     */
    PageResult<CourseContentRespVO> getCourseContentPage(CourseContentPageReqVO pageReqVO);

    /**
     * 获得课程体系列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 课程体系列表
     */
    List<CourseContentDO> getCourseContentList(CourseContentExportReqVO exportReqVO);

}
