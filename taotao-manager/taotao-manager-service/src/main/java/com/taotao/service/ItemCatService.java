package com.taotao.service;

import java.util.List;

import com.taotao.pojo.TbItemCat;

/***
 * 商品分类 service
 * @author Administrator
 *
 */
public interface ItemCatService {
	/***
	 * 查询商品分类
	 * @param parentId
	 * @return
	 */
	public List<TbItemCat> getItemCatList(Long parentId) throws Exception;

}
