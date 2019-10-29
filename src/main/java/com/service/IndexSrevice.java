package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Channels;
import com.mapper.IndexMapper;

/**
 * @作者： 段梦华
 * @时间：2019年10月17日
 * 
 * 和首页相关的service
 */

@Service
public class IndexSrevice {
	@Autowired
	IndexMapper mapper;

	//查询所有的频道
	public List<Channels> findchannels() {
		return mapper.findchannels();
	}
	
	

}
