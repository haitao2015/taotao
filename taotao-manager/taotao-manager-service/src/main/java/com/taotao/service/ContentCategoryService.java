package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;

/***
 * 内容分类SERVICE
 * @author Administrator
 *
 */
public interface ContentCategoryService {
	/**
	 * 内容分类查询 by parentid
	 * @param parentid
	 * @return
	 * @throws Exception
	 */
	public List<EasyUITreeNode> getContentCategoryList(long parentid) throws Exception;
	/***
	 * 保存内容分类节点
	 * @param parentid
	 * @param name
	 * @return
	 * @throws Exception
	 */
	TaotaoResult addNode(long parentId, String name) throws Exception;
}
