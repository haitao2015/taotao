package com.taotao.common.pojo;

import java.util.List;

public class SearchResult {
	//总记录数据
	private Long recordCount;
	//商品列表
	private List<Item> itemList;
	//总页数
	private Long pageCount;
	//当前页
	private Long curPage;
	
	public Long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(Long recordCount) {
		this.recordCount = recordCount;
	}
	public List<Item> getItemList() {
		return itemList;
	}
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
	public Long getPageCount() {
		return pageCount;
	}
	public void setPageCount(Long pageCount) {
		this.pageCount = pageCount;
	}
	public Long getCurPage() {
		return curPage;
	}
	public void setCurPage(Long page) {
		this.curPage = page;
	}
	
}
