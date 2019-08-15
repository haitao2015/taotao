package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
/***
 * 商品管理 Controller
 * @author Administrator
 *
 */
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.service.ItemParamItemService;
@Controller
@RequestMapping("")
public class ItemParamItemController {
	
	@Autowired
	private ItemParamItemService itemParamItemService;
	
	@RequestMapping("/showitem/{itemId}")
	public String showItemParam(@PathVariable Long itemId, Model model) {
		String string = itemParamItemService.getItemParamByItemId(itemId);
		model.addAttribute("itemParam", string);
		return "showitem";
	}
}
