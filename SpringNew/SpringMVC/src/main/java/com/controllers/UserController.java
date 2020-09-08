package com.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.entities.Users;
import com.services.UserServices;

@Controller
@RequestMapping(value="users")
public class UserController {

	private static final Logger log = Logger.getLogger(UserController.class) ;
	
	@Autowired
	UserServices userServices;
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ModelAndView getPage() {
		ModelAndView view = new ModelAndView("hello");
		log.debug("開啟hello頁面");
		return view;
	}
	
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> getSaved(Users users){
		
		log.debug("傳入getSaved方法的資料為:");
		log.debug("user_id : " + users.getUser_id());
		log.debug("user_name : " + users.getUser_name());
		log.debug("email : " + users.getEmail());
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		try {
			if(userServices.saveOrUpdate(users)){
				map.put("status", "200");
				map.put("message", "Your record has been saved successfully");
			}
			log.debug("儲存/更新成功");
		} catch (Exception e) {
			
			log.debug("儲存/更新發生錯誤，錯誤為 : " + e.toString());
		}
		
		return map;
	}
	
	
	@RequestMapping(value="/list", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> getAll(Users users){
		
		log.debug("傳入getAll方法的資料為:");
		log.debug("user_id : " + users.getUser_id());
		log.debug("user_name : " + users.getUser_name());
		log.debug("email : " + users.getEmail());
		
		Map<String,Object> map = new HashMap<String,Object>();
		List<Users> list = userServices.list();
		
		try {
			if(list != null){
				map.put("status", "200");
				map.put("message", "Data is found");
				map.put("data", list);
				log.debug("載入資料成功");
			}else{
				map.put("status", "404");
				map.put("message", "Data is not found");
				log.debug("查無資料");
			}
		} catch (Exception e) {
			log.debug("載入資料發生錯誤，錯誤為 : " + e.toString());
		}
		return map;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> delete(Users users){
		
		log.debug("傳入delete方法的資料為:");
		log.debug("user_id : " + users.getUser_id());
		log.debug("user_name : " + users.getUser_name());
		log.debug("email : " + users.getEmail());
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		try {
			if(userServices.delete(users)){
				map.put("status", "200");
				map.put("message", "Your record has been deleted successfully");
				log.debug("刪除資料成功");
			}
		} catch (Exception e) {
			log.debug("刪除資料發生錯誤，錯誤為 : " + e.toString());
		}
		
		return map;
	}
}
