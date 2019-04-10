package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-04-10 13:45:49
 */
@TableName("view_notice")
public class ViewNoticeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private Integer viewId;
	/**
	 * 
	 */
	private Integer noticeId;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setViewId(Integer viewId) {
		this.viewId = viewId;
	}
	/**
	 * 获取：
	 */
	public Integer getViewId() {
		return viewId;
	}
	/**
	 * 设置：
	 */
	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}
	/**
	 * 获取：
	 */
	public Integer getNoticeId() {
		return noticeId;
	}
}
