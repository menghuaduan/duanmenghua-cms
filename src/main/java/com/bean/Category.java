package com.bean;

/**
 * @作者： 段梦华
 * @时间：2019年10月17日
 * 
 * 频道下的分类的栏目实体类
 */
public class Category {
	 private Integer id;
	 private String name;
	 private String channelid;
	public Category(Integer id, String name, String channelid) {
		super();
		this.id = id;
		this.name = name;
		this.channelid = channelid;
	}
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getChannelid() {
		return channelid;
	}
	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", channelid="
				+ channelid + "]";
	}
	
	 
	 
}
