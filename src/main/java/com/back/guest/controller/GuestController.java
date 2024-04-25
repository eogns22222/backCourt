package com.back.guest.controller;

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

import com.back.guest.dto.GuestDTO;
import com.back.guest.service.GuestService;

@Controller
public class GuestController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	GuestService guestService;

	// 작성페이지 접속
	@RequestMapping(value = "/guest_join/write")
	public String guestWriteGo() {
		logger.info("게스트 모집글 작성페이지 접속");
		return "/guest_join/write";
	}

	// 글쓰기 작성
	@RequestMapping(value = "/guest_join/write.do", method = RequestMethod.POST)
	public String guestWrite(String booking_idx, String guest_info, String guest_level, String guest_position,
			String guest_gender, String guest_to, String guest_fee) {
		logger.info("guest_info:" + guest_info + " guest_level: " + guest_level + " guest_position : " + guest_position
				+ " guest_gender : " + guest_gender + " guest_to : " + guest_to + " guest_fee : " + guest_fee
				+ " court_booking : " + booking_idx);
		// 리스트페이지로 바꿔야함
		String page = "/guest_join/write";
		// 아이디 수정필요
		String id = "admin";
		int court_booking_idx = Integer.parseInt(booking_idx);
		// 팀idx 수정 필요
		int team_idx = guestService.callmyteam(id);
		logger.info("team_idx : " + team_idx);
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

		if (row >= 1) {
			// 나중에 글 상세보기 페이지로 이동
			page = "index";
		}

		return page;
	}

	// 구장 리스트 불러오기
	@RequestMapping(value = "/guest_join/courtlist.ajax", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> courtList(HttpSession session, Model model) {
		logger.info("courtlist 출력");

		String id = "admin";

		Map<String, Object> map = new HashMap<String, Object>();
		List<GuestDTO> list = guestService.courtList(id);
		map.put("list", list);

//		if(session.getAttribute("loginId") != null) {
//			id = (String) session.getAttribute("loginId");
//			logger.info("loginId : ", id);
//			
//		}

		return map;

	}

	// 수정페이지 접속
	@RequestMapping(value = "/guest_join/modify")
	public String update() {
		logger.info("게스트 모집글 수정페이지 접속");

		return "/guest_join/modify";
	}

	// 수정페이지 불러오기
	@RequestMapping(value = "/guest_join/modify.ajax", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, GuestDTO> guestModify(String guestIdx) {

		guestIdx = "4";

		logger.info("guestIdx" + guestIdx);

		return guestService.guestModify(Integer.parseInt(guestIdx));
	}

	// 수정페이지 작성완료
	@RequestMapping(value = "/guest_join/guestUpdate.ajax", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> writeUpdate(String guest_info, String guest_level, String guest_position,
			String guest_gender, String guest_to, int guest_fee, String guestIdx) {
		logger.info("guestUpate " + guest_level + " " + guest_gender);
		guestIdx = "4";
		return guestService.writeUpdate(guest_info, guest_level, guest_position, guest_gender, guest_to, guest_fee,
				guestIdx);
	}

	// 게스트 리스트 접속
	@RequestMapping(value = "/guest_join/list.go")
	public String listGo() {
		logger.info("게스트모집 리스트 접속");
		return "/guest_join/list";
	}

	// 게스트 리스트 가져오기
	@RequestMapping(value = "/guest_join/list.ajax")
	@ResponseBody
	public Map<String, Object> guestList(String address, String position, String currentPage, String gender,
			String level, String searchCategory, String searchWord, String searchFlag) {
		logger.info("게스트리스트 아작스 요청");
		
		return guestService.guestList(Integer.parseInt(currentPage), searchFlag, searchCategory, searchWord, address, gender, position,
				level);
	}
	
	@RequestMapping(value ="/guest_join/info.go")
	public String infoGo() {
		logger.info("상세페이지 진입");
		return "guest_join/info";
	}
}
