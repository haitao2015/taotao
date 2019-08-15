package com.taotao.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
/***
 * 商品管理 Controller
 * @author Administrator
 *
 */
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {
	/***
	 * 商品信息查询
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List  geItemById() throws Exception {
		List catList = new ArrayList();
		return catList;
	}
}
