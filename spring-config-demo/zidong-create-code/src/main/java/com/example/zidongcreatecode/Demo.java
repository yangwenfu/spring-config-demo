package com.example.zidongcreatecode;

import java.io.File;
import java.io.IOException;

/**
 *Create by yangwenfu on 2018/2/1
 */
public class Demo {
	public static void main(String[] args) {
		try {
			//图片文件：此图片是需要被识别的图片
			File file = new File("E:\\4.jpg");
			String recognizeText = new OCRHelper().recognizeText(file);
			System.out.print(recognizeText + "\t");

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
