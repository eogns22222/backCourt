package com.back.admin.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.back.admin.service.AdminCourtService;

@Controller
public class AdminCourtController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired AdminCourtService adminCourtService;
	
	@RequestMapping(value = "/admin/courtList.go")
	public String listGo() {
		return "/admin/court_list";
	}
	
	@RequestMapping(value = "/admin/courtList.ajax", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> callList(String currentPage, String address,String searchCategory, String searchWord, String searchFlag) {
		logger.info("listCall / currentPage = {} / address = {} / ", currentPage, address);
		logger.info(searchFlag);
		int page = Integer.parseInt(currentPage);
		if(searchFlag.equals("true")) {
			return adminCourtService.searchList(searchCategory, searchWord, page, address);
		}else {
			return adminCourtService.list(page, address);			
		}

	}
}
