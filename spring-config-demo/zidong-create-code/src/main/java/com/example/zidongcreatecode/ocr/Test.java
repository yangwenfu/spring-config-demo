package com.example.zidongcreatecode.ocr;

import java.io.File;

/**
 * 对比第一张图片，是不是很完美~哈哈 ，当然了如果你只需要实现验证码的读写，那么上面就足够了。下面继续普及图像处理的知识。
 */
public class Test {
	public static void main(String[] args) {
		try {

			File testDataDir = new File("testdata");
			System.out.println(testDataDir.listFiles().length);
			int i = 0;
			for (File file : testDataDir.listFiles()) {
				i++;
				String recognizeText = new OCRHelper().recognizeText(file);
				System.out.print(recognizeText + "\t");

				if (i % 5 == 0) {
					System.out.println();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}