package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.service.ContentCategoryService;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	@Override
	public List<EasyUITreeNode> getContentCategoryList(long parentid) throws Exception {
		List<EasyUITreeNode> list=new ArrayList<>();
		TbContentCategoryExample example= new TbContentCategoryExample();
		Criteria criteria=	example.createCriteria();
		criteria.andParentIdEqualTo(parentid);
		List<TbContentCategory>  clist=contentCategoryMapper.selectByExample(example);
		for (TbContentCategory tbContentCategory : clist) {
			EasyUITreeNode n=new EasyUITreeNode();
			n.setId(tbContentCategory.getId());
			n.setText(tbContentCategory.getName());
			//判断是否是父节点
			n.setState(tbContentCategory.getIsParent()?"closed":"open");
			list.add(n);
		}
		return list;
	}
	@Override
	public TaotaoResult addNode(long parentId, String name) throws Exception {
		Date date = new Date();
		//添加一个新节点
		//创建一个节点对象
		TbContentCategory node = new TbContentCategory();
		node.setName(name);
		node.setParentId(parentId);
		node.setIsParent(false);
		node.setCreated(date);
		node.setUpdated(date);
		node.setSortOrder(1);
		//状态。可选值:1(正常),2(删除)
		node.setStatus(1);
		//插入新节点。需要返回主键
		contentCategoryMapper.insert(node);
		//判断如果父节点的isparent不是true修改为true
		//取父节点的内容
		TbContentCategory parentNode = contentCategoryMapper.selectByPrimaryKey(parentId);
		if (!parentNode.getIsParent()) {
			parentNode.setIsParent(true);
			contentCategoryMapper.updateByPrimaryKey(parentNode);
		}
		//把新节点返回
		return TaotaoResult.ok(node);
	}
}
