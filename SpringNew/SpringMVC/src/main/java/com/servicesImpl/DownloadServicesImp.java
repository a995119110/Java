package com.servicesImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controllers.DownloadController;
import com.dao.DownloadDao;
import com.entities.Score;
import com.services.DownloadServices;

@Service
public class DownloadServicesImp implements DownloadServices{
	
	@Autowired
	DownloadDao download;
	
	public static final Logger log = Logger.getLogger(DownloadController.class);
	
	
	public List<Score> GetScore() throws Exception {
		return download.GetScore();
	}
	
	public XSSFWorkbook GetXFWB(XSSFWorkbook XFWB ,String date) throws Exception {
		
		List<Score> LScore = this.GetScore();
		
		XSSFSheet sheet = XFWB.createSheet(date);
		XSSFRow row = null;
		XSSFCell cell = null;
		
		try {
			int i = 0;
			for (Score s : LScore) {
				row = sheet.createRow(i++);
				for (int j = 0 ; j < 5 ; j++ ) {
					switch (j) {
					case 0:
						cell = row.createCell(j);
						cell.setCellValue(s.getNumber());
						break;
					case 1:
						cell = row.createCell(j);
						cell.setCellValue(s.getName());
						break;
					case 2:
						cell = row.createCell(j);
						cell.setCellValue(s.getChinese());
						break;
					case 3:
						cell = row.createCell(j);
						cell.setCellValue(s.getMath());
						break;
					case 4:
						cell = row.createCell(j);
						cell.setCellValue(s.getEnglish());
						break;
					}
				
				
				}
			
			
			}
		}catch (Exception e) {
			log.error("鞈�澈瑼�撓��隤�");
		}
		return XFWB;
	}
	
	public XSSFWorkbook SaveFile(String path , XSSFWorkbook XFWB , String date) throws Exception {
		
		File uploadDir = new File(path);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		try {
			path += date + ".xlsx";
			FileOutputStream FOS = new FileOutputStream(path);
			XFWB.write(FOS);
			FOS.close();
		}catch (Exception e) {
			log.error("頝臬�����");
		}
		
		return null;
	}
	
	
}
