package com.taotao.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	/***
	 * 加入购物车
	 * @param itemId
	 * @param num
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/add/{itemId}")
	public String addCartItem(@PathVariable Long itemId, @RequestParam(defaultValue = "1") Integer num,
			HttpServletRequest request, HttpServletResponse response) {
		TaotaoResult result = cartService.addCartItem(itemId, num, request, response);
		return "redirect:/cart/cartsuccess.html";
	}
	/***
	 * 加入购物车成功
	 */
	@RequestMapping("/cartsuccess")
	public String goCartSuccess(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "cartSuccess";
	}
	/***
	 * 展示购物车信息
	 */
	@RequestMapping("/cart")
	public String showCart(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<CartItem> list = cartService.getCartItemList(request, response);
		model.addAttribute("cartList", list);
		return "cart";
	}
	/***
	 * 删除购物车商品
	 */
	@RequestMapping("/delete/{itemId}")
	public String delCart(@PathVariable Long itemId, HttpServletRequest request, HttpServletResponse response) {
		cartService.delCartItem(itemId, request, response);
		return "redirect:/cart/cart.html";
	}
}
