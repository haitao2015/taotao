package com.taotao.rest.service;
/***
 * 内容管理服务 service
 * @author Administrator
 *
 */

import com.taotao.common.pojo.TaotaoResult;

public interface ContentService {

	public TaotaoResult getContentList(long cid) throws Exception;
}
