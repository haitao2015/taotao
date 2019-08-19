package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;

public interface ItemService {
	/***
	 * 商品基本信息查询
	 * @return
	 */
	TaotaoResult getItemBaseInfo(long itemId);
	/***
	 * 商品描述信息查询
	 * @param itemId
	 * @return
	 */
	TaotaoResult getItemDesc(long itemId);
	/***
	 * 商品规格参数查询
	 * @param itemId
	 * @return
	 */
	TaotaoResult getItemParam(long itemId);
}
