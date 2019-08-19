package com.taotao.controller;

import org.springframework.stereotype.Controller;
/***
 * 商品管理 Controller
 * @author Administrator
 *
 */
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/***
 * 页面跳转
 * @author Administrator
 *
 */
@Controller
public class PageController {
//	@Autowired
//	private ItemService itemService;
	/**
	 * 打开首页
	 * @return
	 */
	@RequestMapping("/")
	public String show() {
		return "index";
	}
	/***
	 * 打开其他页面
	 * @return
	 */
	@RequestMapping("/{page}")
	public String showpage(@PathVariable String page) {
		return page;
	}
}
