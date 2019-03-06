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
 * @date 2019-03-06 20:42:41
 */
@TableName("hotel")
public class HotelEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String hotelName;
	/**
	 * 
	 */
	private String hotelType;
	/**
	 * 
	 */
	private Integer price;
	/**
	 * 
	 */
	private String remarks;
	/**
	 * 
	 */
	private String place;

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
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	/**
	 * 获取：
	 */
	public String getHotelName() {
		return hotelName;
	}
	/**
	 * 设置：
	 */
	public void setHotelType(String hotelType) {
		this.hotelType = hotelType;
	}
	/**
	 * 获取：
	 */
	public String getHotelType() {
		return hotelType;
	}
	/**
	 * 设置：
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}
	/**
	 * 获取：
	 */
	public Integer getPrice() {
		return price;
	}
	/**
	 * 设置：
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * 设置：
	 */
	public void setPlace(String place) {
		this.place = place;
	}
	/**
	 * 获取：
	 */
	public String getPlace() {
		return place;
	}
}
