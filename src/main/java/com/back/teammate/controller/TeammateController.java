package com.back.teammate.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.back.teammate.service.TeammateService;

@Controller
public class TeammateController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired TeammateService teammateService;

	@RequestMapping(value="/teamjoin")
	public String list(Model model) {
		logger.info("list 요청한다");
		//List<BoardDTO> list = boardService.list();
		//model.addAttribute("list", list); // response 로 map 형태로 보내야 된다.
		return "teamjoin";
	}
	
	// 비동기 방식 : 일단 페이지 도착 한 다음
		@RequestMapping(value="/teamjoinlist.ajax")
		@ResponseBody // response 객체로 반환
		public Map<String, Object> listCall(String page, String cnt) {
			
			logger.info("listCall 요청한다");
			logger.info("페이지당 보여줄 갯수 : "+cnt);
			logger.info("요청 페이지는 : "+page);		
			int currPage = Integer.parseInt(page);
			int pagePerCnt = Integer.parseInt(cnt);		
			Map<String, Object> map = teammateService.list(currPage,pagePerCnt);
			
			return map; //json 과 가장 닮은 map으로 반환
		}
	
}
