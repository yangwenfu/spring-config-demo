package com.example.zidongcreatecode.AutoCreateCode.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;

/**
 * <p>
 * 
 * </p>
 *
 * @author Yang
 * @since 2018-01-31
 */
public class User implements Serializable{

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	private String name;
	private String mobile;
	private Integer age;
	private String address;
	@TableField("create_time")
	private Date createTime;
	@TableField("last_update_time")
	private Date lastUpdateTime;
	private Date time;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}



	@Override
	public String toString() {
		return "User{" +
			", id=" + id +
			", name=" + name +
			", mobile=" + mobile +
			", age=" + age +
			", address=" + address +
			", createTime=" + createTime +
			", lastUpdateTime=" + lastUpdateTime +
			", time=" + time +
			"}";
	}
}
