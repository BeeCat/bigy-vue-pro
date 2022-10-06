package cn.iocoder.yudao.module.system.dal.mysql.classStudent;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.system.controller.admin.classStudent.vo.ClassStudentExportReqVO;
import cn.iocoder.yudao.module.system.controller.admin.classStudent.vo.ClassStudentPageReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.classStudent.ClassStudentDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 班级学生 Mapper
 *
 * @author 毕国勇
 */
@Mapper
public interface ClassStudentMapper extends BaseMapperX<ClassStudentDO> {

    default PageResult<ClassStudentDO> selectPage(ClassStudentPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ClassStudentDO>()
                .likeIfPresent(ClassStudentDO::getName, reqVO.getName())
                .eqIfPresent(ClassStudentDO::getClassCode, reqVO.getClassCode())
                .eqIfPresent(ClassStudentDO::getCourseCode, reqVO.getCourseCode())
                .eqIfPresent(ClassStudentDO::getStudentCode, reqVO.getStudentCode())
                .likeIfPresent(ClassStudentDO::getStudentName, reqVO.getStudentName())
                .betweenIfPresent(ClassStudentDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(ClassStudentDO::getId));
    }

    default List<ClassStudentDO> selectList(ClassStudentExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ClassStudentDO>()
                .likeIfPresent(ClassStudentDO::getName, reqVO.getName())
                .eqIfPresent(ClassStudentDO::getClassCode, reqVO.getClassCode())
                .eqIfPresent(ClassStudentDO::getCourseCode, reqVO.getCourseCode())
                .eqIfPresent(ClassStudentDO::getStudentCode, reqVO.getStudentCode())
                .likeIfPresent(ClassStudentDO::getStudentName, reqVO.getStudentName())
                .betweenIfPresent(ClassStudentDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(ClassStudentDO::getId));
    }

}
