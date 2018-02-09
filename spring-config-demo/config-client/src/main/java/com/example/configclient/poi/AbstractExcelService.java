package com.example.configclient.poi;

import org.springframework.web.servlet.view.document.AbstractExcelView;


import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public abstract class AbstractExcelService extends AbstractExcelView {
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractExcelService.class);
	private Map<Class, HSSFCellStyle> CELLSTYLE = new ConcurrentHashMap();

	public AbstractExcelService() {
	}

	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String fileName = String.valueOf(model.get("fileName"));
		String tempPath = String.valueOf(model.get("tempPath"));
		InputStream ins = AbstractExcelService.class.getClassLoader().getResourceAsStream(tempPath);
		workbook = new HSSFWorkbook(ins);

		try {
			this.buildExcelData(model, workbook);
			response.reset();
			response.setContentType("APPLICATION/OCTET-STREAM");
			String agent = request.getHeader("USER-AGENT");
			if (agent != null && agent.toLowerCase().indexOf("firefox") > 0) {
				response.setHeader("Content-Disposition", "attachment;filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8"));
			} else {
				response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
			}

			OutputStream out = response.getOutputStream();
			workbook.write(out);
			out.flush();
			out.close();
		} catch (Exception var10) {
			LOGGER.error("error in export xls", var10);
		}

	}

	protected abstract void buildExcelData(Map<String, Object> var1, HSSFWorkbook var2) throws Exception;

	protected HSSFCellStyle getStyle(Class type, HSSFWorkbook workbook) {
		if (this.CELLSTYLE.containsKey(type)) {
			return (HSSFCellStyle)this.CELLSTYLE.get(type);
		} else {
			HSSFFont font = workbook.createFont();
			font.setFontHeightInPoints((short)12);
			font.setFontName("宋体");
			HSSFCellStyle style = workbook.createCellStyle();
			style.setBorderBottom((short)1);
			style.setBottomBorderColor((short)8);
			style.setBorderLeft((short)1);
			style.setLeftBorderColor((short)8);
			style.setBorderRight((short)1);
			style.setRightBorderColor((short)8);
			style.setBorderTop((short)1);
			style.setTopBorderColor((short)8);
			style.setFont(font);
			style.setWrapText(false);
			style.setAlignment((short)1);
			style.setVerticalAlignment((short)1);
			if (Date.class.isAssignableFrom(type)) {
				style.setDataFormat(workbook.createDataFormat().getFormat("yyyy-mm-dd hh:mm:ss"));
			}

			this.CELLSTYLE.put(type, style);
			return style;
		}
	}

	protected HSSFCellStyle getLeftSideStyle(HSSFWorkbook workbook) {
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short)12);
		font.setFontName("宋体");
		HSSFCellStyle style = workbook.createCellStyle();
		style.setBorderBottom((short)1);
		style.setBottomBorderColor((short)8);
		style.setBorderLeft((short)1);
		style.setLeftBorderColor((short)8);
		style.setBorderRight((short)1);
		style.setRightBorderColor((short)8);
		style.setBorderTop((short)1);
		style.setTopBorderColor((short)8);
		style.setFont(font);
		style.setWrapText(false);
		style.setAlignment((short)1);
		style.setVerticalAlignment((short)1);
		return style;
	}

	protected HSSFCellStyle getRightSideStyle(HSSFWorkbook workbook) {
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short)12);
		font.setFontName("宋体");
		HSSFCellStyle style = workbook.createCellStyle();
		style.setBorderBottom((short)1);
		style.setBottomBorderColor((short)8);
		style.setBorderLeft((short)1);
		style.setLeftBorderColor((short)8);
		style.setBorderRight((short)1);
		style.setRightBorderColor((short)8);
		style.setBorderTop((short)1);
		style.setTopBorderColor((short)8);
		style.setFont(font);
		style.setWrapText(false);
		style.setAlignment((short)3);
		style.setVerticalAlignment((short)1);
		return style;
	}

	protected HSSFCellStyle getLeftTopStyle(HSSFWorkbook workbook) {
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short)12);
		font.setFontName("宋体");
		HSSFCellStyle style = workbook.createCellStyle();
		style.setBorderBottom((short)1);
		style.setBottomBorderColor((short)9);
		style.setBorderLeft((short)1);
		style.setLeftBorderColor((short)8);
		style.setBorderRight((short)1);
		style.setRightBorderColor((short)9);
		style.setBorderTop((short)1);
		style.setTopBorderColor((short)8);
		style.setFont(font);
		style.setWrapText(false);
		style.setAlignment((short)1);
		style.setVerticalAlignment((short)1);
		return style;
	}

	protected HSSFCellStyle getLeftBottomStyle(HSSFWorkbook workbook) {
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short)12);
		font.setFontName("宋体");
		HSSFCellStyle style = workbook.createCellStyle();
		style.setBorderBottom((short)1);
		style.setBottomBorderColor((short)8);
		style.setBorderLeft((short)1);
		style.setLeftBorderColor((short)8);
		style.setBorderRight((short)1);
		style.setRightBorderColor((short)9);
		style.setBorderTop((short)1);
		style.setTopBorderColor((short)9);
		style.setFont(font);
		style.setWrapText(false);
		style.setAlignment((short)1);
		style.setVerticalAlignment((short)1);
		return style;
	}

	protected HSSFCellStyle getRightTopStyle(HSSFWorkbook workbook) {
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short)12);
		font.setFontName("宋体");
		HSSFCellStyle style = workbook.createCellStyle();
		style.setBorderBottom((short)1);
		style.setBottomBorderColor((short)9);
		style.setBorderLeft((short)1);
		style.setLeftBorderColor((short)9);
		style.setBorderRight((short)1);
		style.setRightBorderColor((short)8);
		style.setBorderTop((short)1);
		style.setTopBorderColor((short)8);
		style.setFont(font);
		style.setWrapText(false);
		style.setAlignment((short)1);
		style.setVerticalAlignment((short)1);
		return style;
	}

	protected HSSFCellStyle getRightBottomStyle(HSSFWorkbook workbook) {
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short)12);
		font.setFontName("宋体");
		HSSFCellStyle style = workbook.createCellStyle();
		style.setBorderBottom((short)1);
		style.setBottomBorderColor((short)8);
		style.setBorderLeft((short)1);
		style.setLeftBorderColor((short)9);
		style.setBorderRight((short)1);
		style.setRightBorderColor((short)8);
		style.setBorderTop((short)1);
		style.setTopBorderColor((short)9);
		style.setFont(font);
		style.setWrapText(false);
		style.setAlignment((short)1);
		style.setVerticalAlignment((short)1);
		return style;
	}

	protected HSSFCellStyle getTopStyle(HSSFWorkbook workbook) {
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short)12);
		font.setFontName("宋体");
		HSSFCellStyle style = workbook.createCellStyle();
		style.setBorderBottom((short)1);
		style.setBottomBorderColor((short)9);
		style.setBorderLeft((short)1);
		style.setLeftBorderColor((short)9);
		style.setBorderRight((short)1);
		style.setRightBorderColor((short)9);
		style.setBorderTop((short)1);
		style.setTopBorderColor((short)8);
		style.setFont(font);
		style.setWrapText(false);
		style.setAlignment((short)1);
		style.setVerticalAlignment((short)1);
		return style;
	}

	protected HSSFCellStyle getRightStyle(HSSFWorkbook workbook) {
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short)12);
		font.setFontName("宋体");
		HSSFCellStyle style = workbook.createCellStyle();
		style.setBorderBottom((short)1);
		style.setBottomBorderColor((short)9);
		style.setBorderLeft((short)1);
		style.setLeftBorderColor((short)9);
		style.setBorderRight((short)1);
		style.setRightBorderColor((short)8);
		style.setBorderTop((short)1);
		style.setTopBorderColor((short)9);
		style.setFont(font);
		style.setWrapText(false);
		style.setAlignment((short)1);
		style.setVerticalAlignment((short)1);
		return style;
	}

	protected HSSFCellStyle getLeftStyle(HSSFWorkbook workbook) {
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short)12);
		font.setFontName("宋体");
		HSSFCellStyle style = workbook.createCellStyle();
		style.setBorderBottom((short)1);
		style.setBottomBorderColor((short)9);
		style.setBorderLeft((short)1);
		style.setLeftBorderColor((short)8);
		style.setBorderRight((short)1);
		style.setRightBorderColor((short)9);
		style.setBorderTop((short)1);
		style.setTopBorderColor((short)9);
		style.setFont(font);
		style.setWrapText(false);
		style.setAlignment((short)1);
		style.setVerticalAlignment((short)1);
		return style;
	}

	protected HSSFCellStyle getBottomStyle(HSSFWorkbook workbook) {
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short)12);
		font.setFontName("宋体");
		HSSFCellStyle style = workbook.createCellStyle();
		style.setBorderBottom((short)1);
		style.setBottomBorderColor((short)8);
		style.setBorderLeft((short)1);
		style.setLeftBorderColor((short)9);
		style.setBorderRight((short)1);
		style.setRightBorderColor((short)9);
		style.setBorderTop((short)1);
		style.setTopBorderColor((short)9);
		style.setFont(font);
		style.setWrapText(false);
		style.setAlignment((short)3);
		style.setVerticalAlignment((short)1);
		return style;
	}

	protected String getConvert(BigDecimal bigDecimal) {
		DecimalFormat d1 = new DecimalFormat("#,##0.00");
		return d1.format(bigDecimal);
	}
}

