package com.bean;

/**
 * @作者： 段梦华
 * @时间：2019年10月28日
 * 
 * 友情连接的相关类
 */
public class Blogroll {
	private Integer bid;
	private String url;
	private String text;
	public Blogroll(Integer bid, String url, String text) {
		super();
		this.bid = bid;
		this.url = url;
		this.text = text;
	}
	public Blogroll() {
		super();
	}
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Override
	public String toString() {
		return "Blogroll [bid=" + bid + ", url=" + url + ", text=" + text + "]";
	}
}
