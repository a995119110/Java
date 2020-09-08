package com.servicesImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controllers.UploadController;
import com.dao.UploadDao;
import com.entities.Score;
import com.services.UploadServices;

@Service
public class UploadServicesImpl implements UploadServices{

	private static final Logger log = Logger.getLogger(UploadController.class);
	
	private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3 ;
	private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40;
	private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50;	
	
	@Autowired
	UploadDao uploadDao;

	public String saveFile(HttpServletRequest request)
			throws ServletException, IOException {		

		log.debug("���銵aveFile�瘜�瑼��");

		// 撱箇���誑disk-base����隞�
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		// ����摰�
        // ������uffer蝛粹��
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		
		// 閮剖���摮�蔭
		// System.getProperty("java.io.tmpdir")������頂蝯望摮������
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		// 撱箇��������隞�
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		// ��憭扳��之撠�
		upload.setFileSizeMax(MAX_FILE_SIZE);

		// ��憭扯����(��瑼�之撠�”�����)
		upload.setSizeMax(MAX_REQUEST_SIZE);
		
		// UTF-8蝺函Ⅳ
		upload.setHeaderEncoding("UTF-8");
		
		//�摮楝敺�
		String uploadPath = "C:\\Users\\Marya\\Desktop\\test";
		
		// 憒����撠勗遣蝡�
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		String filePath = "";
		
		try {
			
			// �����隞園���(銝瑼��)
			List<FileItem> formItems = upload.parseRequest(request);
			// �������蝛箏���之撠�
			if (formItems != null && formItems.size() > 0) {
				for (FileItem item : formItems) {
					// ���蝝����
					if (!item.isFormField()) {
						// ������
						String fileName = item.getName();
						// 撱箇��瑼�楝敺� 嚗ile.separator='/'
						filePath = uploadPath + File.separator + fileName;
						// 撱箇����
						File storeFile = new File(filePath);
						// 頛詨瑼�摰�
						item.write(storeFile);
						log.debug("����瑼����� : " + fileName);
					}
				}
				log.debug("銝瑼��");
				log.debug("��頝臬� : " + filePath);
				return filePath;
			}
			return null;
		} catch (Exception e) {
			log.debug("銝瑼���隤歹�隤斤 : " + e.toString());
			filePath = "";
			return null;
		}
	}

	@SuppressWarnings("resource")
	public List<Score> getScoreList(String filePath) 
			throws FileNotFoundException, IOException {
		
		log.debug("���銵etScoreList�瘜圾�����");
		
		List<Score> scoreList = new ArrayList<Score>();
		
		try {
			
			//撱箇�����
			InputStream is = new FileInputStream(filePath);
			
			//撱箇�極雿
			Workbook workbook = null;
			
			//��鞈�”�撘�ls雿輻HSSF�lsx雿輻XSSF
			String extString = filePath.substring(filePath.lastIndexOf(".")+1);
			if("xls".equals(extString)){
				workbook = new HSSFWorkbook(is);
			}else if("xlsx".equals(extString)){
				workbook = new XSSFWorkbook(is);
			}
						
			//���洵銝��極雿”
			XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
						
			//����
			int rowNum=sheet.getLastRowNum();
			
			log.debug("鞈�� : " + rowNum);
			
			log.debug("���神�鞈��");
			
			for(int i=1;i<=rowNum;i++){
				
				//��������
				XSSFRow row = sheet.getRow(i);
				String number = row.getCell(0).toString();
				String name = row.getCell(1).toString();
				String chinese = row.getCell(2).toString();
				String math = row.getCell(3).toString();
				String english = row.getCell(4).toString();
				
				//������
				int number_int = (int)Float.parseFloat(number);
				int chinese_int = (int)Float.parseFloat(chinese);
				int math_int = (int)Float.parseFloat(math);
				int english_int = (int)Float.parseFloat(english);
				
				//撱箇�core�隞�
				Score score = new Score();
				score.setNumber(number_int);
				score.setName(name);
				score.setChinese(chinese_int);
				score.setMath(math_int);
				score.setEnglish(english_int);
						
				scoreList.add(score);
			}
			workbook.close();
			log.debug("����������");
			
			return scoreList;
			
		} catch (Exception e) {
			log.debug("霈�����”���隤歹�隤斤 : " + e.toString());
			return null;
		}
		
	}
	
	public void saveAndUpdate(List<Score> scoreList) 
			throws ServletException, IOException {
		
		try {
			log.debug("���銵aveAndUpdate�瘜�鞈�澈");
			uploadDao.saveOrUpdate(scoreList);
			log.debug("����鞈�澈");
		} catch (Exception e) {
			log.debug("銝鞈�澈���隤歹�隤斤 : " + e.toString());
		}

	}
	
	
}
