package com.taotao.controller;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import com.taotao.utils.FtpUtil;

public class FtpTest {

	/***
	 * 
	 * @throws Exception
	 */
	//@Test
	public void testFtpClient() throws Exception {
		FTPClient ftpClient = new FTPClient();
		ftpClient.connect("192.168.232.201", 21);
		ftpClient.login("root", "daiht2015");
		String pathname = "D:\\photo\\_S9A1866.JPG";
		FileInputStream fileStream = new FileInputStream(new File(pathname));
		String basePath = "/var/ftp/pub";
		ftpClient.changeWorkingDirectory(basePath);
		ftpClient.storeFile("myhello1.jpg", fileStream);
		ftpClient.dele("myhello.jpg");
		boolean flag= ftpClient.logout();
		System.out.println("上传："+flag);
	}
/***
 * FtpUtil工具类 测试上传
 * @throws Exception
 */
	@Test
	public void testFtpUtil() throws Exception {
		String pathname = "D:\\photo\\_S9A1866.JPG";
		FileInputStream f = new FileInputStream(new File(pathname));
		String basePath = "/var/ftp/pub";
		String filePath = "/image/2019/08/10";
		String filename = "hello6.jpg";
		boolean flag = FtpUtil.uploadFile("192.168.232.201", 21, "root", "daiht2015", basePath, 
				filePath, filename,f);
		System.out.println("上传："+flag);
	}
}
