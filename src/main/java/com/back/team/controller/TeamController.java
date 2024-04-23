package com.back.team.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.back.team.dto.TeamDTO;
import com.back.team.service.TeamService;

@Controller
public class TeamController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired TeamService teamService;
	
	@RequestMapping(value = "/team")
	public String official() {
		return "redirect:/team/info_list.go";
	}
	
	@RequestMapping(value = "/team/info_list.go")
	public String listGo(HttpSession session, Model model, int team_idx) {
		logger.info("info_list.go /");
		String id = (String) session.getAttribute("loginId");
		
		TeamDTO dto = teamService.teamDetail(team_idx);
		model.addAttribute("info", dto);
		model.addAttribute("id", id);
		
		logger.info(dto.getId());
		logger.info(id);
		
		return "team/info_list";
	}
	
	@RequestMapping(value = "/team/info_list.ajax", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> callList(HttpSession session, String currentPage, int team_idx) {
		logger.info("listCall / currentPage = {} / team_idx = {} / ", currentPage, team_idx);
		int page = Integer.parseInt(currentPage);
		String id = (String) session.getAttribute("loginId");
		Map<String, Object> map = teamService.list(page, team_idx, id);

		return map;
	}
	
	@RequestMapping(value = "/team/user_pop.ajax", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> userPop(String userId) {
		logger.info("listCall / userId = {} / ", userId);
		
		TeamDTO list = teamService.userPop(userId);
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("list", list);
		
		return map;
	}
	
}



























