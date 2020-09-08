package com.services;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.entities.Score;

public interface DownloadServices {
	public List<Score> GetScore() throws Exception;
	public XSSFWorkbook GetXFWB(XSSFWorkbook XFWB , String date) throws Exception;
	public XSSFWorkbook SaveFile(String path , XSSFWorkbook XFWB , String date) throws Exception;
}
