package io.renren.modules.sys.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.modules.sys.entity.HotelEntity;
import io.renren.modules.sys.service.HotelService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-03-06 20:42:41
 */
@RestController
@RequestMapping("sys/hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:hotel:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = hotelService.queryPage(params);

        return R.ok().put("page", page);
    }
    /**
     * 列表
     */
    @GetMapping("/hotel")
    public R hotel(){
        List<HotelEntity> page = hotelService.hotel();

        return R.ok().put("hotel", page);
    }


    /**
     * 信息//修改使用
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("sys:hotel:info")
    public R info(@PathVariable("id") Integer id){
        HotelEntity hotel = hotelService.selectById(id);

        return R.ok().put("hotel", hotel);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:hotel:save")
    public R save(@RequestBody HotelEntity hotel){
        hotelService.insert(hotel);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:hotel:update")
    public R update(@RequestBody HotelEntity hotel){
        ValidatorUtils.validateEntity(hotel);
        hotelService.updateAllColumnById(hotel);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:hotel:delete")
    public R delete(@RequestBody Integer[] ids){
        hotelService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
