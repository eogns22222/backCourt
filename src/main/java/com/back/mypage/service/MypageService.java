package com.back.mypage.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.mypage.dao.MypageDAO;

@Service
public class MypageService {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired MypageDAO mypageDAO;
	
	public int report(Map<String, Object> map) {
		logger.info("report-service  map={}",map);
		return mypageDAO.report(map);
		
	}
	
}
