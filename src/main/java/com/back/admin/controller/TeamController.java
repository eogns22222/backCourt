package com.back.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.back.admin.service.TeamService;

@Controller
public class TeamController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired TeamService teamService;
	
}