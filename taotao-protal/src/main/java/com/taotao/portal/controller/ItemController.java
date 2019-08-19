package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.Item;
import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemService;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	/**
	 * 商品详细页
	 * @param itemId
	 * @param model
	 * @return
	 */
	@RequestMapping("/item/{itemId}")
	public String showItem(@PathVariable Long itemId, Model model) {
		ItemInfo item = itemService.getItemById(itemId);
		model.addAttribute("item", item);
		//跳页面
		return "item";
	}
	/***
	 * 商品描述信息
	 * @param itemId
	 * @return
	 */
	
	@RequestMapping(value="/item/desc/{itemId}", produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemDesc(@PathVariable Long itemId) {
		String string = itemService.getItemDescById(itemId);
		//不跳页面，只是异步请求，返回 数据
		return string;
	}
	/**
	 * 商品规格信息
	 * @param itemId
	 * @return
	 */
	@RequestMapping(value="/item/param/{itemId}", produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemParam(@PathVariable Long itemId) {
		String string = itemService.getItemParam(itemId);
		return string;
	}
}
