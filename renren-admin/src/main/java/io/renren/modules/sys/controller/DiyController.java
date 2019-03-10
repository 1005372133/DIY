package io.renren.modules.sys.controller;

import io.renren.common.utils.R;
import io.renren.modules.sys.entity.DiyEntity;
import io.renren.modules.sys.service.DiyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping ("/save")
    @ApiOperation(value = "收藏")
    public R save(@RequestBody DiyEntity diyEntity){
        diyService.add(diyEntity);
        return R.ok();
    }
}
