package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.ViewEntity;

import java.io.Serializable;
import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-03-06 19:44:52
 */
public interface ViewService extends IService<ViewEntity> {

    PageUtils queryPage(Map<String, Object> params);

    ViewEntity selectId(String id);
}

