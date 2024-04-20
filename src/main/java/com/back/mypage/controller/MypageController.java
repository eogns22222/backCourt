package com.back.mypage.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.back.mypage.service.MypageService;

@Controller
public class MypageController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired MypageService mypageService;
	
	@RequestMapping(value = "/mypage/report")
	public String reportGo() {
		logger.info("제발");
		return "/mypage/report";
	}
	

	@RequestMapping(value ="/mypage/report.do", method = RequestMethod.POST)
	public String report(Model model, HttpSession session, String title, String contents){
		logger.info("report title :{}, contents : {}",title, contents);
		String page = "/mypage/report";
		// 받아온 아이디 getsession하기
		String id = "admin";
		// 전에 페이지에서 받아온 글구분이랑, 글번호 변경
		String report_write_type = "신고";
		int report_write_idx = 1;
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("report_tit", title);
		map.put("report_content", contents);
		map.put("report_write_type", report_write_type);
		map.put("report_write_idx", report_write_idx);
		int row = mypageService.report(map);
		
		if(row >= 1) {
			page = "index";
		}
		
		return page;
	}
	
	@RequestMapping(value = "/mypage/jjim.go")
	public String jjimGo() {
		return "/mypage/jjim";
	}
	
	@RequestMapping(value = "/mypage/jjimList.ajax")
	@ResponseBody
	public Map<String, Object> jjimList(){
		logger.info("mypage/jjimList ");
		// session check 필요
		String id = "admin"; //id 받아오는 로직으로 수정 필요
		return mypageService.jjimList(id);
	}
	
	@RequestMapping(value = "/mypage/jjimDel.ajax")
	@ResponseBody
	public Map<String, Object> jjimDel(@RequestParam(value = "selectedIdxList[]") List<String> selectedIdxList){
		logger.info("{}",selectedIdxList);
		return mypageService.jjimDel(selectedIdxList);
	}
}