package com.example.configclient.poi;



import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DataTablesExcelService extends AbstractExcelService {
	private static final Logger LOGGER = LoggerFactory.getLogger(DataTablesExcelService.class);

	public DataTablesExcelService() {
	}

	public void buildExcelData(Map<String, Object> map, HSSFWorkbook workbook) throws Exception {
		List dataList = (List)map.get("data");
		HSSFSheet sheet = workbook.getSheetAt(0);

		for(int i = 1; i <= dataList.size(); ++i) {
			HSSFRow row = sheet.createRow(i);
			Object data = dataList.get(i - 1);
			Field[] fields = data.getClass().getDeclaredFields();
			int j = 0;
			Field[] var10 = fields;
			int var11 = fields.length;

			for(int var12 = 0; var12 < var11; ++var12) {
				Field field = var10[var12];
				if (!"serialVersionUID".equals(field.getName())) {
					HSSFCell cell = row.createCell(j, 1);
					cell.setCellValue("");

					try {
						if (this.setCellValue(field, data, cell)) {
							++j;
						}
					} catch (Exception var16) {
						LOGGER.error("set cell value failed", var16);
						throw new Exception("");
					}
				}
			}
		}

	}

	private boolean setCellValue(Field field, Object data, HSSFCell cell) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> clazz = data.getClass();
		Class<?> type = field.getType();
		cell.setCellStyle(this.getStyle(type, cell.getSheet().getWorkbook()));
		Method getter = ReflectionUtil.getGetter(clazz, field);
		if (null == getter) {
			return false;
		} else {
			String fieldValue;
			if (String.class.isAssignableFrom(type)) {
				fieldValue = (String)getter.invoke(data);
				if (null != fieldValue) {
					cell.setCellValue(fieldValue);
				}
			} else if (BigDecimal.class.isAssignableFrom(type)) {
//				BigDecimal fieldValue = (BigDecimal)getter.invoke(data);
//				if (null != fieldValue) {
//					cell.setCellValue(fieldValue.doubleValue());
//				}
			} else if (Date.class.isAssignableFrom(type)) {
//				Date fieldValue = (Date)getter.invoke(data);
//				if (null != fieldValue) {
//					cell.setCellValue(fieldValue);
//				}
//			} else if (PageEnum.class.isAssignableFrom(type)) {
//				PageEnum fieldValue = (PageEnum)getter.invoke(data);
//				if (null != fieldValue) {
//					cell.setCellValue(fieldValue.getText());
//				}
			} else {
				if (!Number.class.isAssignableFrom(type)) {
					throw new UnsupportedOperationException("field has getter but type is unknown");
				}

				fieldValue = String.valueOf(getter.invoke(data));
				if (null != fieldValue) {
					cell.setCellValue(fieldValue);
				}
			}

			return true;
		}
	}
}
