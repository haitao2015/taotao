package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.service.ItemService;

/***
 * 商品管理Service
 * 
 * @author Administrator
 *
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper tbItemMapper;

	@Override
	public TbItem getItemById(long itemId) {
		// TODO Auto-generated method stub
		// TbItem tbItem=tbItemMapper.selectByPrimaryKey(itemId);
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list = tbItemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			TbItem tbItem = list.get(0);
			return tbItem;
		}
		return null;
	}

	@Override
	public EUDataGridResult getItemList(int page, int rows) {
		PageHelper.startPage(page, rows);
		// 查询商品列表
		TbItemExample t = new TbItemExample();
		List<TbItem> list = tbItemMapper.selectByExample(t);
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

}
