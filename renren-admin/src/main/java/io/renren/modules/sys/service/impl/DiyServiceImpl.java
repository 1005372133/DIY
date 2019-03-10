package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.sys.dao.DiyDao;
import io.renren.modules.sys.entity.DiyEntity;
import io.renren.modules.sys.service.DiyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


/**
 * @Auther: Administrator
 * @Date: 2019/3/10 15:53
 * @Description:
 */
@Service("DiyService")
public class DiyServiceImpl  extends ServiceImpl<DiyDao, DiyEntity> implements DiyService {

    @Autowired
    DiyDao diyDao;

    @Override
    public void add(DiyEntity diyEntity) {
                diyDao.add(diyEntity);
    }
}
