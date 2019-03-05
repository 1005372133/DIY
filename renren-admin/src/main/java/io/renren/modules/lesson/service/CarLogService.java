package io.renren.modules.lesson.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.lesson.entity.CarLogEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-01-23 16:48:35
 */
public interface CarLogService extends IService<CarLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
    CarLogEntity insertSelective(CarLogEntity carLogEntity);
}

