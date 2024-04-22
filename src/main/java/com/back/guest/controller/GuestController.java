package com.back.guest.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.back.guest.service.GuestService;

@Controller
public class GuestController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired GuestService guestService;
	
	@RequestMapping(value = "/guest_join/write")
	public String guestWriteGo() {
		logger.info("게스트 모집글 작성페이지 접속");
		return "/guest_join/write";
	}
	
	@RequestMapping(value = "/guest_join/write.do", method = RequestMethod.POST)
	public String guestWrite(String guest_info, String guest_level,
			String guest_position, String guest_gender, String guest_to, String guest_fee){
		logger.info("write content:"+guest_info
				+" guest_level: "+guest_level
				+" guest_position : "+guest_position
				+" guest_gender : "+guest_gender
				+" guest_to : "+guest_to
				+" guest_fee : "+guest_fee);
		String page = "/guest_join/write";
		 // 나중에 팀모집글에서 들어올 팀장아이디 get session 하기 
		String id = "admin";
		 // 구장찾기에서 불러온 코트번호, 지역명, 날짜, 구장명으로 변경해주기
		int court_booking_idx = 3;
		int team_idx = 14;
		Map<String, Object> map = new HashMap<>();
		String guestFee = guest_fee.replace(",", "");
		logger.info(guestFee);
		map.put("id", id);
		map.put("court_booking_idx", court_booking_idx);
		map.put("team_idx", team_idx);
		map.put("guest_position", guest_position);
		map.put("guest_level", guest_level);
		map.put("guest_gender", guest_gender);
		map.put("guest_to", Integer.parseInt(guest_to));
		map.put("guest_info", guest_info);
		map.put("guest_fee", Integer.parseInt(guestFee));
		map.put("guest_state", "true");
		int row = guestService.guestWrite(map);
		
		if(row >= 1) {
			// 나중에 글 상세보기 페이지로 이동
			page = "index";
		}
		
		
		return page;
	}
}
