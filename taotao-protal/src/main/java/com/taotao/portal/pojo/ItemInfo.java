package com.taotao.portal.pojo;

import com.taotao.pojo.TbItem;

public class ItemInfo extends TbItem{

	private String[] images;

	public String[] getImages() {
		String image=getImage();
		if(image!=null) {
			String[] images=image.split(",");
			return images;
		}
		
		return null;
	}

	
	
}
