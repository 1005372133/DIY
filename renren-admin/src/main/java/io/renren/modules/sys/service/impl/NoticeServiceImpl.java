package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.NoticeDao;
import io.renren.modules.sys.entity.NoticeEntity;
import io.renren.modules.sys.service.NoticeService;


@Service("noticeService")
public class NoticeServiceImpl extends ServiceImpl<NoticeDao, NoticeEntity> implements NoticeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<NoticeEntity> page = this.selectPage(
                new Query<NoticeEntity>(params).getPage(),
                new EntityWrapper<NoticeEntity>()
        );

        return new PageUtils(page);
    }

}
