package com.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.entities.Users;
import com.services.UserServices;

@Service
public class UserServicesImpl implements UserServices{

	@Autowired
	UserDao userdao;
	
	public List<Users> list() {
		return userdao.list();
	}

	public boolean delete(Users users) {
		return userdao.delete(users);
	}

	public boolean saveOrUpdate(Users users) {
		return userdao.saveOrUpdate(users);
	}

}
