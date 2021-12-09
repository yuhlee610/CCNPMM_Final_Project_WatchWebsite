package com.ccnpmm.controller;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

@Repository
public class Common {
	private static final String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
    private static final String alphaUpperCase = alpha.toUpperCase(); // A-Z
    private static final String digits = "0123456789"; // 0-9
    private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
    private static Random generator = new Random();

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
	
	public String setCodeOrder() {
		int numberOfCharactor = 8;
		String code = randomAlphaNumeric(numberOfCharactor);
		return code;
		
	}
	
	/**
     * Random string with a-zA-Z0-9, not included special characters
     */
    private String randomAlphaNumeric(int numberOfCharactor) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfCharactor; i++) {
            int number = randomNumber(0, ALPHA_NUMERIC.length() - 1);
            char ch = ALPHA_NUMERIC.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }
    
    private static int randomNumber(int min, int max) {
        return generator.nextInt((max - min) + 1) + min;
    }
}
