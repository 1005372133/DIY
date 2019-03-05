package io.renren.modules.lesson.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-01-23 16:48:35
 */
@TableName("car_log")
public class CarLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private String id;
	/**
	 * 
	 */
	private String carName;
	/**
	 * 
	 */
	private String carType;
	/**
	 * 
	 */
	private Date startTime;
	/**
	 * 
	 */
	private Date endTime;
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
	public void setCarName(String carName) {
		this.carName = carName;
	}
	/**
	 * 获取：
	 */
	public String getCarName() {
		return carName;
	}
	/**
	 * 设置：
	 */
	public void setCarType(String carType) {
		this.carType = carType;
	}
	/**
	 * 获取：
	 */
	public String getCarType() {
		return carType;
	}
	/**
	 * 设置：
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * 设置：
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：
	 */
	public Date getEndTime() {
		return endTime;
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
