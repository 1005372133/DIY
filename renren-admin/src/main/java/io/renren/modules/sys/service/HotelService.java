package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.HotelEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-03-06 20:42:41
 */
public interface HotelService extends IService<HotelEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

