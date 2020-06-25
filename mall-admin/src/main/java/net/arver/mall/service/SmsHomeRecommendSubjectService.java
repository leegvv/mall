package net.arver.mall.service;

import net.arver.mall.model.SmsHomeRecommendSubject;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 首页专题推荐管理Service
 */
public interface SmsHomeRecommendSubjectService {

    /**
     * 添加首页推荐.
     * @param recommendSubjectList
     * @return
     */
    @Transactional
    int create(List<SmsHomeRecommendSubject> recommendSubjectList);

    /**
     * 修改推荐排序
     * @param id
     * @param sort
     * @return
     */
    int updateSort(Long id, Integer sort);

    /**
     * 批量删除推荐
     * @param ids
     * @return
     */
    int delete(List<Long> ids);

    /**
     * 更新推荐状态
     * @param ids
     * @param recommendStatus
     * @return
     */
    int updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    /**
     * 分页查询推荐
     * @param subjectName
     * @param recommendStatus
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<SmsHomeRecommendSubject> list(String subjectName, Integer recommendStatus, Integer pageNum, Integer pageSize);

}
