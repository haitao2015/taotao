package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemService;
import com.taotao.utils.ExceptionUtil;
import com.taotao.utils.IDUtils;

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
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

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
	@Override
	public TaotaoResult saveItem(TbItem item, String itemDesc, String itemParams) {
		try {
			//生成商品id
			//可以使用redis的自增长key，在没有redis之前使用时间+随机数策略生成
			Long itemId = IDUtils.genItemId();
			//补全不完整的字段
			item.setId(itemId);
			item.setStatus((byte) 1);
			Date date = new Date();
			item.setCreated(date);
			item.setUpdated(date);
			//把数据插入到商品表
			tbItemMapper.insert(item);
			//添加商品描述信息
			TaotaoResult result = insertItemDesc(itemId, itemDesc);
			if (result.getStatus() != 200) {
				throw new Exception();
			}
			//添加商品规格信息
			result = insertItemParamItem(itemId, itemParams);
			if (result.getStatus() != 200) {
				throw new Exception();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		return TaotaoResult.ok();
	}
	/**
	 * 添加商品描述
	 * <p>Title: insertItemDesc</p>
	 * <p>Description: </p>
	 * @param desc
	 */
	private TaotaoResult insertItemDesc(Long itemId, String desc) {
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		//把数据插入到商品描述表
		itemDescMapper.insert(itemDesc);
		return TaotaoResult.ok();
	}
	/***
	 * 添加商品分类规格信息
	 * @param itemId
	 * @param desc
	 * @return
	 */
	private TaotaoResult insertItemParamItem(Long itemId, String itemParams) {
		//添加商品规格
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemParams);
		itemParamItem.setCreated(new Date());
		itemParamItem.setUpdated(new Date());
		itemParamItemMapper.insert(itemParamItem);
		return TaotaoResult.ok();
	}
}
