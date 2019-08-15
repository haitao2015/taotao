package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;
import com.taotao.utils.JsonUtils;
/***
 * 商品分类列表
 * ItemCatController
 * @author Administrator
 *
 */
@Controller
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;
	/**
	 * 方法一  商品分类数据JSON数据  
	 * produces=MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8"   不加的话会  中文乱码
	 * @param callback
	 * @return
	 */
	@RequestMapping(value="/itemcat/all", 
			produces=MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public String getItemCatList(String callback) {
		CatResult catResult = itemCatService.getItemCatList();
		//把pojo转换成字符串
		String json = JsonUtils.objectToJson(catResult);
		//拼装返回值
		String result = callback + "(" + json + ");";
		return result;
	}
	/**
	 * 方法二   商品分类数据JSON数据   springmvc 4.1之后提供了 
	 * produces=MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8"   不加的话会  中文乱码
	 * @param callback
	 * @return
	 */
	/*@RequestMapping(value="/itemcat/all")
	@ResponseBody
	public Object getItemCatList2(String callback) {
		CatResult catResult = itemCatService.getItemCatList();
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(catResult);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}*/
}
