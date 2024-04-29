package com.back.admin.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.back.admin.service.AdminMemberService;

@Controller
public class AdminMemberController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired AdminMemberService memberService;
	
	// 회원 관리 게시판 입장
	@RequestMapping(value = "/admin/adminMemberList.go")
	public String teammate() {
		logger.info("회원 관리 게시판 접속");
		return "/admin/member_list";
	}
	
	// 회원 관리 리스트 페이징
	@RequestMapping(value = "/admin/adminMember.ajax", method = RequestMethod.POST)
	@ResponseBody // response 객체로 반환
	public Map<String, Object> callList(String currentPage, String state, String searchWord,
			String searchFlag) {
		
		
		logger.info("listCall / currentPage = {} / state = {} / ", currentPage, state);
		logger.info(searchFlag);
		int page = Integer.parseInt(currentPage);
		if (searchFlag.equals("true")) {
			return memberService.searchList(page, state, searchWord);
		} else {
			return memberService.list(page, state);
		}

	}
}