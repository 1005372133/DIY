package io.renren.modules.sys.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.Constant;
import io.renren.common.utils.LoginUserUtil;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.sys.dao.DiyDao;
import io.renren.modules.sys.dao.ViewDao;
import io.renren.modules.sys.entity.DiyEntity;
import io.renren.modules.sys.entity.HotelEntity;
import io.renren.modules.sys.entity.ViewEntity;
import io.renren.modules.sys.service.DiyService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.View;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * @Auther: Administrator
 * @Date: 2019/3/10 15:53
 * @Description:
 */
@Service("DiyService")
public class DiyServiceImpl extends ServiceImpl<DiyDao, DiyEntity> implements DiyService {

    @Autowired
    DiyDao diyDao;
    @Autowired
    ViewDao viewDao;

    @Override
    public Boolean add(String Viewid) {
        boolean flag = true;
        /*验证是否被收藏过*/
        if (diyDao.exitsDiy(LoginUserUtil.getUserId(), Viewid).size() > 0) {
            flag = false;
            return flag;
        }
        /*没有收藏过则添加*/
        DiyEntity diyEntity = new DiyEntity();
        diyEntity.setViewId(Viewid);
        diyEntity.setUserId(LoginUserUtil.getUserId());
        diyEntity.setId(UUID.randomUUID().toString().substring(0, 8));
        diyDao.add(diyEntity);
        return flag;
    }

    @Override
    public Boolean ifFavorite(String Viewid) {
        boolean flag = true;
        if (diyDao.exitsDiy(LoginUserUtil.getUserId(), Viewid).size() > 0) {
            flag = false;
            return flag;
        } else {
            return flag;
        }
    }

    @Override
    public void deleteId(String id) {
        diyDao.deleteId(id);
    }

    @Override
    public List<DiyEntity> selectByDiy(String userId) {
        List<DiyEntity> diyEntity = new ArrayList<>();
        if (userId.equals("1")) {
            diyEntity = diyDao.selectByAdmin();
        } else {
            diyEntity = diyDao.selectByDiy(userId);//    获取所有数据
        }
        //依次查询景点
        for (int i = 0; i < diyEntity.size(); i++) {
            ViewEntity viewEntity = viewDao.selectById(diyEntity.get(i).getViewId());
            diyEntity.get(i).setViewEntity(viewEntity);            //给景点赋值
        }
        return diyEntity;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiyEntity> page = this.selectPage(
                new Query<DiyEntity>(params).getPage(),
                new EntityWrapper<DiyEntity>()
        );
        return new PageUtils(page);
    }


}
