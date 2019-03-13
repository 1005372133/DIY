package io.renren.modules.sys.entity;

import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2019/3/10 15:47
 * @Description:
 */
public class DiyEntity {
    private String id;
    private String viewId;
    private String userId;
    private ViewEntity viewEntity;

    public ViewEntity getViewEntity() {
        return viewEntity;
    }

    public void setViewEntity(ViewEntity viewEntity) {
        this.viewEntity = viewEntity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getViewId() {
        return viewId;
    }

    public void setViewId(String viewId) {
        this.viewId = viewId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
