package com.ccnpmm.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ccnpmm.dao.RoleDAO;
import com.ccnpmm.dao.UserDAO;
import com.ccnpmm.entity.Role;
import com.ccnpmm.entity.User;

@Controller
public class AccountController {

	/**
	 * Inject từ @Repository CustomerDAO
	 */
	@Autowired
	private UserDAO userDao;
	@Autowired
	private RoleDAO roleDAO;
	
	
	/**
	 * GET: login.htm
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		model.addAttribute("user", new User());
		model.addAttribute("errors","");
		return "account/login";
	}

	/**
	 * GET: login.htm
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(HttpServletRequest request ,ModelMap model, @ModelAttribute("user") User user) {
		try {
			
			String username = user.getUsername();
			if(username.equals("") ) model.addAttribute("errors","");
			User cust = userDao.getByUsername(username);
			if (cust.getPassword().equals(user.getPassword()) && cust.isState()) {
				
				HttpSession session = request.getSession();
				session.setAttribute("username", cust.getUsername());
				session.setAttribute("avatar", cust.getAvatar());
				session.setAttribute("userId", cust.getId());
				
				//get roleName
				String roleName = getRoleName(cust.getRoleId());
				if(roleName != null) {
					session.setAttribute("role", roleName);
				}
				
				
				if(roleName.equals("User")) {
					return "redirect:/";
				}
				else if(roleName.equals("Admin")) {
					return "redirect:/admin/";
				}
				
				
			} else {
				model.addAttribute("errors", "Tên đăng nhập hoặc mật khẩu không hợp lệ!");
			}
		}
		catch( Exception e) {
			model.addAttribute("errors", "Tên đăng nhập hoặc mật khẩu không hợp lệ!");
		}
		
		return "account/login";
	}
	
	@RequestMapping("getRoleName")
	public String getRoleName(Serializable idRole) {
		String roleName = "";
		Role role = roleDAO.getById(idRole);
		roleName = role.getRoleName();
		return roleName;
	}
}
