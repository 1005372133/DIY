package io.renren.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;

import java.util.Map;

/**
 * @Auther: Administrator
 * @Date: 2019/3/11 19:00
 * @Description:
 */
public class LoginUserUtil {

    /**
     * 获取当前登录用户ID
     *
     */
    public static String getUserId() {
        String userId = null;
        try {
            Subject subject = SecurityUtils.getSubject();
            Object object=subject.getPrincipal();
            if(object!=null){
                String json = JSONObject.toJSONString(object);
                if(json!=null && json.length()>0){
                    Map<String, Object> userInfo=JSONObject.parseObject(json, Map.class);
                    userId=userInfo.get("userId").toString();
                }
            }
        } catch (UnavailableSecurityManagerException e) {
        } catch (InvalidSessionException e) {
        }
        return userId;
    }

    public static String getUserParamValue() {
        String paramValue = null;
        try {
            Subject subject = SecurityUtils.getSubject();
            Object object=subject.getPrincipal();
            if(object!=null){
                String json = JSONObject.toJSONString(object);
                if(json!=null && json.length()>0){
                    Map<String, Object> userInfo=JSONObject.parseObject(json, Map.class);
                    paramValue=userInfo.get("paramValue").toString();
                }
            }
        } catch (UnavailableSecurityManagerException e) {
        } catch (InvalidSessionException e) {
        }
        return paramValue;
    }


}
