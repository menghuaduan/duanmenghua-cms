package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.bean.Article;
import com.bean.Blogroll;
import com.bean.Tag;
import com.github.pagehelper.PageInfo;

/**
 * @作者： 段梦华
 * @时间：2019年10月17日
 * 
 * 
 * 文章mapper
 */
public interface ArticleMapper {

	//查询分类下的文章
	List list(@Param("chnId")Integer chnId, @Param("catId")Integer catId);

	//查看热门文章
	List hostlist();

	//最新文章
	List<Article> last(int sum);

	
	//根据编号查询找具体文章
	Article findById(@Param("articleId")Integer articleId);

	//添加文章
	int  add(Article article);

	//查看我的文章
	List<Article> listByUserId(Integer userid);

	/**
	 * 根据文章id删除文章
	 */
	@Update("UPDATE cms_article SET deleted=1 WHERE id=#{value}")
	int deleteById(Integer id);

	//修改文章
	@Update("UPDATE cms_article set title=#{title},content=#{content},picture=#{picture},channel_id=#{channelId},"
			+ "category_id=#{categoryId},updated=now() WHERE id=#{id}")
	int update(Article article);

	
	
	//管理员查看所有文章			
	List<Article> listAdmin(@Param("status")Integer status);

	//设置文章是否通过
	@Update("UPDATE cms_article set status=#{status},updated=now() WHERE id=#{articleId}")
	int updateStatus(@Param("articleId") Integer articleId, @Param("status") int status);

	//设置文章是否热门
	@Update("UPDATE cms_article set hot=#{status},updated=now() "
			+ " WHERE id=#{articleId}")
	int updateHot(@Param("articleId") Integer articleId, @Param("status") int status);

	
	//根据名称查找文章标签
	@Select("SELECT * FROM cms_tag where tagname=#{value} limit 1")
	Tag findTagByName(String tag);

	//添加标签
	void addTag(Tag tagBean);

	//添加文章和标签的中间表
	@Insert("INSERT INTO cms_article_tag_middle values(#{articleId},#{tagId}) ")
	void addArticleTag(@Param("articleId") Integer articleId, @Param("tagId") Integer tagId);
	
	
	//  删除中间表
	@Delete(" DELETE FROM cms_article_tag_middle WHERE aid=#{value}")
	int delTagsByArticleId(Integer articleId);

	//友情链接
	List<Blogroll> blogroll(int i);
}
