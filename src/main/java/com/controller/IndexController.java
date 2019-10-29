package com.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bean.Article;
import com.bean.Blogroll;
import com.bean.Category;
import com.bean.Channels;
import com.github.pagehelper.PageInfo;
import com.service.ArticleService;
import com.service.CatService;
import com.service.IndexSrevice;
import com.utils.PageUtils;

/**
 * @作者： 段梦华
 * @时间：2019年10月17日
 * 首页的Controller 查询所有的频道以及频道下边的分类
 */

@Controller
@RequestMapping("index")
public class IndexController{
	
	//查询频道的service
	@Autowired
	IndexSrevice service;
	
	//查询频道下的分类
	@Autowired
	CatService catService;
	
	//查询分类下的文章
	@Autowired
	ArticleService articleService;
	

	//获取所有的频道及下边的分类并返回index.jsp
	@RequestMapping("findchannels.do")
	public String findchannels(HttpServletRequest request,
			@RequestParam(defaultValue="0")Integer chnId,
			@RequestParam(defaultValue="0")Integer catId,
			@RequestParam(defaultValue="1")Integer page
														){
	
		//查询所有的频道
		List<Channels> Channels = service.findchannels();
		
		//查询频道下的分类
		if(chnId != 0){
			List<Category> Category = catService.findCatBychnId(chnId);
			
			//根据频道id查出来的所有分类
			request.setAttribute("catygories", Category);
			
			//查询分类下边的文章
			PageInfo<Article>  articleList = articleService.list(chnId,catId,page);
			PageUtils.page(request, "/index/findchannels.do?chnId="+chnId+"&catId=" + catId, 10, articleList.getList(),
					(long)articleList.getTotal(), articleList.getPageNum());
			request.setAttribute("articles", articleList);
		}else{
			// 首页热门
			// 获取热门文章
			PageInfo<Article>  articleList = articleService.hostList(page);
			for (Article channels2 : articleList.getList()) {
				System.out.println("得到的数据："+channels2);
			}
			PageUtils.page(request, "index/findchannels.do", 6, articleList.getList(),
					(long)articleList.getTotal(), articleList.getPageNum());
			request.setAttribute("articles", articleList);
		}
		
		
		//获取最新文章
		List<Article>  lastList = articleService.last(5);
		request.setAttribute("lastList", lastList);
		
		//获取友情链接
		List<Blogroll>  blogrollList = articleService.blogroll(5);
		for (Blogroll blogroll : blogrollList) {
			System.out.println("得到的友情链接"+blogroll);
		}
		request.setAttribute("links",blogrollList);
		//查询到的所有频道
		request.setAttribute("Channels", Channels);
		request.setAttribute("chnId", chnId);//将频道id传过去
		request.setAttribute("catId", catId);//将分类id传过去
		return "index";
	}
}
