package com.back.member.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.member.dao.MemberDAO;

@Service
public class MemberService {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired MemberDAO memberDAO;
	public String login(String id, String pw) {
		String loginId = memberDAO.loginId(id,pw);
		logger.info("loginId : "+loginId);
		return loginId;
	}
	public int Join(Map<String, String> param) {
		int row = memberDAO.Join(param);
		return row;
	}
	
	public Object overlay(String id) {	
		return memberDAO.overlay(id);
	}
	
}
