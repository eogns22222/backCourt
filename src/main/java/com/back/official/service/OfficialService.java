package com.back.official.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.official.dao.OfficialDAO;

@Service
public class OfficialService {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired OfficialDAO officialDAO;
	
}
