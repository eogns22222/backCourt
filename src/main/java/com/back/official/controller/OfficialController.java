package com.back.official.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.back.official.service.OfficialService;

@Controller
public class OfficialController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired OfficialService officialService;
	
	@RequestMapping(value = "/official")
	public String home() {
		return "official/match_list";
	}
	
}
