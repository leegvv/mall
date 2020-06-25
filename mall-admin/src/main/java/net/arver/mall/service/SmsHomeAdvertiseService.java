package net.arver.mall.service;

import net.arver.mall.model.SmsHomeAdvertise;

import java.util.List;

/**
 * 首页广告管理Service
 */
public interface SmsHomeAdvertiseService {

    /**
     * 添加广告
     * @param advertise
     * @return
     */
    int create(SmsHomeAdvertise advertise);

    /**
     * 更新广告
     * @param id
     * @param advertise
     * @return
     */
    int update(Long id, SmsHomeAdvertise advertise);

    /**
     * 批量删除广告
     * @param ids
     * @return
     */
    int delete(List<Long> ids);

    /**
     * 修改上、下线状态
     * @param id
     * @param status
     * @return
     */
    int updateStatus(Long id, Integer status);

    /**
     * 获取广告详情
     * @param id
     * @return
     */
    SmsHomeAdvertise getItem(Long id);

    /**
     * 分页查询广告
     * @param name
     * @param type
     * @param endTime
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<SmsHomeAdvertise> list(String name, Integer type, String endTime, Integer pageNum, Integer pageSize);
}
