package io.renren.modules.sys.controller;

import io.renren.common.utils.LoginUserUtil;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.service.DiyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Auther: Administrator
 * @Date: 2019/3/10 15:49
 * @Description:
 */

@RestController
@RequestMapping("sys/diy")
@Api("diy")
public class DiyController {


    @Autowired
    private DiyService diyService;
    /**
     * 保存
     */
    @PostMapping ("{Viewid}")
    @ApiOperation(value = "收藏")
    public R save(@PathVariable String Viewid ){
       if (diyService.add(Viewid)){
           return R.ok();
       }
        else {
           return R.error(1,"您已经收藏过了");
       }
    }


    /**
     * 保存
     */
    @PostMapping ("saveweb")
    @ApiOperation(value = "收藏")
    public R saveweb(String rid ){
        if (diyService.add(rid)){
            return R.ok();
        }
        else {
            return R.error(1,"您已经收藏过了");
        }
    }


    @PostMapping ("delete/{id}")
    @ApiOperation(value = "取消收藏")
    public R delete(@PathVariable String id ){
        diyService.deleteId(id);
        return R.ok();
    }



    @GetMapping ("/selectByDiy")
    @ApiOperation(value = "查找收藏")
    public R selectByDiy(@RequestParam Map<String, Object> params){
        PageUtils page = diyService.queryPage(params);
        if ((LoginUserUtil.getUserId())==null){
            return null;
        }
        page.setList(diyService.selectByDiy(LoginUserUtil.getUserId()));
        return R.ok().put("page",page);
    }
}
