package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.bean.Category;

/**
 * @作者： 段梦华
 * @时间：2019年10月17日
 * 
 * 频道下边的分类
 */
public interface CatMapper {

	List<Category> findCatBychnId(@Param("chnId")Integer chnId);
	
	// 根据id获取对应的分类
	
	/*@Select("SELECT * FROM cms_category WHERE id = #{value} limit 1")*/
	Category findById(Integer id);

}
