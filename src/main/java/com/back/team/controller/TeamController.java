package com.back.team.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.back.team.service.TeamService;

@Controller
public class TeamController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired TeamService teamService;
	
	@RequestMapping(value="/list")
	public String list(Model model) {
		logger.info("팀 모집 리스트 요청한다");
		return "teamjoin";
	}
	
	
}
