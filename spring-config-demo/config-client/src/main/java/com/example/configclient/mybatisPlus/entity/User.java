package com.example.configclient.mybatisPlus.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.FieldStrategy;

/**
 *Create by yangwenfu on 2018/1/31
 */

public class User extends SuperEntity<User>{
	private String name;
	private String mobile;
	private int age;
	@TableField(value="address",fill = FieldFill.INSERT)
	private String address;
	// // 注意！这里需要标记为填充字段 否则不会起作用
	@TableField(value="create_time",fill = FieldFill.INSERT)
	private Date createTime;
	@TableField(value="last_update_time",fill = FieldFill.UPDATE)
	private Date lastUpdateTime;
	@TableField(value="time",fill = FieldFill.INSERT)
	private Date time;

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
