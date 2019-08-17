package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;

public interface RedisService {
	/***
	 * 内容数据同步
	 * @param contentCid
	 * @return
	 */
	TaotaoResult syncContent(long contentCid);

}
