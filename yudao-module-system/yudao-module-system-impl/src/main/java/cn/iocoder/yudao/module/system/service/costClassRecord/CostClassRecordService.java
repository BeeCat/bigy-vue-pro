package cn.iocoder.yudao.module.system.service.costClassRecord;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.costClassRecord.vo.CostClassRecordCreateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.costClassRecord.vo.CostClassRecordExportReqVO;
import cn.iocoder.yudao.module.system.controller.admin.costClassRecord.vo.CostClassRecordPageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.costClassRecord.vo.CostClassRecordUpdateReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.costClassRecord.CostClassRecordDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 消课记录 Service 接口
 *
 * @author 毕国勇
 */
public interface CostClassRecordService {

    /**
     * 创建消课记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createCostClassRecord(@Valid CostClassRecordCreateReqVO createReqVO);

    /**
     * 更新消课记录
     *
     * @param updateReqVO 更新信息
     */
    void updateCostClassRecord(@Valid CostClassRecordUpdateReqVO updateReqVO);

    /**
     * 删除消课记录
     *
     * @param id 编号
     */
    void deleteCostClassRecord(Integer id);

    /**
     * 获得消课记录
     *
     * @param id 编号
     * @return 消课记录
     */
    CostClassRecordDO getCostClassRecord(Integer id);

    /**
     * 获得消课记录列表
     *
     * @param ids 编号
     * @return 消课记录列表
     */
    List<CostClassRecordDO> getCostClassRecordList(Collection<Integer> ids);

    /**
     * 获得消课记录分页
     *
     * @param pageReqVO 分页查询
     * @return 消课记录分页
     */
    PageResult<CostClassRecordDO> getCostClassRecordPage(CostClassRecordPageReqVO pageReqVO);

    /**
     * 获得消课记录列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 消课记录列表
     */
    List<CostClassRecordDO> getCostClassRecordList(CostClassRecordExportReqVO exportReqVO);

}
