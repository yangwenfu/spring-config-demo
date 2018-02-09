package com.example.configclient.poi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *Create by yangwenfu on 2018/2/2
 */
@RestController
public class ExportController {

	@GetMapping("/export")
	public ModelAndView export(){
		List data = new ArrayList();
		data.add("a");
		data.add("a");
		data.add("a");
		data.add("a");
		data.add("a");
		data.add("a");
		data.add("a");
		data.add("a");
		data.add("a");
		data.add("a");
		data.add("a");
		data.add("a");
		data.add("a");
		Map<String, Object> model = new HashedMap();
		model.put("data", data);
		model.put("fileName", "分销员签到记录.xls");
		model.put("tempPath", "E://分销员签到记录.xls");
		DataTablesExcelService dataTablesExcelService = new DataTablesExcelService();
		return new ModelAndView(dataTablesExcelService, model);

	}
}
