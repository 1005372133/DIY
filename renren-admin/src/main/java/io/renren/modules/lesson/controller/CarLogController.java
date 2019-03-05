package io.renren.modules.lesson.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.lesson.entity.CarLogEntity;
import io.renren.modules.lesson.service.CarLogService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-01-23 16:48:35
 */
@RestController
@RequestMapping("sys/carlog")
public class CarLogController {
    @Autowired
    private CarLogService carLogService;

    /**
     * 列表
     */
    @RequestMapping("/list")
   // @RequiresPermissions("sys:carlog:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = carLogService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
   // @RequiresPermissions("sys:carlog:info")
    public R info(@PathVariable("id") String id){
        CarLogEntity carLog = carLogService.selectById(id);

        return R.ok().put("carLog", carLog);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
   // @RequiresPermissions("sys:carlog:save")
    public R save(@RequestBody CarLogEntity carLog){
        carLogService.insert(carLog);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
   // @RequiresPermissions("sys:carlog:update")
    public R update(@RequestBody CarLogEntity carLog){
        ValidatorUtils.validateEntity(carLog);
        carLogService.updateAllColumnById(carLog);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
   // @RequiresPermissions("sys:carlog:delete")
    public R delete(@RequestBody String[] ids){
        carLogService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    @PostMapping("/insertSelective")
    //@RequiresPermissions("sys:prolesson:save")
    @ApiOperation("添加")
    @ResponseBody
    public R insertSelective(@RequestBody CarLogEntity carLogEntity){
        carLogService.insertSelective(carLogEntity);
        return R.ok("新增成功");
    }

}
