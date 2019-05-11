package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-03-06 19:44:52
 */
@TableName("view")
public class ViewEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String area;
	/**
	 * 
	 */
	private String transport;
	/**
	 * 
	 */
	private Integer price;
	/**
	 * 
	 */
	private Integer hotelId;
	/**
	 * 
	 */
	private String hotelName;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private String picture;
	/**
	 * 
	 */
	private String remarks;

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
	public void setArea(String area) {
		this.area = area;
	}
	/**
	 * 获取：
	 */
	public String getArea() {
		return area;
	}
	/**
	 * 设置：
	 */
	public void setTransport(String transport) {
		this.transport = transport;
	}
	/**
	 * 获取：
	 */
	public String getTransport() {
		return transport;
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
	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}
	/**
	 * 获取：
	 */
	public Integer getHotelId() {
		return hotelId;
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
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}
	/**
	 * 获取：
	 */
	public String getPicture() {
		return picture;
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

	@TableField(exist = false)
	private List<String> Viewlist;
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM")
	private String Time;

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public List<String> getViewlist() {
		return Viewlist;
	}

	public void setViewlist(List<String> viewlist) {
		Viewlist = viewlist;
	}
	private String picture1;
	private String picture2;
	private String picture3;
	private String line;
	private String line2;

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getPicture1() {
		return picture1;
	}

	public void setPicture1(String picture1) {
		this.picture1 = picture1;
	}

	public String getPicture2() {
		return picture2;
	}

	public void setPicture2(String picture2) {
		this.picture2 = picture2;
	}

	public String getPicture3() {
		return picture3;
	}

	public void setPicture3(String picture3) {
		this.picture3 = picture3;
	}
}
