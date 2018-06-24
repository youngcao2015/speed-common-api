package com.speed.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.speed.entity.Blog;
import com.speed.mapper.BlogMapper;
import com.speed.service.IBlogService;

import javax.annotation.Resource;

/**
 *
 * Blog 表数据服务层接口实现类
 *
 */
@Service("blogService")
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {

	@Resource
	private BlogMapper blogMapper;
	
	@Override
	public Page<Map<Object, Object>> selectBlogPage(Page<Map<Object, Object>> page) {
		// TODO Auto-generated method stub
		
		List<Map<Object, Object>> list = blogMapper.selectMap(page);
		page.setRecords(list);
		return page;
	}

}