package com.back.teammate.controller;

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

import com.back.teammate.service.TeammateService;

@Controller
public class TeammateController {

   Logger logger = LoggerFactory.getLogger(getClass());
   @Autowired TeammateService teammateService;
   
   // 팀원 모집 게시판 입장
	@RequestMapping(value = "/teammate")
	public String teammate() {
		logger.info("팀메이트 접속");
		return "redirect:/teammate/teammate_join_list.go";
	}
   
   @RequestMapping(value = "/teammate/teammate_join_list.go")
   public String teammateGo(HttpSession session, Model model) {
      logger.info("teammate_join_list.go /");
      
      if(session.getAttribute("loginId") != null) {
			model.addAttribute("chk", "on");
		}else {
			model.addAttribute("chk", "notOn");
		}
      return "teammate/teammate_join_list";
   }
   
   // 팀원 리스트 페이징 처리
      @RequestMapping(value="/teammate/teammatePage.ajax", method = RequestMethod.POST)
      @ResponseBody // response 객체로 반환
      public Map<String, Object> callList(String currentPage, String address, String position, String level) {
         logger.info(position);
         int page = Integer.parseInt(currentPage);      
         Map<String, Object> map = teammateService.pageList(page, address, position, level);
         
         return map;
     
      }
      
  	@RequestMapping(value = "/teammate/teammateSearchList.ajax", method = RequestMethod.POST)
  	@ResponseBody
  	public Map<String, Object> searchList(String currentPage, String teammateSearchCategory, String teammateSearchWord,String searchFlag, String address,String id, String teamName) {
		logger.info("listCall / currentPage = {} / address = {} / ", currentPage, address);
		logger.info("서치리스트");
  		int page = Integer.parseInt(currentPage);
  		Map<String, Object> map = teammateService.searchList(teammateSearchCategory, teammateSearchWord, page, address, id, teamName);

  		return map;
  	}
  	// 팀원모집 상세보기
  	@RequestMapping(value = "/teammate/teammate_detail.go", method = RequestMethod.GET)
  	public String detail(HttpSession session, Model model, String join_team_idx) {
  		logger.info("팀원모집상세보기입장이요");
  		logger.info("join_team_idx : {}", join_team_idx);
  		String page = "../login";
  		
  		if(session.getAttribute("loginId") != null) {
			page = "/teammate/teammate_detail";
			teammateService.detail(join_team_idx, model);
		}else {
			model.addAttribute("msg","로그인이 필요한 서비스입니다.");
		}
  		
  		return page;
  	}
      
   
}