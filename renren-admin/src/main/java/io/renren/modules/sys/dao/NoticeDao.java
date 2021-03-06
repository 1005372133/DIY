package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.NoticeEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-04-10 13:45:49
 */
public interface NoticeDao extends BaseMapper<NoticeEntity> {
    List<NoticeEntity> selectByNotice(Integer ViewId);
}
