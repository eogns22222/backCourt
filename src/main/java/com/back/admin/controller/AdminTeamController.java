package com.back.admin.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.back.admin.service.AdminCourtService;
import com.back.admin.service.AdminTeamService;

@Controller
public class AdminTeamController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired AdminTeamService adminTeamService;
	
	@RequestMapping(value ="/admin/team_list.go")
	public String listGo() {
		logger.info("팀관리 리스트 페이지 접속");
		return "/admin/team_list";
	}
	
	@RequestMapping(value = "/admin/teamList.ajax")
	@ResponseBody
	public Map<String, Object> teamList(@RequestParam Map<String, Object> param){
		logger.info("팀리스트 in param : {}",param);
		return adminTeamService.teamList(param);
	}
	
	
	@RequestMapping(value="/admin/team_detail.go")
	public String detailGo(HttpSession session, String teamIdx, Model model) {
		logger.info("teamDetailPage Controller 접속");
		adminTeamService.teamInfo(teamIdx, model);
		return "/admin/team_detail";
	}
	
}















