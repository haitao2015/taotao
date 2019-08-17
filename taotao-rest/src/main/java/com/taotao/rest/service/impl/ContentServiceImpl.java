package com.taotao.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ContentService;
import com.taotao.utils.JsonUtils;

@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private TbContentMapper contentMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;

	@Override
	public TaotaoResult getContentList(long cid) throws Exception {
		// 从缓存中取内容
		try {
			//缓存逻辑，先判断缓存中是否有内容
			String result =jedisClient.hget(INDEX_CONTENT_REDIS_KEY, "" + cid + "");
			if(!StringUtils.isBlank(result)) {
				//把json字符串转换成对象列表
				List<TbContent> jsonList=	JsonUtils.jsonToList(result, TbContent.class);
				return TaotaoResult.ok(jsonList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		TbContentExample example = new TbContentExample();
		// 添加条件
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(cid);
		List<TbContent> list = contentMapper.selectByExample(example);
		// 向缓存中添加内容
		try {
			// 把list转的确成字符串
			String cacheString = JsonUtils.objectToJson(list);
			jedisClient.hset(INDEX_CONTENT_REDIS_KEY, "" + cid + "", cacheString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TaotaoResult.ok(list);
	}

}
