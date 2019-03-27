package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.ViewEntity;
import io.renren.modules.sys.service.ViewService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-03-06 19:44:52
 */
@RestController
@RequestMapping("sys/view")
public class ViewController {
    @Autowired
    private ViewService viewService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:view:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = viewService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("sys:view:info")
    public R info(@PathVariable("id") Integer id){
        ViewEntity view = viewService.selectById(id);

        return R.ok().put("view", view);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:view:save")
    public R save(@RequestBody ViewEntity view){
        viewService.insert(view);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:view:update")
    public R update(@RequestBody ViewEntity view){
        ValidatorUtils.validateEntity(view);
        viewService.updateAllColumnById(view);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:view:delete")
    public R delete(@RequestBody Integer[] ids){
        viewService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
