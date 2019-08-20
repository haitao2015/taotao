package com.taotao.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

public interface UserService {
	/**
	 *  用户登陆
	 * @param username
	 * @param password
	 * @param request 
	 * @param response 
	 * @return
	 * @throws Exception
	 */
	TaotaoResult login(String username, String password, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	/***
	 * 校验用户信息
	 * @param value 
	 * @param type type为类型，可选参数1、2、3分别代表username、phone、email
	 * @return
	 * @throws Exception
	 */
	TaotaoResult checkInfo(String value, String type) throws Exception;
	/***
	 * 用户注册
	 * @param user
	 * @return
	 * @throws Exception
	 */
	TaotaoResult register(TbUser user) throws Exception;
	/***
	 * 用户token
	 * @param token
	 * @return
	 * @throws Exception
	 */
	TaotaoResult getUserByToken(String token) throws Exception;
	/***
	 * 安全退出
	 * @param token
	 * @return
	 * @throws Exception
	 */
	TaotaoResult logout(String token) throws Exception;
}
