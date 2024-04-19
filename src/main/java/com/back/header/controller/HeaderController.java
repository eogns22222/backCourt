package com.back.header.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.back.header.dto.HeaderDTO;
import com.back.header.service.HeaderService;
import com.back.member.dto.MemberDTO;

@Controller
public class HeaderController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired HeaderService headerserivce;
	
	@RequestMapping(value = "/header")
	public String header(HttpSession session, Model model) {
		logger.info("접속");
		session.setAttribute("loginInfo", "testID2");
//		session.removeAttribute("loginInfo");
		String id = (String) session.getAttribute("loginInfo");
		
		if(session.getAttribute("loginInfo") != null) {
			MemberDTO dto = headerserivce.nav(id);
			model.addAttribute("member", dto);
			model.addAttribute("login", "javascript:;");
		}else {
			model.addAttribute("login", "../login");
			model.addAttribute("msg", "loginMsg()");
		}
		
		return "header/header";
	}
	
	@RequestMapping(value = "/header/team/list.ajax", method = RequestMethod.GET)
	@ResponseBody 
	public Map<String, Object> teamListCall(HttpSession session, Model model) {
		logger.info("team팝업 list 출력");
		// 임의의 아이디 값
		session.setAttribute("loginInfo", "testID2");
//		session.removeAttribute("loginInfo");
		
		String id = "";
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(session.getAttribute("loginInfo") != null) {
			id = (String) session.getAttribute("loginInfo");
			
			List<HeaderDTO> list = headerserivce.teamList(id);
			map.put("list", list);
		}else {
			map.put("check", 1);
			map.put("msg", "로그인이 필요한 서비스입니다.");
			
		}
		
		return map; 
	}
	
	@RequestMapping(value = "/header/notice/list.ajax", method = RequestMethod.GET)
	@ResponseBody 
	public Map<String, Object> noticeListCall(HttpSession session) {
		logger.info("notice list 출력");
		// 임의의 아이디 값
//		session.setAttribute("loginInfo", "testID2");
//		session.removeAttribute("loginInfo");
		
		String id = (String) session.getAttribute("loginInfo");
		Map<String, Object> map = new HashMap<String, Object>();
		List<HeaderDTO> list = new ArrayList<HeaderDTO>();
		
		if(session.getAttribute("loginInfo") != null) {
			list = headerserivce.noticeList(id);
			HeaderDTO dto = headerserivce.count(id);
			map.put("list", list);
			map.put("count", dto);
		}else {
			map.put("check", 1);
			map.put("msg", "로그인이 필요한 서비스입니다.");
			
		}
		
		return map; 
	}
	
	@RequestMapping(value = "/header/notice/delete.ajax", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> del(String idx){
		logger.info("idx : " + idx);
		
		int cnt = headerserivce.del(idx);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cnt", cnt);
		
		return map;
	}
	
	@RequestMapping(value = "/logout.do")
	public String logout(HttpSession session) {
		logger.info("로그아웃 시도");
		String page = "redirect:/login.do";
		session.removeAttribute("loginInfo");
		
		return page;
	}
	
}

















