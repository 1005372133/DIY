package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.DiyEntity;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Administrator
 * @Date: 2019/3/10 15:51
 * @Description:
 */
public interface DiyService extends IService<DiyEntity> {

    Boolean add(String  Viewid);

    void deleteId(String id);

    List<DiyEntity> selectByDiy(String userId);


    PageUtils queryPage(Map<String,Object> params);
}
