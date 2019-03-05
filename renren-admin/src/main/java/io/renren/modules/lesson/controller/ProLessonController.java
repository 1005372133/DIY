package io.renren.modules.lesson.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.lesson.entity.ProLessonEntity;
import io.renren.modules.lesson.service.ProLessonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 课程信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-01-09 22:11:15
 */
@Api("课程信息表")
@RestController
@RequestMapping("sys/prolesson")
public class ProLessonController {
    @Autowired
    private ProLessonService proLessonService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("sys:prolesson:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = proLessonService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("sys:prolesson:info")
    public R info(@PathVariable("id") String id){
        ProLessonEntity proLesson = proLessonService.selectById(id);

        return R.ok().put("proLesson", proLesson);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("sys:prolesson:save")
    public R save(@RequestBody ProLessonEntity proLesson){
        proLessonService.insert(proLesson);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
   // @RequiresPermissions("sys:prolesson:update")
    public R update(@RequestBody ProLessonEntity proLesson){
        ValidatorUtils.validateEntity(proLesson);
        proLessonService.updateAllColumnById(proLesson);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("sys:prolesson:delete")
    public R delete(@RequestBody String[] ids){
        proLessonService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    @PostMapping("/insertSelective")
    //@RequiresPermissions("sys:prolesson:save")
    @ApiOperation("添加")
    @ResponseBody
    public R insertSelective(@RequestBody ProLessonEntity proLesson){
        proLessonService.insertSelective(proLesson);
        return R.ok("新增成功");
    }

}
