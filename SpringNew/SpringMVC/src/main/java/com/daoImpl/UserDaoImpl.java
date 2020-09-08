package com.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDao;
import com.entities.Users;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	SessionFactory session;
	
	public boolean saveOrUpdate(Users users){
		session.getCurrentSession().saveOrUpdate(users);
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<Users> list(){
		return session.getCurrentSession().createQuery("from Users").list();
	}
	
	public boolean delete(Users users){
		try{
			session.getCurrentSession().delete(users);
		}catch(Exception ex){
			return false;
		}
		return true;
	}
}
