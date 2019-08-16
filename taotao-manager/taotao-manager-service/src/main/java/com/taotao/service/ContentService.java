package com.taotao.service;

import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

/***
 * 内容SERVICE
 * @author Administrator
 *
 */
public interface ContentService {

	/***
	 * 内容查询 列表
	 * @param page
	 * @param rows
	 * @return
	 */
	public EasyUIResult getContentList(Long catId, Integer page, Integer rows) throws Exception;
	/***
	 * 插入内容
	 * @return
	 */
	public TaotaoResult addContent(TbContent content) throws Exception;
	/**
	 * 内容查询 列表
	 * @param cid
	 * @return
	 * @throws Exception
	 */
	public TaotaoResult getContentList(long cid) throws Exception;
	
}
