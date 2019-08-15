package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;

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
	/***
	 * 添加商品
	 * @param item
	 * @param itemDesc
	 * @param itemParams
	 * @return
	 */
	TaotaoResult saveItem(TbItem item, String itemDesc, String itemParams);
}
