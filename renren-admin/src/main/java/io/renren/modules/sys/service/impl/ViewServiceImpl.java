package io.renren.modules.sys.service.impl;

import io.renren.common.utils.Constant;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    ViewDao viewDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String name = (String)params.get("name");
        String price = (String)params.get("price");
        String transport = (String)params.get("transport");
        String area = (String)params.get("area");
        String time = (String)params.get("time");
        String pricemax = (String)params.get("pricemax");
        String hotelName = (String)params.get("hotelName");
        String line = (String)params.get("line");
        Page<ViewEntity> page = this.selectPage(
                new Query<ViewEntity>(params).getPage(),
                new EntityWrapper<ViewEntity>()
                        .like(StringUtils.isNotBlank(name),"name", name)
                        .addFilterIfNeed(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
                        .gt(StringUtils.isNotBlank(price),"price", price)
                        .addFilterIfNeed(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
                        .lt(StringUtils.isNotBlank(pricemax),"price", pricemax)
                        .addFilterIfNeed(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
                        .like(StringUtils.isNotBlank(area),"area", area)
                        .addFilterIfNeed(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
                        .like(StringUtils.isNotBlank(transport),"transport", transport)
                        .addFilterIfNeed(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
                        .like(StringUtils.isNotBlank(line),"line", line)
                        .addFilterIfNeed(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
                        .like(StringUtils.isNotBlank(hotelName),"hotel_name", hotelName)
                        .addFilterIfNeed(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
                        .like(StringUtils.isNotBlank(time),"time", time)
                        .addFilterIfNeed(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
        );

        return new PageUtils(page);
    }

    @Override
    public ViewEntity selectId(String id) {
        return viewDao.selectId(id);
    }

}
