package com.ccnpmm.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CommonDAO {
	@Autowired
	protected JdbcTemplate jdbc;
	
}
