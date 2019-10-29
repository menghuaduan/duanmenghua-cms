package com.bean;

import java.util.Date;
import java.util.List;

import com.comon.ArticleType;

/**
 * @作者： 段梦华
 * @时间：2019年10月17日
 * 
 * 文章的实体类
 */
public class Article {
	
	private Integer id;
	private String title;
	private String content;
	private String picture;
	private Integer channelId;
	private Integer categoryId;
	private Integer userId;
	private Integer hits;
	private Integer hot;
	private Integer status;
	private Integer deleted;
	private Date created;
	private Date updated;
	private Integer commentCnt;
	//添加其他类型的属性
	private Channels channel;
	private Category cat;
	private User user;
	
	//标签
	private String tags;
	
	//文章类型
	private ArticleType articleType=ArticleType.HTML;
	
	//图片类型文章的集合
	private List<ImageBean> imgList;
	
	
	
	
	public List<ImageBean> getImgList() {
		return imgList;
	}
	public void setImgList(List<ImageBean> imgList) {
		this.imgList = imgList;
	}
	public ArticleType getArticleType() {
		return articleType;
	}
	public void setArticleType(ArticleType articleType) {
		this.articleType = articleType;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Channels getChannel() {
		return channel;
	}
	public void setChannel(Channels channel) {
		this.channel = channel;
	}
	public Category getCat() {
		return cat;
	}
	public void setCat(Category cat) {
		this.cat = cat;
	}
	public Article(Integer id, String title, String content, String picture,
			Integer channelId, Integer categoryId, Integer userId,
			Integer hits, Integer hot, Integer status, Integer deleted,
			Date created, Date updated, Integer commentCnt) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.picture = picture;
		this.channelId = channelId;
		this.categoryId = categoryId;
		this.userId = userId;
		this.hits = hits;
		this.hot = hot;
		this.status = status;
		this.deleted = deleted;
		this.created = created;
		this.updated = updated;
		this.commentCnt = commentCnt;
	}
	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getHits() {
		return hits;
	}
	public void setHits(Integer hits) {
		this.hits = hits;
	}
	public Integer getHot() {
		return hot;
	}
	public void setHot(Integer hot) {
		this.hot = hot;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public Integer getCommentCnt() {
		return commentCnt;
	}
	public void setCommentCnt(Integer commentCnt) {
		this.commentCnt = commentCnt;
	}
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", content="
				+ content + ", picture=" + picture + ", channelId=" + channelId
				+ ", categoryId=" + categoryId + ", userId=" + userId
				+ ", hits=" + hits + ", hot=" + hot + ", status=" + status
				+ ", deleted=" + deleted + ", created=" + created
				+ ", updated=" + updated + ", commentCnt=" + commentCnt
				+ ", channel=" + channel + ", cat=" + cat + ", user=" + user
				+ ", tags=" + tags + ", articleType=" + articleType
				+ ", imgList=" + imgList + "]";
	}
	
	
	

}
