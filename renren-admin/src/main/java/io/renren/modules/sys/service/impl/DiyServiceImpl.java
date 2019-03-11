package io.renren.modules.sys.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.LoginUserUtil;
import io.renren.modules.sys.dao.DiyDao;
import io.renren.modules.sys.entity.DiyEntity;
import io.renren.modules.sys.service.DiyService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
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
    public void add(String Viewid) {
        DiyEntity diyEntity=new DiyEntity();
        diyEntity.setId(UUID.randomUUID().toString().substring(0,8));
        diyEntity.setUserId(LoginUserUtil.getUserId());
        diyEntity.setViewId(Viewid);
                diyDao.add(diyEntity);
    }
}
