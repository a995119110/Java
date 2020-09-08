package com.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.DownloadDao;
import com.entities.Score;

@Repository
@Transactional
public class DownloadDaoImp implements DownloadDao{
	@Autowired
	SessionFactory Session;
	
	@SuppressWarnings("unchecked")
	public List<Score> GetScore(){
		try {
			return Session.getCurrentSession().createQuery("From Score").list();
		}catch (Exception e) {
			return null;
		}
		
	}
}
