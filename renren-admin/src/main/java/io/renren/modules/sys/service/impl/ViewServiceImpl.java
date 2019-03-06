package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.ViewDao;
import io.renren.modules.sys.entity.ViewEntity;
import io.renren.modules.sys.service.ViewService;


@Service("viewService")
public class ViewServiceImpl extends ServiceImpl<ViewDao, ViewEntity> implements ViewService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ViewEntity> page = this.selectPage(
                new Query<ViewEntity>(params).getPage(),
                new EntityWrapper<ViewEntity>()
        );

        return new PageUtils(page);
    }

}
