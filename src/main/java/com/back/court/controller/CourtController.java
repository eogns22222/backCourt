package com.back.court.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.back.court.service.CourtService;

@Controller
public class CourtController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	CourtService courtService;

	@RequestMapping(value = "/")
	public String home() {
		return "redirect:/court/list.go";
	}

	@RequestMapping(value = "/court/list.go")
	public String listGo() {
		logger.info("list.go /");
		return "court/list";
	}

	@RequestMapping(value = "/court/list.ajax", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> callList(String currentPage, String address,String searchCategory, String searchWord, String searchFlag) {
		logger.info("listCall / currentPage = {} / address = {} / ", currentPage, address);
		logger.info(searchFlag);
		int page = Integer.parseInt(currentPage);
		if(searchFlag.equals("true")) {
			return courtService.searchList(searchCategory, searchWord, page, address);
		}else {
			return courtService.list(page, address);			
		}

	}



	@RequestMapping(value = "/court/jjim.ajax", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Boolean> jjimAdd(String courtIdx) {
		logger.info("jjimAdd / courtIdx = {}", courtIdx);

		Map<String, Boolean> map = new HashMap<String, Boolean>();
//		세션 아이디 받아오
		String id = "admin";
		map.put("result", courtService.jjim(id, Integer.parseInt(courtIdx)));

		return map;
	}

	@RequestMapping(value = "/court/noJjim.ajax", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Boolean> jjimRemove(String courtIdx) {
		logger.info("jjimAdd / courtIdx = {}", courtIdx);

		Map<String, Boolean> map = new HashMap<String, Boolean>();

//		세션 아이디 받아오
		String id = "admin";
		map.put("result", courtService.jjimRemove(id, Integer.parseInt(courtIdx)));

		return map;
	}

	@RequestMapping(value = "/court/detail.go")
	public String detailGo(Model model, String court_idx) {
		logger.info(court_idx);
		model.addAttribute("courtIdx",court_idx);
		return "court/detail";
	}
	
}
