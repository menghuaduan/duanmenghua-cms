package com.mapper;

import java.util.List;

import com.bean.Channels;

/**
 * @作者： 段梦华
 * @时间：2019年10月17日
 */
public interface IndexMapper {
	//查询所有的频道
	List<Channels> findchannels();

	
	
	
	/**
	 *  根据id获取对应的频道
	 * @param id
	 * @return
	 */
	/*@Select("SELECT * FROM cms_channel WHERE id = #{value} limit 1")*/
	Channels findById(Integer id);
	

}
