package com.back.admin.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.back.admin.service.AdminReportService;

@Controller
public class AdminReportController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	AdminReportService adminReportService;

	@RequestMapping(value = "/admin/reportList.go")
	public String reportListGo() {
		return "/admin/report_list";
	}

	@RequestMapping(value = "/admin/reportList.ajax", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> callList(String reportState, String currentPage, String reportSearch,
			String searchFlag) {
		logger.info("listCall / currentPage = {} /  ", currentPage);
		logger.info(searchFlag);
		int page = Integer.parseInt(currentPage);

		return adminReportService.list(reportState, page, reportSearch, searchFlag);

	}
	
	@RequestMapping(value = "/admin/reportDetail.go")
	public String reportDetailGo() {
		return "/admin/feed";
	}

}
