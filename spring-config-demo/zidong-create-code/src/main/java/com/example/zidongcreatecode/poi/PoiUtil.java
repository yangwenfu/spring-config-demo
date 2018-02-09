package com.example.zidongcreatecode.poi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 *Create by yangwenfu on 2018/2/2
 * 从数据库查出的内容 先写到本地  在写出的浏览器
 */
public class PoiUtil {

	public static void main(String[] args) throws IOException {

//	}
//	public String export(List list,HttpServletResponse response) throws IOException {
		//创建HSSFWorkbook对象(excel的文档对象)
		HSSFWorkbook wb = new HSSFWorkbook();
		//建立新的sheet对象（excel的表单）
		HSSFSheet sheet = wb.createSheet();

		// 创建单元格样式
		HSSFCellStyle cellStyle = wb.createCellStyle();
		// 指定单元格居中对齐
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		// 指定单元格垂直居中对齐
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		// 指定当单元格内容显示不下时自动换行
		cellStyle.setWrapText(true);

		// 设置单元格字体
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setFontHeight((short) 200);
		cellStyle.setFont(font);
		cellStyle.setWrapText(false);
		cellStyle.setHidden(false);

		//在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row1=sheet.createRow(0);
		//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		HSSFCell cell=row1.createCell(0);
		//设置单元格内容
		cell.setCellValue("学员考试成绩一览表");
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));
		//在sheet里创建第二行
		HSSFRow row2=sheet.createRow(1);

		//创建单元格并设置单元格内容
		row2.createCell(0).setCellValue("姓名");
		row2.createCell(1).setCellValue("班级");
		row2.createCell(2).setCellValue("笔试成绩");
		row2.createCell(3).setCellValue("机试成绩");
		//在sheet里创建第三行
		HSSFRow row3=sheet.createRow(2);
		row3.createCell(0).setCellValue("李明");
		row3.createCell(1).setCellValue("As178");
		row3.createCell(2).setCellValue(87);
		row3.createCell(3).setCellValue(78);
		FileOutputStream exportXls = new FileOutputStream("E://工单信息表.xls");
		wb.write(exportXls);
		exportXls.close();
		System.out.println("导出成功!");

		wb.write(exportXls);


	}


	public void download(HttpServletResponse response, String path,String fileName) throws IOException {
		// 读到流中
		InputStream in = new FileInputStream(path);// 文件的存放路径
		// 设置输出的格式
		response.reset();
		response.setContentType("application/msexcel");
		response.addHeader("Content-Disposition", "attachment; filename=\"+"+fileName+".xls\"");

		OutputStream out = response.getOutputStream();
		// 循环取出流中的数据
		byte[] b = new byte[100];
		int len;
		try {
			while ((len = in.read(b)) > 0)
				out.write(b, 0, len);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
