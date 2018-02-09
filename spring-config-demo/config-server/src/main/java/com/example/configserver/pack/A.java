package com.example.configserver.pack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FilenameUtils;

/**
 *Create by yangwenfu on 2018/1/24
 */
public class A {
	public static void main(String[] args) throws IOException {
		File file = new File("static/DKL01001.vm");
		//生成字符缓冲流对象com.example.configserver
//		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("config-server/src/main/resource/static/DKL01001.vm")));
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("F:\\myProject\\spring-config-demo\\config-server\\src\\main\\java\\com\\example\\configserver\\DKL01001.vm")));
		String str;

		String extension = FilenameUtils.getExtension("F:\\myProject\\spring-config-demo\\config-server\\src\\main\\java\\com\\example\\configserver\\DKL01001.vm");
		Path path = Paths.get("/", "fsdsdf.vm");
		//一次性读取一行
		StringBuilder s = new StringBuilder();
		while ((str = reader.readLine()) != null) {
			//System.out.println(str);
			s.append(str).append("\r\n");
		}
		System.out.println(s.toString());

		//关闭流
		reader.close();
	}
}

