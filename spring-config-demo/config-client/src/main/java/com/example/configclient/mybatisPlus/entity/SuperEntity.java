package com.example.configclient.mybatisPlus.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;

/**
 *Create by yangwenfu on 2018/1/31
 */
public class SuperEntity <T extends Model> extends Model<T> {
	/**
	 * 主键ID , 这里故意演示注解可以无
	 */
	@TableId("id")
	private Long id;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}
