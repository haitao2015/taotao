package com.taotao.portal.service;
import java.util.List;

/***
 * 购物车 Service
 * @author Administrator
 *
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.pojo.CartItem;

public interface CartService {
	/**
	 * 加入购物车
	 * @param itemId
	 * @param num
	 * @param request
	 * @param response
	 * @return
	 */
	TaotaoResult addCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response);
	/***
	 * 展示购物车信息
	 * @param request
	 * @param response
	 * @return
	 */
	List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response);
	/***
	 * 删除购物车商品
	 * @param itemId
	 * @param request
	 * @param response
	 * @return
	 */
	TaotaoResult delCartItem(long itemId, HttpServletRequest request, HttpServletResponse response);
}
