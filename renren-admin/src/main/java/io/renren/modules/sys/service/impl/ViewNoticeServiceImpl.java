package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.ViewNoticeDao;
import io.renren.modules.sys.entity.ViewNoticeEntity;
import io.renren.modules.sys.service.ViewNoticeService;


@Service("viewNoticeService")
public class ViewNoticeServiceImpl extends ServiceImpl<ViewNoticeDao, ViewNoticeEntity> implements ViewNoticeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ViewNoticeEntity> page = this.selectPage(
                new Query<ViewNoticeEntity>(params).getPage(),
                new EntityWrapper<ViewNoticeEntity>()
        );

        return new PageUtils(page);
    }

}
