package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.modules.sys.entity.DiyEntity;

/**
 * @Auther: Administrator
 * @Date: 2019/3/10 15:51
 * @Description:
 */
public interface DiyService extends IService<DiyEntity> {

    void add(String  Viewid);

}
