package com.taotao.portal.service;

import com.taotao.common.pojo.SearchResult;

/***
 * 搜索 Service solr
 * @author Administrator
 *
 */
public interface SearchService {

	SearchResult search(String queryString, int page);

}
