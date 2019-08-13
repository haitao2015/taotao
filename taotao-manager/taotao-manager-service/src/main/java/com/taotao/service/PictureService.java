package com.taotao.service;

import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.pojo.PictureResult;

/***
 * 图片管理 service
 * @author Administrator
 *
 */
public interface PictureService {
	/**
	 *  上传 图片文件功能实现
	 * @param uploadFile
	 * @return
	 * @throws Exception
	 */
	PictureResult uploadFile(MultipartFile uploadFile) throws Exception;

}
