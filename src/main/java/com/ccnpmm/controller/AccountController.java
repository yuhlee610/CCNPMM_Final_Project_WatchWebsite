package com.ccnpmm.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	
	
	@Autowired
	private RoleDAO roleDao;

	@Autowired
	ServletContext context;

	@Autowired
	JavaMailSender mailSender;

	private String emailFrom = "nhomltweb@gmail.com";

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String register(ModelMap model) {
		model.addAttribute("user", new User());
		return "user/register";
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(ModelMap model, @ModelAttribute("user") User user, HttpServletRequest request) {
		List<User> userList2 = userDao.getBySql("SELECT * FROM [User] WHERE Username='" + user.getUsername() + "' OR " + "Email='" + user.getEmail() + "'");
		if(!userList2.isEmpty()) {
			model.addAttribute("message", "Username or email existed");
			return "user/register";
		}
		// tao session gom (email duoc encode, user)
		ArrayList<Object> listOfObjects = new ArrayList<Object>();
		String emailEncode = Base64.getEncoder().encodeToString(user.getEmail().getBytes());
		listOfObjects.add(emailEncode);
		listOfObjects.add(user);

		if (!sendMailHandler(user.getEmail(), user.getName(), emailEncode)) {
			model.addAttribute("message", "Register fail");
			return "user/register";
		}

		request.getSession().setAttribute("registerUser",listOfObjects);


		model.addAttribute("message", "We have sent an email to your email address.");
		return "user/register";
	}
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

	@RequestMapping(value = "/verifyRegister")
	public String verifyRegister(@RequestParam(value = "emailEncode", required = true) String emailEncode, HttpServletRequest request, ModelMap model) {
		ArrayList<Object> listOfObjects = (ArrayList<Object>) request.getSession().getAttribute("registerUser");
		User user = (User) listOfObjects.get(1);
		
		byte[] decodedBytes = Base64.getDecoder().decode(emailEncode);
		String decodedEmail = new String(decodedBytes);
		
		try {
			if(decodedEmail.equals(user.getEmail())) {
				List<Role> roleList = roleDao.getBySql("SELECT * FROM [Role] WHERE Rolename='Customer'");
				user.setRoleId(roleList.get(0).getRoleId());
				userDao.register(user);
				return "user/index";
			}
			throw new Exception("Link not valid!");
		}
		catch(Exception ex) {
			model.addAttribute("message", ex.toString());
			return "user/register";
		}
	}

	public boolean sendMailHandler(String to, String name, String emailEncode) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(emailFrom);
			helper.setTo(to);
			helper.setReplyTo(emailFrom);
			helper.setSubject("Thank you for your interest in WatchShop");
			String body = "Click link below to activate your account:<br>"
					+ "<a href=\"http://localhost:81/CCNPMM_Final_Project_WatchWebsite/verifyRegister?emailEncode=" + emailEncode + "\">Click me</a>";
			helper.setText(body, true);
			mailSender.send(message);
			return true;
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return false;
		}
	}
}
