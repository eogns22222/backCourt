package com.back.guest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.back.guest.service.GuestService;

@Controller
public class GuestController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired GuestService guestService;
	
}
