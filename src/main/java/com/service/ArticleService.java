package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Article;
import com.bean.Blogroll;
import com.bean.Tag;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.ArticleMapper;

/**
 * @作者： 段梦华
 * @时间：2019年10月17日
 * 
 *用来查询分类下的文章
 */

@Service
public class ArticleService {
	@Autowired
	 ArticleMapper mapper;

	//查询分类下的文章
	public PageInfo<Article> list(Integer chnId, Integer catId, Integer page) {
		PageHelper.startPage(page, 6);
		//查询指定页码数据 并返回页面信息
		return new PageInfo(mapper.list(chnId, catId)) ;
		
	}

	//获取热门
	public PageInfo<Article> hostList(Integer page) {
		PageHelper.startPage(page, 6);
		//查询指定页码数据 并返回页面信息
		return new PageInfo(mapper.hostlist()) ;
	}

	//获取最新文章
	public List<Article> last(int sum) {
		return mapper.last(sum);
	}

	//根据文章id获取文章
	public Article findById(Integer articleId) {
		return mapper.findById(articleId);
	}

	
	//添加文章
	public void add(Article article) {
		//添加文章
		int i = mapper.add(article);
		
		//添加标签
		processTag(article);
	}

	//添加标签
	private void processTag(Article article){
		
		//如果标签补位空的话执行相关操作
		if(article.getTags() != null){
			String[] tags = article.getTags().split(",");
			for (String tag : tags) {
				// 判断这个tag在数据库当中是否存在
				Tag tagBean = mapper.findTagByName(tag);
				if(tagBean==null) {
					tagBean = new Tag(tag);
					//添加标签
					mapper.addTag(tagBean);
				}
				//插入中间表
				try {
					mapper.addArticleTag(article.getId(),tagBean.getId());
				}catch(Exception e){
					System.out.println("插入失败 ");
				}
			}
		}
			
			
		}
	
	
	//查看我的文章
	public PageInfo<Article> listArticleByUserId(Integer userid, Integer page) {
		PageHelper.startPage(page, 6);
		return new PageInfo<Article>(mapper.listByUserId(userid));
	}

	//删除我的文章
	public int remove(Integer id) {
		// TODO Auto-generated method stub
		int result =  mapper.deleteById(id);
		return result;
	}

	//修改文章
	public int update(Article article) {
		
		//添加文章
		int result = mapper.update(article);
		//删除标签中间表
		mapper.delTagsByArticleId(article.getId());
		//添加标签
		processTag(article);
		return result;
	}

	//管理员查看所有文章
	public PageInfo<Article> getAdminArticles(Integer page,Integer status) {
		PageHelper.startPage(page, 6);
		return new PageInfo<Article>(mapper.listAdmin(status));
	}

	
	//设置文章是否通过
	public int updateStatus(Integer articleId, int status) {
		// TODO Auto-generated method stub
		return mapper.updateStatus(articleId,status);
	}

	//设置文章是否为热门
	public int updateHot(Integer articleId, int status) {
		// TODO Auto-generated method stub
		return mapper.updateHot(articleId,status);
	}

	public List<Blogroll> blogroll(int i) {
		
		return mapper.blogroll(i);
	}
	

}
