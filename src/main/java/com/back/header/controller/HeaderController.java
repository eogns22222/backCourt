package com.back.header.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.back.header.dto.HeaderDTO;
import com.back.header.service.HeaderService;

@Controller
public class HeaderController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired HeaderService headerserivce;
	
	@RequestMapping(value = "/header")
	public String header() {
		return "header/header";
	}
	
	@RequestMapping(value = "/header/team/list.ajax", method = RequestMethod.GET)
	@ResponseBody 
	public Map<String, Object> teamListCall(HttpSession session) {
		logger.info("team팝업 list 출력");
		// 임의의 아이디 값
		session.setAttribute("loginInfo", "testID2");
		
		String id = (String) session.getAttribute("loginInfo");
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<HeaderDTO> list = headerserivce.teamList(id);
		map.put("list", list);
		
		return map; 
	}
	
	@RequestMapping(value = "/header/notice/list.ajax", method = RequestMethod.GET)
	@ResponseBody 
	public Map<String, Object> noticeListCall(HttpSession session) {
		logger.info("notice list 출력");
		// 임의의 아이디 값
		session.setAttribute("loginInfo", "testID2");
		
		String id = (String) session.getAttribute("loginInfo");
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<HeaderDTO> list = headerserivce.noticeList(id);
		map.put("list", list);
		
		return map; 
	}
	
	@RequestMapping(value = "/header/del.do")
	public String delete(String idx, HttpSession session) {
		logger.info(idx + " 삭제");
		String page = "redirect:/header/notice/list.ajax";
		
		if(session.getAttribute("loginInfo") != null) {
//			page = "redirect:/list";
			headerserivce.delete(idx);
		}
		
		return page;
	}
	
}

















