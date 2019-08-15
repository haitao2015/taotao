package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;

/***
 * 商品分类规则参数SERVICE
 * @author Administrator
 *
 */
public interface ItemParamService {

/***
 * 商品分类规则参数列表
 * @param page
 * @param rows
 * @return
 */
public EUDataGridResult getItemParamList(int page, int rows);
/***
 * 查询商品分类规则信息
 * @param cid
 * @return
 */
TaotaoResult getItemParamByCid(long cid);
/***
 * 增加商品分类规则信息
 * @param itemParam
 * @return
 */
TaotaoResult insertItemParam(TbItemParam itemParam);
}
