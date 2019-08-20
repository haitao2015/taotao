package com.taotao.portal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.taotao.pojo.TbUser;
import com.taotao.portal.service.impl.UserServiceImpl;
import com.taotao.utils.CookieUtils;

/***
 * 模拟URL拦截器
 * 
 * @author Administrator
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private UserServiceImpl userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 在Handler执行之前处理
		// 例如：判断用户登陆状态
		// 1.从COOKIE 中取token
		String cookieName = "TT_TOKEN";
		String token = CookieUtils.getCookieValue(request, cookieName);
		// 根据token换取用户信息，调用SSO系统接口
		TbUser user = userService.getUserByToken(token);
		// 如果取不到用户信息
		if (null == user) {
			// ,跳转到登录页面，把用户请求的URL作为参数传递给登录页面，这样就能在登录后在跳转到那个请求页面了
			response.sendRedirect(userService.SSO_BASE_URL + userService.SSO_USER_LOGIN
					+ "?redirect="+request.getRequestURL());
			//如果取不到用户信息 返回false
			return false;	
		}
		// 返回值决定 handler是否执行。true 执行，false 不执行
		//如果取到用户信息 返回true
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 在Handler执行之后，返回ModelAndView之前 处理

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 在Handler执行之后，返回ModelAndView之后
		// 响应用户之后

	}

}
