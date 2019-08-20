package com.taotao.sso.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.pojo.TbUserExample.Criteria;
import com.taotao.sso.dao.JedisClient;
import com.taotao.sso.service.UserService;
import com.taotao.utils.CookieUtils;
import com.taotao.utils.JsonUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private TbUserMapper userMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${REDIS_USER_SESSION_KEY}")
	private String REDIS_USER_SESSION_KEY;
	@Value("${SESSION_EXPIRE_TIME}")
	private Integer SESSION_EXPIRE_TIME;
	
	@Override
	public TaotaoResult login(String username, String password, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//根据用户名查询用户信息
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<TbUser> list = userMapper.selectByExample(example);
		if (null == list || list.isEmpty()) {
			return TaotaoResult.build(400, "用户不存在");
		}
		//核对密码
		TbUser user = list.get(0);
		if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
			return TaotaoResult.build(400, "密码错误");
		}
		//登录成功，把用户信息写入redis
		//生成一个用户token
		String token = UUID.randomUUID().toString();
		user.setPassword("");//安全情况下，把密码清空，不在redis保持密码
		//用户信息写入redis
		jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token, JsonUtils.objectToJson(user));
		//设置session过期时间
		jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SESSION_EXPIRE_TIME);
		
		/***
		 * 新增写入Cookie   cookie有效期，默认关闭浏览器失效。
		 * 注意：页面取到这个cookie值 再去查询用户信息： reids
		 */
		String cookieName="TT_TOKEN";//name
		String cookieValue=token;//value
		CookieUtils.setCookie(request, response, cookieName, cookieValue);
		
		
		
		
		//返回token
		return TaotaoResult.ok(token);
	}
	@Override
	public TaotaoResult checkInfo(String value, String type) throws Exception {
		
		boolean result = false;
		//type为类型，可选参数1、2、3分别代表username、phone、email
		if ("1".equals(type)) {
			result = checkUserName(value);
		} else if ("2".equals(type)) {
			result = checkPhone(value);
		} else if ("3".equals(type)) {
			result = checkEmail(value);
		}
		//返回结果
		if (result) {
			return TaotaoResult.ok(result);
		}
		return TaotaoResult.build(201, "此数值已经存在");
		
	}
	
	private boolean checkUserName(String userName) throws Exception {
		//创建查询条件
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(userName);
		List<TbUser> list = userMapper.selectByExample(example);
		//判断结果中是否存在
		if (list == null || list.isEmpty()) {
			return true;
		}
		return false;
	}
	
	private boolean checkPhone(String phone) throws Exception {
		//创建查询条件
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andPhoneEqualTo(phone);
		List<TbUser> list = userMapper.selectByExample(example);
		//判断结果中是否存在
		if (list == null || list.isEmpty()) {
			return true;
		}
		return false;
	}
	
	private boolean checkEmail(String email) throws Exception {
		//创建查询条件
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmailEqualTo(email);
		List<TbUser> list = userMapper.selectByExample(example);
		//判断结果中是否存在
		if (list == null || list.isEmpty()) {
			return true;
		}
		return false;
	}
	@Override
	public TaotaoResult register(TbUser user) throws Exception {
		//有效性验证
		if (StringUtils.isBlank(user.getUsername())) {
			return TaotaoResult.build(400, "用户名不能为空");
		}
		if (StringUtils.isBlank(user.getPassword())) {
			return TaotaoResult.build(400, "密码不能为空");
		}
		if (StringUtils.isBlank(user.getPhone())) {
			return TaotaoResult.build(400, "手机不能为空");
		}
		//转换md5
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		//完善user信息
		user.setCreated(new Date());
		user.setUpdated(new Date());
		
		//添加到数据库
		userMapper.insert(user);
		
		return TaotaoResult.ok();
	}
	
	/**
	 * 根据token取用户信息
	 * <p>Title: getUserByToken</p>
	 * <p>Description: </p>
	 * @param token
	 * @return
	 * @throws Exception
	 * @see com.taotao.sso.service.UserTokenService#getUserByToken(java.lang.String)
	 */
	@Override
	public TaotaoResult getUserByToken(String token) throws Exception {
		//从redis中取用户信息
		String userJson = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);
		
		if (StringUtils.isBlank(userJson)) {
			return TaotaoResult.build(400, "该用户已过期");
		}
		//把json转换成user对象
		TbUser user = JsonUtils.jsonToPojo(userJson, TbUser.class);
		//更新用户有效期
		jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SESSION_EXPIRE_TIME);
		
		return TaotaoResult.ok(user);
	}
	@Override
	public TaotaoResult logout(String token) throws Exception {
		//从redis中取用户信息
		Long userJson = jedisClient.del(REDIS_USER_SESSION_KEY + ":" + token);
		return TaotaoResult.ok(userJson);
	}
}
