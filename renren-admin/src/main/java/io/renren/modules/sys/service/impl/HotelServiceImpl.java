package io.renren.modules.sys.service.impl;

import io.renren.common.utils.Constant;
import org.apache.commons.lang.StringUtils;
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
        String hotelName = (String)params.get("hotel_name");
        String hotelType = (String)params.get("hotel_type");
        Page<HotelEntity> page = this.selectPage(
                new Query<HotelEntity>(params).getPage(),
                new EntityWrapper<HotelEntity>()
                        .like(StringUtils.isNotBlank(hotelName),"hotel_name", hotelName)
                        .addFilterIfNeed(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
                        .like(StringUtils.isNotBlank(hotelType),"hotel_type", hotelType)
                        .addFilterIfNeed(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
        );

        return new PageUtils(page);
    }

    @Override
    public List<HotelEntity> hotel() {

        return dao.hotel();
    }

}
