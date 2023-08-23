package cn.iocoder.yudao.module.system.dal.mysql.student;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.system.controller.admin.student.vo.StudentExportReqVO;
import cn.iocoder.yudao.module.system.controller.admin.student.vo.StudentPageReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.student.StudentDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 学员管理 Mapper
 *
 * @author 毕国勇
 */
@Mapper
public interface StudentMapper extends BaseMapperX<StudentDO> {

    default PageResult<StudentDO> selectPage(StudentPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<StudentDO>()
                .likeIfPresent(StudentDO::getName, reqVO.getName())
                .eqIfPresent(StudentDO::getAge, reqVO.getAge())
                .eqIfPresent(StudentDO::getTitle, reqVO.getTitle())
                .eqIfPresent(StudentDO::getBirthDay, reqVO.getBirthDay())
                .eqIfPresent(StudentDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(StudentDO::getStageType, reqVO.getStageType())
                .eqIfPresent(StudentDO::getAddress, reqVO.getAddress())
                .betweenIfPresent(StudentDO::getOfferDate, reqVO.getBeginOfferDate(), reqVO.getEndOfferDate())
                .likeIfPresent(StudentDO::getFatherName, reqVO.getFatherName())
                .likeIfPresent(StudentDO::getMotherName, reqVO.getMotherName())
                .eqIfPresent(StudentDO::getFatherMobile, reqVO.getFatherMobile())
                .eqIfPresent(StudentDO::getMotherMobile, reqVO.getMotherMobile())
                .eqIfPresent(StudentDO::getSex, reqVO.getSex())
                .eqIfPresent(StudentDO::getDeleted, reqVO.getDeleted())
                .orderByDesc(StudentDO::getId));
    }

    default List<StudentDO> selectList(StudentExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<StudentDO>()
                .likeIfPresent(StudentDO::getName, reqVO.getName())
                .eqIfPresent(StudentDO::getAge, reqVO.getAge())
                .eqIfPresent(StudentDO::getTitle, reqVO.getTitle())
                .eqIfPresent(StudentDO::getBirthDay, reqVO.getBirthDay())
                .eqIfPresent(StudentDO::getStageType, reqVO.getStageType())
                .eqIfPresent(StudentDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(StudentDO::getAddress, reqVO.getAddress())
                .betweenIfPresent(StudentDO::getOfferDate, reqVO.getBeginOfferDate(), reqVO.getEndOfferDate())
                .likeIfPresent(StudentDO::getFatherName, reqVO.getFatherName())
                .likeIfPresent(StudentDO::getMotherName, reqVO.getMotherName())
                .eqIfPresent(StudentDO::getFatherMobile, reqVO.getFatherMobile())
                .eqIfPresent(StudentDO::getMotherMobile, reqVO.getMotherMobile())
                .eqIfPresent(StudentDO::getSex, reqVO.getSex())
                .eqIfPresent(StudentDO::getDeleted, reqVO.getDeleted())
                .orderByDesc(StudentDO::getId));
    }

}
