package com.bean;

/**
 * @作者： 段梦华
 * @时间：2019年10月17日
 * 
 * 
 * 所有的频道
 */
public class Channels {
	public Integer id;
	public String name;
	public String description;
	public String icon;
	public Channels(Integer id, String name, String description, String icon) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.icon = icon;
	}
	public Channels() {
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	@Override
	public String toString() {
		return "Channels [id=" + id + ", name=" + name + ", description="
				+ description + ", icon=" + icon + "]";
	}
	
	
	
}
