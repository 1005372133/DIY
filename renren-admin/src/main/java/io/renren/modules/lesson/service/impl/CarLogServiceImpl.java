package io.renren.modules.lesson.service.impl;

import io.renren.common.utils.Constant;
import io.renren.modules.lesson.dao.CarLogDao;
import io.renren.modules.lesson.entity.CarLogEntity;
import io.renren.modules.lesson.service.CarLogService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.UUID;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;



@Service("carLogService")
public class CarLogServiceImpl extends ServiceImpl<CarLogDao, CarLogEntity> implements CarLogService {

    @Autowired
    CarLogDao dao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String carName = (String)params.get("car_name");
        String carType = (String)params.get("car_type");
        Page<CarLogEntity> page = this.selectPage(
                new Query<CarLogEntity>(params).getPage(),
                new EntityWrapper<CarLogEntity>()
                .like(StringUtils.isNotBlank(carName),"car_name", carName)
                .addFilterIfNeed(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
                .like(StringUtils.isNotBlank(carType),"car_type", carType)
                .addFilterIfNeed(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
        );
        return new PageUtils(page);
    }

    @Override
    public CarLogEntity insertSelective(CarLogEntity carLogEntity) {
        carLogEntity.setId(UUID.randomUUID().toString());
        dao.insertSelective(carLogEntity);
        return carLogEntity;
    }

}
