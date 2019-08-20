package com.taotao.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserService;
import com.taotao.utils.ExceptionUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	/***
	 * 用户登录
	 * @param username
	 * @param password
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult login(String username, String password,HttpServletRequest 
			request,HttpServletResponse response) {//只接收POST
		TaotaoResult result = null;
		try {
			result = userService.login(username, password,request,response);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return result;
	}
	/***
	 * 用户校验信息
	 * @param param
	 * @param type
	 * @param callback
	 * @return
	 */
	@RequestMapping("/check/{param}/{type}")
	@ResponseBody
	public Object checkInfo(@PathVariable String param, @PathVariable String type, String callback) {
		TaotaoResult result = null;
		try {
			result = userService.checkInfo(param, type);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		//支持jsonp
		if (!StringUtils.isBlank(callback)) {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}
		return result;
	}
	/***
	 * 用户注册
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/register", method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult register(TbUser user) {
		TaotaoResult taotaoResult = null;
		try {
			taotaoResult = userService.register(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return taotaoResult;
	}
	/***
	 * 取用户token
	 * @param token
	 * @param callback
	 * @return
	 */
	@RequestMapping("/token/{token}")
	@ResponseBody
	public Object getUserByToken(@PathVariable String token, String callback) {
		TaotaoResult result = null;
		try {
			result = userService.getUserByToken(token);
		} catch (Exception e) {
			e.printStackTrace();
			result = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		//判断是否为jsonp调用
		if (!StringUtils.isBlank(callback)) {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}
		return result;
	}
	/**
	 *  用户安全退出
	 * @param token
	 * @param callback
	 * @return
	 */
	@RequestMapping(value="/logout/{token}")
	@ResponseBody
	public Object logout(@PathVariable String token, String callback) {
		TaotaoResult result = null;
		try {
			result = userService.logout(token);
		} catch (Exception e) {
			e.printStackTrace();
			result = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		//判断是否为jsonp调用
		if (!StringUtils.isBlank(callback)) {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}
		return result;
	}
	/**
	 * 打开login
	 * @return
	 */
	@RequestMapping(value={"/login", "/showLogin"})
	public String showLogin(@Param("redirect")String redirect,Model model) {
		model.addAttribute("redirect", redirect);
		return "login";
	}
	/**
	 * 打开注册页
	 * @return
	 */
	@RequestMapping(value={"/register", "/showRegister"})
	public String showRegister() {
		return "register";
	}
	
}
