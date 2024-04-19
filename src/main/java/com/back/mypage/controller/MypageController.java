package com.back.mypage.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.back.mypage.service.MypageService;

@Controller
public class MypageController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired MypageService mypageService;
	
	@RequestMapping(value = "mypage/report")
	public String reportGo() {
		logger.info("제발");
		return "mypage/report";
	}
	

	@RequestMapping(value ="mypage/report.do", method = RequestMethod.POST)
	public String report(Model model, HttpSession session, String id){
		logger.info("들어왓냐");
		String msg = "전송에 실패하였습니다.";
		String page = "mypage/report.do";
		String writeId = mypageService.report(id);
		
		if(writeId != null) {
			page = "report_list";
			session.setAttribute("writeId", writeId);
		}else {
			
		}
			
		return page;
	}
}