package com.example.zidongcreatecode.erWeiMa;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * 用于二维码的解析，由Google提供。
 *
 * Created by Eric on 2017/2/15.
 */
public final class BufferedImageLuminanceSource extends LuminanceSource {

	private final BufferedImage image;
	private final int left;
	private final int top;

	public BufferedImageLuminanceSource(BufferedImage image) {
		this(image, 0, 0, image.getWidth(), image.getHeight());
	}

	public BufferedImageLuminanceSource(BufferedImage image, int left, int top, int width, int height) {
		super(width, height);

		int sourceWidth = image.getWidth();
		int sourceHeight = image.getHeight();
		if (left + width > sourceWidth || top + height > sourceHeight) {
			throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
		}

		for (int y = top; y < top + height; y++) {
			for (int x = left; x < left + width; x++) {
				if ((image.getRGB(x, y) & 0xFF000000) == 0) {
					image.setRGB(x, y, 0xFFFFFFFF); // = white
				}
			}
		}

		this.image = new BufferedImage(sourceWidth, sourceHeight, BufferedImage.TYPE_BYTE_GRAY);
		this.image.getGraphics().drawImage(image, 0, 0, null);
		this.left = left;
		this.top = top;
	}

	@Override
	public byte[] getRow(int y, byte[] row) {
		if (y < 0 || y >= getHeight()) {
			throw new IllegalArgumentException("Requested row is outside the image: " + y);
		}
		int width = getWidth();
		if (row == null || row.length < width) {
			row = new byte[width];
		}
		image.getRaster().getDataElements(left, top + y, width, 1, row);
		return row;
	}

	@Override
	public byte[] getMatrix() {
		int width = getWidth();
		int height = getHeight();
		int area = width * height;
		byte[] matrix = new byte[area];
		image.getRaster().getDataElements(left, top, width, height, matrix);
		return matrix;
	}

	@Override
	public boolean isCropSupported() {
		return true;
	}

	@Override
	public LuminanceSource crop(int left, int top, int width, int height) {
		return new BufferedImageLuminanceSource(image, this.left + left, this.top + top, width, height);
	}

	@Override
	public boolean isRotateSupported() {
		return true;
	}

	@Override
	public LuminanceSource rotateCounterClockwise() {

		int sourceWidth = image.getWidth();
		int sourceHeight = image.getHeight();

		AffineTransform transform = new AffineTransform(0.0, -1.0, 1.0, 0.0, 0.0, sourceWidth);

		BufferedImage rotatedImage = new BufferedImage(sourceHeight, sourceWidth, BufferedImage.TYPE_BYTE_GRAY);

		Graphics2D g = rotatedImage.createGraphics();
		g.drawImage(image, transform, null);
		g.dispose();

		int width = getWidth();
		return new BufferedImageLuminanceSource(rotatedImage, top, sourceWidth - (left + width), getHeight(), width);
	}

	/**
	 * 根据内容，生成指定宽高、指定格式的二维码图片
	 *
	 * @param text   内容
	 * @param width  宽
	 * @param height 高
	 * @param format 图片格式
	 * @return 生成的二维码图片路径
	 * @throws Exception
	 */
	public static String generateQRCode(String text, int width, int height, String format) throws Exception {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
		String pathName = "F:/new.png";
		File outputFile = new File(pathName);
		MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
		return pathName;
	}
	/**
	 * 随机生成指定长度的验证码
	 *
	 * @param length 验证码长度
	 * @return 生成的验证码
	 */
	public static String generateNumCode(int length) {
		String val = "";
		String charStr = "char";
		String numStr = "num";
		Random random = new Random();

		//参数length，表示生成几位随机数
		for (int i = 0; i < length; i++) {

			String charOrNum = random.nextInt(2) % 2 == 0 ? charStr : numStr;
			//输出字母还是数字
			if (charStr.equalsIgnoreCase(charOrNum)) {
				//输出是大写字母还是小写字母
				int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val += (char) (random.nextInt(26) + temp);
			} else if (numStr.equalsIgnoreCase(charOrNum)) {
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}

	/**
	 * 解析指定路径下的二维码图片
	 *
	 * @param filePath 二维码图片路径
	 * @return
	 */
	public static String parseQRCode(String filePath) {
		String content = "";
		try {
			File file = new File(filePath);
			BufferedImage image = ImageIO.read(file);
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			Binarizer binarizer = new HybridBinarizer(source);
			BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
			Map<DecodeHintType, Object> hints = new HashMap<>();
			hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
			MultiFormatReader formatReader = new MultiFormatReader();
			Result result = formatReader.decode(binaryBitmap, hints);

			System.out.println("result 为：" + result.toString());
			System.out.println("resultFormat 为：" + result.getBarcodeFormat());
			System.out.println("resultText 为：" + result.getText());
			//设置返回值
			content = result.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}
}
