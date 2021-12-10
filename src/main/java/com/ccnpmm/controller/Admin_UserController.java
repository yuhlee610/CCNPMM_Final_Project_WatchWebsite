package com.ccnpmm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccnpmm.dao.RoleDAO;
import com.ccnpmm.dao.UserDAO;
import com.ccnpmm.entity.Role;
import com.ccnpmm.entity.User;

@Controller
@RequestMapping("/admin/")
public class Admin_UserController {
	@Autowired
	UserDAO userdao;
	@Autowired
	RoleDAO roledao;
	@Autowired
	Common common;
	private Integer userID = 0;

	@RequestMapping("user")
	public String user(ModelMap model, HttpServletRequest request) {
		
		userID = common.AdminLogin(request);
		if(userID == 0) {
			return "redirect:/login";
		}

		List<User> user = userdao.getAll();

//	for(int i=0;i<user.size();i++)
//	{
//		Role role=new Role();
//		role.setRoleId(user.get(i).getRoleId());
//		role.setRoleName(roledao.getById(user.get(i).getRoleId()).getRoleName());
//		user.get(i).setRole(role);
//	}
		model.addAttribute("users", user);
		return "admin/user";
	}

	@RequestMapping(value = "user/changeStatus", method = RequestMethod.POST)
	public @ResponseBody String searchPerson(HttpServletRequest request) {
		String a = request.getParameter("idUser");
		int id = Integer.valueOf(a);
		boolean state = Boolean.valueOf(request.getParameter("state"));
		User user = userdao.getById(id);
		if (state == true) {
			user.setState(false);
			userdao.updateStatus(user);
		} else {
			user.setState(true);
			userdao.updateStatus(user);
		}
		ObjectMapper mapper = new ObjectMapper();
		String ajaxResponse = "";
		try {
			ajaxResponse = mapper.writeValueAsString(user);
		} catch (Exception e) {
			ajaxResponse = "Error";
		}

		return ajaxResponse;
	}
}
