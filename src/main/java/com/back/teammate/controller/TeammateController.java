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
         logger.info(address);
         int page = Integer.parseInt(currentPage);      
         Map<String, Object> map = teammateService.pageList(page, address, level, position);
         
         return map;
     
      }
      
  	@RequestMapping(value = "/official/teammateSearchList.ajax", method = RequestMethod.POST)
  	@ResponseBody
  	public Map<String, Object> searchList(String searchCategory, String searchWord,String searchFlag, String currentPage, String address,String id, String teamName) {
		logger.info("listCall / currentPage = {} / address = {} / ", currentPage, address);
		logger.info("서치리스트");
  		int page = Integer.parseInt(currentPage);
  		Map<String, Object> map = teammateService.searchList(searchCategory, searchWord, page, address, id, teamName);

  		return map;
  	}
  	@RequestMapping(value = "/teammate/teammate_detail.go", method = RequestMethod.POST)
  	public String detailGo(Model model, String join_team_idx) {
  		logger.info("디테일");
  		model.addAttribute("teammateIdx",join_team_idx);
  		return "teammate/teammate_detail";
  	}
      
   
}