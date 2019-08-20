package com.taotao.portal.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

/***
 * 购物车
 * @author Administrator
 *
 */
public class CartItem {
	private Long id;

    private String title;
    private String image;
	private Long num;
	private Long price;
	private String[] images;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getNum() {
		return num;
	}
	public void setNum(Long num) {
		this.num = num;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	@JsonIgnore
	public String[] getImages() {
		if(image!=null) {
			images=image.split(",");
			return images;
		}
		return null;
	}
}
