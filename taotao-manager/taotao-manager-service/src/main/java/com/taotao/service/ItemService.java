package com.taotao.service;

import com.taotao.pojo.TbItem;

/***
 * 商品管理Service
 * @author Administrator
 *
 */
public interface ItemService {

	/***
	 * 查询商品信息 by id
	 * @param itemId
	 * @return
	 */
	public TbItem getItemById(long itemId);
}
