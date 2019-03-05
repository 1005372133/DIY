package io.renren.modules.lesson.dao;

import io.renren.modules.lesson.entity.ProLessonEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 课程信息表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-01-09 22:11:15
 */
public interface ProLessonDao extends BaseMapper<ProLessonEntity> {

    int insertSelective(ProLessonEntity proLessonEntity);
}
