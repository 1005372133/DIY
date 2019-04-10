package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.ViewNoticeEntity;
import io.renren.modules.sys.service.ViewNoticeService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-04-10 13:45:49
 */
@RestController
@RequestMapping("sys/viewnotice")
public class ViewNoticeController {
    @Autowired
    private ViewNoticeService viewNoticeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:viewnotice:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = viewNoticeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:viewnotice:info")
    public R info(@PathVariable("id") Integer id){
        ViewNoticeEntity viewNotice = viewNoticeService.selectById(id);

        return R.ok().put("viewNotice", viewNotice);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:viewnotice:save")
    public R save(@RequestBody ViewNoticeEntity viewNotice){
        viewNoticeService.insert(viewNotice);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:viewnotice:update")
    public R update(@RequestBody ViewNoticeEntity viewNotice){
        ValidatorUtils.validateEntity(viewNotice);
        viewNoticeService.updateAllColumnById(viewNotice);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:viewnotice:delete")
    public R delete(@RequestBody Integer[] ids){
        viewNoticeService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
