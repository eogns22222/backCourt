package com.back.admin.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String officialWriteGo(HttpSession session) {
//		String isAdmin = (String) session.getAttribute("isAdmin");
//		if (isAdmin == null ||isAdmin.isEmpty()) {
//			return "redirect:/login.go";
//		}
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

	@RequestMapping(value = "/admin/officialUpdate.go")
	public String officialUpdateGo(HttpSession session, Model model, String officialIdx) {
//		String isAdmin = (String) session.getAttribute("isAdmin");
//		if (isAdmin == null ||isAdmin.isEmpty()) {
//			return "redirect:/login.go";
//		}
		adminWriteService.officialUpdateGo(model, officialIdx);
		model.addAttribute("officialIdx", officialIdx);
		return "/admin/official_update";
	}

	@RequestMapping(value = "/admin/officialUpdate.ajax", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Boolean> officialUpdate(@RequestParam Map<String, Object> param) {
		logger.info("officialUpdate Contoroller param = {}", param);
		return adminWriteService.officialUpdate(param);
	}

}
