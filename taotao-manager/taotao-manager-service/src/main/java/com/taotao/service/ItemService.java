package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
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
	/***
	 * 查询商品 列表
	 * @param page
	 * @param rows
	 * @return
	 */
	public EUDataGridResult getItemList(int page, int rows);
}
