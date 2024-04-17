package com.back.admin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.admin.dao.WriteDAO;

@Service
public class WriteService {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired WriteDAO writeDAO;
	
}
