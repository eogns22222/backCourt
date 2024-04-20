package com.back.official.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.back.official.service.OfficialService;

@Controller
public class OfficialController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired OfficialService officialService;
	
	@RequestMapping(value = "/official")
	public String home() {
		return "redirect:/official/match_list.go";
	}
	
	@RequestMapping(value = "/official/match_list.go")
	public String listGo() {
		logger.info("match_list.go /");
		return "official/match_list";
	}
	
	@RequestMapping(value = "/official/match_list.ajax", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> callList(String currentPage, String address, String level) {
		logger.info("listCall / currentPage = {} / address = {} / level = {} / ", currentPage, address);
		logger.info("listCall / level = {} / ", level);
		int page = Integer.parseInt(currentPage);
		Map<String, Object> map = officialService.list(page, address, level);

		return map;
	}
	
}


















