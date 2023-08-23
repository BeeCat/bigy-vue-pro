package cn.iocoder.yudao.module.system.dal.mysql.customer;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.system.controller.admin.customer.vo.ZhhCustomerExportReqVO;
import cn.iocoder.yudao.module.system.controller.admin.customer.vo.ZhhCustomerPageReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.customer.ZhhCustomerDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *  Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ZhhCustomerMapper extends BaseMapperX<ZhhCustomerDO> {

    default PageResult<ZhhCustomerDO> selectPage(ZhhCustomerPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ZhhCustomerDO>()
                .likeIfPresent(ZhhCustomerDO::getName, reqVO.getName())
                .eqIfPresent(ZhhCustomerDO::getTitle, reqVO.getTitle())
                .eqIfPresent(ZhhCustomerDO::getSourceFrom, reqVO.getSourceFrom())
                .eqIfPresent(ZhhCustomerDO::getTeacherFrom, reqVO.getTeacherFrom())
                .eqIfPresent(ZhhCustomerDO::getSaleFrom, reqVO.getSaleFrom())
                .eqIfPresent(ZhhCustomerDO::getAge, reqVO.getAge())
                .eqIfPresent(ZhhCustomerDO::getBirthDay, reqVO.getBirthDay())
                .betweenIfPresent(ZhhCustomerDO::getOfferDate, reqVO.getBeginOfferDate(), reqVO.getEndOfferDate())
                .eqIfPresent(ZhhCustomerDO::getSchool, reqVO.getSchool())
                .eqIfPresent(ZhhCustomerDO::getVillage, reqVO.getVillage())
                .eqIfPresent(ZhhCustomerDO::getAddress, reqVO.getAddress())
                .likeIfPresent(ZhhCustomerDO::getFatherName, reqVO.getFatherName())
                .likeIfPresent(ZhhCustomerDO::getMotherName, reqVO.getMotherName())
                .eqIfPresent(ZhhCustomerDO::getFatherMobile, reqVO.getFatherMobile())
                .eqIfPresent(ZhhCustomerDO::getMotherMobile, reqVO.getMotherMobile())
                .eqIfPresent(ZhhCustomerDO::getGradeType, reqVO.getGradeType())
                .eqIfPresent(ZhhCustomerDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ZhhCustomerDO::getSex, reqVO.getSex())
                .betweenIfPresent(ZhhCustomerDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(ZhhCustomerDO::getId));
    }

    default List<ZhhCustomerDO> selectList(ZhhCustomerExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ZhhCustomerDO>()
                .likeIfPresent(ZhhCustomerDO::getName, reqVO.getName())
                .eqIfPresent(ZhhCustomerDO::getTitle, reqVO.getTitle())
                .eqIfPresent(ZhhCustomerDO::getSourceFrom, reqVO.getSourceFrom())
                .eqIfPresent(ZhhCustomerDO::getTeacherFrom, reqVO.getTeacherFrom())
                .eqIfPresent(ZhhCustomerDO::getSaleFrom, reqVO.getSaleFrom())
                .eqIfPresent(ZhhCustomerDO::getAge, reqVO.getAge())
                .eqIfPresent(ZhhCustomerDO::getBirthDay, reqVO.getBirthDay())
                .betweenIfPresent(ZhhCustomerDO::getOfferDate, reqVO.getBeginOfferDate(), reqVO.getEndOfferDate())
                .eqIfPresent(ZhhCustomerDO::getSchool, reqVO.getSchool())
                .eqIfPresent(ZhhCustomerDO::getVillage, reqVO.getVillage())
                .eqIfPresent(ZhhCustomerDO::getAddress, reqVO.getAddress())
                .likeIfPresent(ZhhCustomerDO::getFatherName, reqVO.getFatherName())
                .likeIfPresent(ZhhCustomerDO::getMotherName, reqVO.getMotherName())
                .eqIfPresent(ZhhCustomerDO::getFatherMobile, reqVO.getFatherMobile())
                .eqIfPresent(ZhhCustomerDO::getMotherMobile, reqVO.getMotherMobile())
                .eqIfPresent(ZhhCustomerDO::getGradeType, reqVO.getGradeType())
                .eqIfPresent(ZhhCustomerDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ZhhCustomerDO::getSex, reqVO.getSex())
                .betweenIfPresent(ZhhCustomerDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(ZhhCustomerDO::getId));
    }

}
