package io.renren.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.sys.entity.DiyEntity;

/**
 * @Auther: Administrator
 * @Date: 2019/3/10 15:58
 * @Description:
 */
public interface DiyDao extends BaseMapper<DiyEntity> {
    void add(DiyEntity diyEntity);
}
