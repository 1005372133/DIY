package io.renren.modules.lesson.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.lesson.entity.ProLessonEntity;

import java.util.Map;

/**
 * 课程信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-01-09 22:11:15
 */
public interface ProLessonService extends IService<ProLessonEntity> {

    PageUtils queryPage(Map<String, Object> params);

    ProLessonEntity insertSelective(ProLessonEntity proLessonEntity);
}

