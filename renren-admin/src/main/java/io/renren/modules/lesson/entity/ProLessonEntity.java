package io.renren.modules.lesson.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 课程信息表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-01-09 22:11:15
 */
@TableName("pro_lesson")
public class ProLessonEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String id;
	/**
	 * 
	 */
	private String lessonName;
	/**
	 * 
	 */
	private Date lessonTime;
	/**
	 * 
	 */
	private String content;
	/**
	 * 
	 */
	private String trainer;
	/**
	 * 
	 */
	private String teachWay;

	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}
	/**
	 * 获取：
	 */
	public String getLessonName() {
		return lessonName;
	}
	/**
	 * 设置：
	 */
	public void setLessonTime(Date lessonTime) {
		this.lessonTime = lessonTime;
	}
	/**
	 * 获取：
	 */
	public Date getLessonTime() {
		return lessonTime;
	}
	/**
	 * 设置：
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：
	 */
	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}
	/**
	 * 获取：
	 */
	public String getTrainer() {
		return trainer;
	}
	/**
	 * 设置：
	 */
	public void setTeachWay(String teachWay) {
		this.teachWay = teachWay;
	}
	/**
	 * 获取：
	 */
	public String getTeachWay() {
		return teachWay;
	}
}
