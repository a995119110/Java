package com.daoImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UploadDao;
import com.entities.Score;

@Repository
@Transactional
public class UploadDaoImpl implements UploadDao{
	
	private static final Logger log = Logger.getLogger(UploadDaoImpl.class);
	
	@Autowired
	SessionFactory session;
	
	public void saveOrUpdate(List<Score> scoreList){
		
		try {
			for (Score score : scoreList){
				session.getCurrentSession().saveOrUpdate(score);
			}
		} catch (HibernateException e) {
			log.debug("上傳資料庫發生錯誤，錯誤為 : " + e.toString());
		}
		
	}
	
}
