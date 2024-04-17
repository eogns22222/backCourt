package com.back.court.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.court.dao.CourtDAO;

@Service
public class CourtService {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired CourtDAO courtDAO;
	
}
