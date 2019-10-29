package com.duanmenghau.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bean.Article;
import com.comon.ArticleType;
import com.service.ArticleService;


public class TestArticle  extends BaseTest{
	
	@Autowired
	ArticleService arService;
	
	
	
	
	@Test
	public void testGetAarticle() {
		
		System.out.println(" 33  文章是 ： " +  arService.findById(33).toString());
		
		System.out.println(" 34  文章是 ： " + arService.findById(34).toString());
		
	} 
	
	@Test
	public void testAddAarticle() {
		
		Article article1 = new Article();
		article1.setArticleType(ArticleType.HTML);
		article1.setTitle("测试html 文章");
		arService.add(article1);
		
		
		Article article2 = new Article();
		article2.setArticleType(ArticleType.IMAGE);
		article2.setTitle("测试html 文章");
		arService.add(article2);
		
	} 
	

}
