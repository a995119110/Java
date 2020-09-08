package com.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.services.DownloadServices;

@Controller
@RequestMapping(value="download")
public class DownloadController {

	private static final Logger log = Logger.getLogger(DownloadController.class);
	
	@Autowired
	DownloadServices downloadservices;
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ModelAndView getPage() {
		ModelAndView view = new ModelAndView("download");
		log.debug("開啟Download頁面");
		return view;
		
	}
	
	@SuppressWarnings("all")
	@RequestMapping(value = "/downloadFile", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> DownloadFile()throws Exception {
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		Date DNow = new Date( );
	      SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd");
		
		String downloadpath = "C:\\Users\\Administrator\\Desktop\\test\\" ;
		
		XSSFWorkbook XFWB = new XSSFWorkbook();
		
		try {
			XFWB = downloadservices.GetXFWB(XFWB , ft.format(DNow).toString());
		}catch (Exception e) {
			map.put("message", "出現了異常錯誤 關於無法得到xlsx檔案");
			return map;
		}
		try {
			downloadservices.SaveFile(downloadpath, XFWB, ft.format(DNow));
			map.put("message", "已成功存取資料，預設路徑為 " + downloadpath);
		}catch(Exception e) {
			map.put("message", "關於無法在路徑中存取資料");
			return map;
		}
		
		XFWB.close();
		
		return map;
	}
	
	
}
