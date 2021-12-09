package com.ccnpmm.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ccnpmm.dao.CommentDAO;
import com.ccnpmm.dao.UserDAO;
import com.ccnpmm.entity.Brand;
import com.ccnpmm.entity.Comment;
import com.ccnpmm.entity.User;

@Controller
@RequestMapping("/admin/comment")
public class Admin_CommentController {
	@Autowired CommentDAO cdao;
	@Autowired UserDAO udao;
@RequestMapping()
public String index(ModelMap model)
{
	List<Comment> noreplies =cdao.getAllNoReply();
	List<Comment> replieds =cdao.getAllReplied();
	for(int i=0;i<noreplies.size();i++)
	{
		User user=new User();
		user.setUsername(udao.getById(noreplies.get(i).getUserId()).getUsername());
		
		noreplies.get(i).setUser(user);
	}
	for(int j=0;j<replieds.size();j++)
	{
		User user=new User();
		User user1=new User();
		Comment comment=new Comment();
		user.setUsername(udao.getById(replieds.get(j).getUserId()).getUsername());
		
		comment.setContent(cdao.getByReplyId(replieds.get(j).getId()).getContent());
		comment.setUserId(cdao.getByReplyId(replieds.get(j).getId()).getUserId());
		user1.setUsername(udao.getById(comment.getUserId()).getUsername());
		replieds.get(j).setUser(user);
		replieds.get(j).setUser1(user1);
		replieds.get(j).setComment(comment);
	}
	model.addAttribute("comment", new Comment());
	model.addAttribute("noreplies", noreplies);
	model.addAttribute("replieds", replieds);
	return "admin/comment";
}
@RequestMapping(value = "/create", method = RequestMethod.POST)
public String create(ModelMap model,@ModelAttribute("comment")Comment comment)
{
	long millis=System.currentTimeMillis();  
  Date date = new Date(millis);
	//Comment cm=cdao.getById(comment.getIdComment());
	comment.setReplyFrom(comment.getIdComment());
	comment.setUserId(4);
	comment.setDate(date);
	try {
		cdao.insert(comment);
	}
	catch (Exception e) {
		model.addAttribute("messages", "");
	}
	List<Comment> noreplies =cdao.getAllNoReply();
	List<Comment> replieds =cdao.getAllReplied();
	for(int i=0;i<noreplies.size();i++)
	{
		User user=new User();
		user.setUsername(udao.getById(noreplies.get(i).getUserId()).getUsername());
		
		noreplies.get(i).setUser(user);
	}
	for(int j=0;j<replieds.size();j++)
	{
		User user=new User();
		User user1=new User();
		Comment comment1=new Comment();
		user.setUsername(udao.getById(replieds.get(j).getUserId()).getUsername());
		
		comment1.setContent(cdao.getByReplyId(replieds.get(j).getId()).getContent());
		comment1.setUserId(cdao.getByReplyId(replieds.get(j).getId()).getUserId());
		user1.setUsername(udao.getById(comment1.getUserId()).getUsername());
		replieds.get(j).setUser(user);
		replieds.get(j).setUser1(user1);
		replieds.get(j).setComment(comment1);
	}
	model.addAttribute("comment", new Comment());
	model.addAttribute("noreplies", noreplies);
	model.addAttribute("replieds", replieds);
	return "admin/comment";
}
}
