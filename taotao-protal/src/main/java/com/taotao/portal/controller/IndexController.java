package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.portal.service.AdService;

/***
 * 首页 IndexController
 * @author Administrator
 *
 */
@Controller
@RequestMapping("")
public class IndexController {
	
	@Autowired
	private AdService adService;
	/***
	 * 首页展示
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/index")
	public String showIndex(Model model) throws Exception {
		
		//大广告位的内容数据
		String adResult = adService.getAdItemList();
		model.addAttribute("ad1", adResult);
		//其他内容数据
		
		return "index";
	}
}
