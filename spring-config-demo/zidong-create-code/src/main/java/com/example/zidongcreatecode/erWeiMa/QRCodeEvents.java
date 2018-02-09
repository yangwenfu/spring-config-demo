package com.example.zidongcreatecode.erWeiMa;


import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

/**
 * 利用Google的ZXing工具包，生成和解析二维码图片
 * <p>
 * Created by Eric on 2017/2/15.
 */
public class QRCodeEvents {

	public static void main(String[] args) {

		String text =BufferedImageLuminanceSource.generateNumCode(12);  //随机生成的12位验证码
		System.out.println("随机生成的12位验证码为： " + text);
		int width = 100;    //二维码图片的宽
		int height = 100;   //二维码图片的高
		String format = "png";  //二维码图片的格式

		try {
			//生成二维码图片，并返回图片路径
			String pathName = BufferedImageLuminanceSource.generateQRCode(text, width, height, format);
			System.out.println("生成二维码的图片路径： " + pathName);

			String content = BufferedImageLuminanceSource.parseQRCode(pathName);
			System.out.println("解析出二维码的图片的内容为： " + content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}