package io.renren.modules.lesson.dao;

import io.renren.modules.lesson.entity.CarLogEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-01-23 16:48:35
 */
public interface CarLogDao extends BaseMapper<CarLogEntity> {

    int insertSelective(CarLogEntity carLogEntity);
}
