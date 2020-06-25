package net.arver.mall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import net.arver.mall.mapper.SmsHomeAdvertiseMapper;
import net.arver.mall.model.SmsHomeAdvertise;
import net.arver.mall.model.SmsHomeAdvertiseExample;
import net.arver.mall.service.SmsHomeAdvertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 首页广告管理Service实现类
 */
@Service
public class SmsHomeAdvertiseServiceImpl implements SmsHomeAdvertiseService {

    @Autowired
    private SmsHomeAdvertiseMapper advertiseMapper;

    @Override
    public int create(final SmsHomeAdvertise advertise) {
        advertise.setClickCount(0);
        advertise.setOrderCount(0);
        return advertiseMapper.insert(advertise);
    }

    @Override
    public int update(final Long id, final SmsHomeAdvertise advertise) {
        advertise.setId(id);
        return advertiseMapper.updateByPrimaryKeySelective(advertise);
    }

    @Override
    public int delete(final List<Long> ids) {
        final SmsHomeAdvertiseExample example = new SmsHomeAdvertiseExample();
        example.createCriteria().andIdIn(ids);
        return advertiseMapper.deleteByExample(example);
    }

    @Override
    public int updateStatus(final Long id, final Integer status) {
        final SmsHomeAdvertise advertise = new SmsHomeAdvertise();
        advertise.setId(id);
        advertise.setStatus(status);
        return advertiseMapper.updateByPrimaryKeySelective(advertise);
    }

    @Override
    public SmsHomeAdvertise getItem(final Long id) {
        return advertiseMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SmsHomeAdvertise> list(final String name, final Integer type, final String endTime, final Integer pageNum, final Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        final SmsHomeAdvertiseExample example = new SmsHomeAdvertiseExample();
        final SmsHomeAdvertiseExample.Criteria criteria = example.createCriteria();
        if (StrUtil.isNotEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        if (type != null) {
            criteria.andTypeEqualTo(type);
        }
        if (StrUtil.isNotEmpty(endTime)) {
            String startStr = endTime + " 00:00:00";
            String endStr = endTime + " 23:59:59";
            final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start = null;
            Date end = null;
            try {
                start = sdf.parse(startStr);
                end = sdf.parse(endStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (start != null && end != null) {
                criteria.andEndTimeBetween(start, end);
            }
        }
        example.setOrderByClause("sort desc");
        return advertiseMapper.selectByExample(example);
    }
}
