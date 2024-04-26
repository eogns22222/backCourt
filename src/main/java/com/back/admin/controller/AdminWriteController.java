package com.back.admin.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	@RequestMapping(value = "/admin/callCourtList.ajax", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> callCourtList(String currentPage, String searchWord) {
		logger.info("::::::::::::callCourtList in:::::::::::::");
		logger.info("param currentPage = " + currentPage);
		logger.info("param searchWord = " + searchWord);

		Map<String, Object> map = adminWriteService.callCourtList(Integer.parseInt(currentPage), searchWord);

		logger.info("::::::::::::callCourtList out:::::::::::::");
		return map;
	}

	@RequestMapping(value = "/admin/callCourtInfo.ajax", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> callCourtInfo(String courtIdx, String selectDate) {
		return adminWriteService.callCourtInfo(courtIdx, selectDate);
	}

	@RequestMapping(value = "/admin/officialWrite.ajax")
	@ResponseBody
	public Map<String, Boolean> officialWrite(@RequestParam Map<String, Object> param) {
		return adminWriteService.officialWrite(param);
	}

}
