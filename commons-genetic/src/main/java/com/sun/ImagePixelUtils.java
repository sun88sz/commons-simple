package com.sun;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePixelUtils {
	/**
	 * 读取一张图片的RGB值
	 * 
	 */
	public static int[][] getImagePixel(File file) {

		BufferedImage bi = null;
		try {
			bi = ImageIO.read(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int width = bi.getWidth();
		int height = bi.getHeight();
		int minx = bi.getMinX();
		int miny = bi.getMinY();

		int[][] picArray = new int[width][height];

		for (int i = minx; i < width; i++) {
			for (int j = miny; j < height; j++) {
				// fffefefe
				// 将一个数字转换为RGB数字
				int pixel = bi.getRGB(i, j); 
				picArray[i][j] = pixel & 0xffffff;
			}
		}
		return picArray;
	}

	/**
	 * 根据像素打印图片
	 * 
	 * @param width
	 * @param height
	 * @param rpgArr
	 * @param suffix
	 *            .jpg
	 * @param file
	 */
	public static void printImageByPixel(int width, int height, int[][] rpgArr, String suffix, File file) {
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				bi.setRGB(i, j, rpgArr[i][j]);
			}
		}
		try {
			ImageIO.write(bi, suffix, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 返回屏幕色彩值
	 * 函数返回值为颜色的RGB值。
	 *
	 * @param x
	 * @param y
	 * @return
	 * @throws AWTException
	 */
	public static int getScreenPixel(int x, int y) throws AWTException { 
		// java.awt.image包中的类，可以用来抓取屏幕，即截屏。
		Robot rb = null;
		rb = new Robot();
		// 获取缺省工具包
		Toolkit tk = Toolkit.getDefaultToolkit();
		// 屏幕尺寸规格
		Dimension di = tk.getScreenSize(); 
		System.out.println(di.width);
		System.out.println(di.height);
		Rectangle rec = new Rectangle(0, 0, di.width, di.height);
		BufferedImage bi = rb.createScreenCapture(rec);
		int pixelColor = bi.getRGB(x, y);

		// pixelColor的值为负，经过实践得出：加上颜色最大值就是实际颜色值。
		return 16777216 + pixelColor; 
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		File file = new File("commons-genetic/src/main/resources/firefox64.jpg");

		System.out.println(file.getAbsoluteFile() + "" + file.length());

		int[][] imagePixel = ImagePixelUtils.getImagePixel(file);

		File fileW = new File("commons-genetic/src/main/resources/firefoxTest.jpg");
		printImageByPixel(64, 64, imagePixel, "jpg", fileW);

	}
}
