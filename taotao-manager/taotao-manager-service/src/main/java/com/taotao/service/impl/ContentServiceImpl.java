package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.service.ContentService;
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private  TbContentMapper  contentMapper;
	@Override
	public EasyUIResult getContentList(Long catId, Integer page, Integer rows) throws Exception {
		//根据category_id查询内容列表
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(catId);
		//分页处理
		PageHelper.startPage(page, rows);
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
		//取分页信息
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		EasyUIResult result = new EasyUIResult(pageInfo.getTotal(), list);
		return result;
	}
	@Override
	public TaotaoResult addContent(TbContent content) throws Exception {
		
		//把图片信息保存至数据库
		content.setCreated(new Date());
		content.setUpdated(new Date());
		//把内容信息添加到数据库
		contentMapper.insert(content);
		
		return TaotaoResult.ok();
	}
	@Override
	public TaotaoResult getContentList(long cid) throws Exception {
		TbContentExample example = new TbContentExample();
		//添加条件
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(cid);
		List<TbContent> list = contentMapper.selectByExample(example);
		return TaotaoResult.ok(list);
	}

	

}
