package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Category;
import com.mapper.CatMapper;

/**
 * @作者： 段梦华
 * @时间：2019年10月17日
 * 
 * 查询频道下边的分类
 */

@Service
public class CatService {
	@Autowired
	CatMapper catMapper;

	//根据频道的id去查询他下边的栏目
	public List<Category> findCatBychnId(Integer chnId) {
		return catMapper.findCatBychnId(chnId);
	}
	

}
