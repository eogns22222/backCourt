package com.back.teammate.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.teammate.dao.TeammateDAO;

@Service
public class TeammateService {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired TeammateDAO teammateDAO;
	
}
