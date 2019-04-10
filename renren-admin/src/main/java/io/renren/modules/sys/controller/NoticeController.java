package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import io.renren.common.utils.LoginUserUtil;
import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.NoticeEntity;
import io.renren.modules.sys.service.NoticeService;
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
@RequestMapping("sys/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
   // @RequiresPermissions("sys:notice:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = noticeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:notice:info")
    public R info(@PathVariable("id") Integer id){
        NoticeEntity notice = noticeService.selectById(id);

        return R.ok().put("notice", notice);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("sys:notice:save")
    public R save(@RequestBody NoticeEntity notice){
        notice.setTime(new Date());
        notice.setUserId(Integer.valueOf(LoginUserUtil.getUserId()));
        noticeService.insert(notice);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:notice:update")
    public R update(@RequestBody NoticeEntity notice){
        ValidatorUtils.validateEntity(notice);
        noticeService.updateAllColumnById(notice);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
  //  @RequiresPermissions("sys:notice:delete")
    public R delete(@RequestBody Integer[] ids){
        noticeService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
