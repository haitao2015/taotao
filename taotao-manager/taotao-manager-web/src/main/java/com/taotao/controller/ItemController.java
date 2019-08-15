package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/***
 * 商品管理 Controller
 * @author Administrator
 *
 */
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;
import com.taotao.service.ItemService;
@Controller
@RequestMapping("/item")
public class ItemController {
	@Autowired
	private ItemService itemService;

	/***
	 * 商品信息查询
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/{itemId}")
	@ResponseBody
	public TbItem geItemById(@PathVariable Long itemId) {
		TbItem tbItem=itemService.getItemById(itemId);
		return tbItem;
	}
/***
 * 商品列表查询
 * @param page
 * @param rows
 * @return
 */
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page,Integer rows) {
		EUDataGridResult result =  itemService.getItemList(page, rows);
		return result;
	}
	/***
	 * 商品  保存
	 * @param item
	 * @param desc
	 * @return
	 */
	@RequestMapping(value="/save" , method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult addItem(TbItem item, String desc,String itemParams) {
		TaotaoResult result = itemService.saveItem(item, desc, itemParams);
		return result;
	}
}
