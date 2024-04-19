package com.back.mypage.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.mypage.dao.MypageDAO;

@Service
public class MypageService {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired MypageDAO mypageDAO;
	public String report(String id) {
		return mypageDAO.report(id);
	}
	
}
