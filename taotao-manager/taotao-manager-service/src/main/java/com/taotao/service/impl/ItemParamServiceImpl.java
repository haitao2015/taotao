package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.service.ItemParamService;
import com.taotao.utils.IDUtils;

/***
 * 商品规则参数 impl
 * 
 * @author Administrator
 *
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper itemParamMapper;

	@Override
	public EUDataGridResult getItemParamList(int page, int rows) {
		
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		PageHelper.startPage(page, rows);
		
		TbItemParamExample example = new TbItemParamExample();
		//查询列表
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		result.setRows(list);
		// 取记录总条数
		PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
		
	}
	@Override
	public TaotaoResult getItemParamByCid(long cid) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		//判断是否查询到结果
		if (list != null && list.size() > 0) {
			return TaotaoResult.ok(list.get(0));
		}
		
		return TaotaoResult.ok();
	}
	@Override
	public TaotaoResult insertItemParam(TbItemParam itemParam) {
		//补全pojo
		//生成商品id
		//可以使用redis的自增长key，在没有redis之前使用时间+随机数策略生成
		Long id = IDUtils.genItemId();
		//补全不完整的字段
		itemParam.setId(id);
		itemParam.setItemCatId(itemParam.getItemCatId());
		itemParam.setParamData(itemParam.getParamData());
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		//插入到规格参数模板表
		itemParamMapper.insert(itemParam);
		return TaotaoResult.ok();
	}
}
