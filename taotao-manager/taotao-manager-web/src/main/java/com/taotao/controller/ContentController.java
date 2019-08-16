package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;
/***
 * 内容管理  
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;
	/**
	 * 内容查询 分页
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/query/list")
	@ResponseBody
	public EasyUIResult getContentList(Long categoryId, Integer page, Integer rows) throws Exception {
		EasyUIResult result = contentService.getContentList(categoryId, page, rows);
		return result;
		
	}
	@RequestMapping("/category/{cid}")
	@ResponseBody
	public TaotaoResult getContentList(@PathVariable Long cid ) {
		TaotaoResult result = null;
		try {
			result = contentService.getContentList(cid);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, e.getMessage());
		}
		return result;
	}
	/**
	 * 保存 内容信息
	 * @param content
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult addContent(TbContent content) throws Exception {
		TaotaoResult result = contentService.addContent(content);
		return result;
	}
}
