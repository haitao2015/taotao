package com.taotao.portal.service;

import com.taotao.portal.pojo.ItemInfo;

public interface ItemService {
	/**
	 * 商品信息查询 rest
	 * @param itemId
	 * @return
	 */
	ItemInfo getItemById(Long itemId);
	/***
	 * 商品描述信息查询 rest
	 * @param itemId
	 * @return
	 */
	String getItemDescById(Long itemId);
	/***
	 * 商品规格信息查询 rest
	 * @param itemId
	 * @return
	 */
	String getItemParam(Long itemId);
}
