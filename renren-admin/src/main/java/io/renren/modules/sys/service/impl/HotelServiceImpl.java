package io.renren.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.HotelDao;
import io.renren.modules.sys.entity.HotelEntity;
import io.renren.modules.sys.service.HotelService;


@Service("hotelService")
public class HotelServiceImpl extends ServiceImpl<HotelDao, HotelEntity> implements HotelService {

    @Autowired
    HotelDao dao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<HotelEntity> page = this.selectPage(
                new Query<HotelEntity>(params).getPage(),
                new EntityWrapper<HotelEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<HotelEntity> hotel() {

        return dao.hotel();
    }

}
