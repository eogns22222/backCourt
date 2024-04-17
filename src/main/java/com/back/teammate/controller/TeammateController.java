package com.back.teammate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.back.teammate.service.TeammateService;

@Controller
public class TeammateController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired TeammateService teammateService;
	
}
