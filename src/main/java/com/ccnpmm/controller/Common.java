package com.ccnpmm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

@Repository
public class Common {

	public Integer Login(HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			int userId = Integer.valueOf(session.getAttribute("userId").toString());
			return userId;
		}
		catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}
}
