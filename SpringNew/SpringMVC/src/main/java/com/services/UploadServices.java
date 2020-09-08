package com.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.entities.Score;

public interface UploadServices {
	public void saveAndUpdate(List<Score> scoreList) 
			throws ServletException, IOException;
	public String saveFile(HttpServletRequest request)
			throws ServletException, IOException;
	public List<Score> getScoreList(String filePath) 
			throws FileNotFoundException, IOException;
}
