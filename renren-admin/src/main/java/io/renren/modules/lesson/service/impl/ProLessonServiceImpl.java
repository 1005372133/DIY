package io.renren.modules.lesson.service.impl;

import com.mysql.jdbc.log.LogUtils;
import io.renren.common.utils.Constant;
import io.renren.modules.lesson.dao.ProLessonDao;
import io.renren.modules.lesson.entity.ProLessonEntity;
import io.renren.modules.lesson.service.ProLessonService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.UUID;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;


@Service("proLessonService")
public class ProLessonServiceImpl extends ServiceImpl<ProLessonDao, ProLessonEntity> implements ProLessonService {
@Autowired
    ProLessonDao proLessonDao;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String lessonName = (String)params.get("lesson_name");
        String content = (String)params.get("content");

        Page<ProLessonEntity> page = this.selectPage(
                new Query<ProLessonEntity>(params).getPage(),
                new EntityWrapper<ProLessonEntity>()
                .like(StringUtils.isNotBlank(lessonName),"lesson_name", lessonName)
                .addFilterIfNeed(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
                        .like(StringUtils.isNotBlank(content),"content", content)
                        .addFilterIfNeed(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
        );
        return new PageUtils(page);
    }

    @Override
    public ProLessonEntity insertSelective(ProLessonEntity proLessonEntity) {
        proLessonEntity.setId(UUID.randomUUID().toString());
         proLessonDao.insertSelective(proLessonEntity);
        return proLessonEntity;
    }


}
