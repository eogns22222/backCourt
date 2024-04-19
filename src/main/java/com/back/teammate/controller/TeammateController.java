package com.back.teammate.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.back.teammate.service.TeammateService;

@Controller
public class TeammateController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired TeammateService teammateService;
	
	// 팀원 모집 게시판 입장	
	@RequestMapping(value = "/teamjoin")
	public String home() {
		return "redirect:/teamjoin/teamjoin.go";
	}
	
	@RequestMapping(value = "/teamjoin/teamjoin.go")
	public String listGo() {
		logger.info("list.go /");
		return "teamjoin/teamjoin";
	}
	
	// 팀원 리스트 페이징 처리
		@RequestMapping(value="/teamjoin/teamjoinpage.ajax")
		@ResponseBody // response 객체로 반환
		public Map<String, Object> listCall(String page, String cnt) {
			
			logger.info("팀원모집listCall 요청한다");
			logger.info("페이지당 보여줄 갯수 : "+cnt);
			logger.info("요청 페이지는 : "+page);		
			int currPage = Integer.parseInt(page);
			int pagePerCnt = Integer.parseInt(cnt);		
			Map<String, Object> map = teammateService.pageList(currPage,pagePerCnt);
			
			return map;
		}
		
		@RequestMapping(value = "/teamjoin/teamjoinaddr.ajax", method=RequestMethod.GET)
		@ResponseBody
		public Map<String, Object> callList(String address){
			logger.info("listCall / address = {}",address);
			Map<String, Object> map;
			if(address.equals("")) {
				map = teammateService.teamJoinList();		
			}else {
				map = teammateService.addrList(address);			
			}
			
			return map;
		}
	
}
