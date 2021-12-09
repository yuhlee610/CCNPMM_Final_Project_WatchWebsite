package com.ccnpmm.controller;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ccnpmm.dao.User1DAO;
import com.ccnpmm.dao.UserDAO;
import com.ccnpmm.entity.User;
import com.ccnpmm.entity.User1;

@Controller
@RequestMapping("/user/")
public class UserController {
	
	@Autowired ServletContext context;
	@Autowired User1DAO user1Dao;
	@Autowired Common common;
	private Integer userId = 0;
	private Integer flagAlert = 0;

	@RequestMapping("/profile")
	public String index(HttpServletRequest request, ModelMap model) {
		userId = common.Login(request);
		if(userId == 0) {
			return "redirect:/login";
		}
		
		User1 user = user1Dao.getById(userId);
		String birthdayStr = user.getBirthday().toString();
		SimpleDateFormat StringToDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date birthdayDate = null;
		try {
			birthdayDate = StringToDate.parse(birthdayStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		DateFormat DateToString = new SimpleDateFormat("yyyy-MM-dd");
        String birthday = DateToString.format(birthdayDate);
        user.setBirthday(birthday);
        if(flagAlert == 1) {
        	model.addAttribute("message", "Cập nhật thông tin thành công");
        	flagAlert = 0;
        }
        else if( flagAlert == 0) {
        	model.addAttribute("message", "");
        }
        //user.setBirthday(birthday);
		model.addAttribute("user", user);
		return "user/profile";
	}
	
	@RequestMapping(value = "/editProfile", method = RequestMethod.POST )
	public String editProfile(HttpServletRequest request, ModelMap model, @ModelAttribute("user") User1 user) {
		try {
			userId = common.Login(request);
			if(userId == 0) {
				return "redirect:/login";
			}
			
			user.setId(userId);
			
			// Kiem tra exception null của avatar
			try {
				String avatar = user.getAvatar();
				if(avatar.equals(null)) {
					user.setAvatar("resources/Users/images/profile/pic1.jpg");
				}
			}
			catch (Exception e) {
				user.setAvatar("resources/Users/images/profile/pic1.jpg");
			}
			
			
			user1Dao.update(user);
			model.addAttribute("message", "Cập nhật thông tin thành công");
			flagAlert = 1;
			return "redirect:/user/profile";
		}
		catch (Exception e) {
			// TODO: handle exception
			return "redirect:/login";
		}
		
	}
	
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST )
	@ResponseBody 
	public String changePassword(HttpServletRequest request, String newPassword, String currentPassword) {
		HttpSession session = request.getSession();
		try {
			if(session.getAttribute("username").equals(""))
				return "login";
			Integer userid = Integer.valueOf(session.getAttribute("userId").toString());
			User1 user = user1Dao.getById(userid);
			if(user.getPassword().equals(currentPassword)) {
				user.setPassword(newPassword);
				user1Dao.changePassword(user);
				return "true";
			}
			else {
				return "NotValid";
			}
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
			return "login";
		}
		
	}
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST )
	@ResponseBody 
	public String uploadFile(MultipartFile file) {
		if (!file.isEmpty()) {
			String imageUrl = "resources/assets/img/avatar/" + file.getOriginalFilename();
			String absolutePath = context.getRealPath(imageUrl);
			File uploadFile = new File(absolutePath);
			try {
				file.transferTo(uploadFile);
			} catch (Exception e) {
				return "false";
			}
			return absolutePath;
		}
		return "false";
	}
	
}


