package com.back.admin.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.back.admin.service.AdminWriteService;

@Controller
public class AdminWriteController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	AdminWriteService adminWriteService;

	@RequestMapping(value = "/admin/officialWrite.go")
	public String officialWriteGo() {
		return "/admin/official_write";
	}

	@RequestMapping(value = "/admin/callCourtList.ajax")
	@ResponseBody
	public Map<String, Object> callCourtList() {
		logger.info("::::::::::::callCourtList in:::::::::::::");
		Map<String, Object> map = adminWriteService.callCourtList();

		logger.info("::::::::::::callCourtList out:::::::::::::");
		return map;
	}

//	@RequestMapping(value = "/admin/callCourtInfo.ajax")
//	public Map<String, Object> callCourtInfo(String courtIdx) {
//		return adminWriteService.callCourtInfo(courtIdx);
//	}

}
