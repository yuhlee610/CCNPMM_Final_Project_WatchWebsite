package com.ccnpmm.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import com.ccnpmm.dao.CommentDAO;
import com.ccnpmm.entity.Comment;

@Controller
public class CommentController {
	@Autowired
	private CommentDAO commentDao;
	@Autowired Common common;
	private Integer userId;
	
	@RequestMapping(value = "addComment", method = RequestMethod.POST)
	public RedirectView index(
			ModelMap model, 
			@ModelAttribute("comment") Comment comment,
			HttpServletRequest request) {
		RedirectView rv = new RedirectView();
		rv.setContextRelative(true);
		userId = common.Login(request);
		if(userId == 0) {
			rv.setUrl("/login");
			return rv;
		}
		rv.setUrl("/detail?productId=" + comment.getProductId());
		
//		User user = (User) request.getSession().getAttribute("user");
		
		
		comment.setDate(new Date());
		comment.setUserId(userId);
		
		if(comment.getReplyFrom() == 0) {
			comment.setReplyFrom(null);
		}
		
		commentDao.insert(comment);
		return rv;
	}
}
