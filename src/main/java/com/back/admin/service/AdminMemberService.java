package com.back.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.admin.dao.AdminMemberDAO;
import com.back.admin.dto.AdminMemberDTO;

@Service
public class AdminMemberService {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired AdminMemberDAO memberDAO;
	public Map<String, Object> list(int page, String state) {
	      Map<String, Object> result = new HashMap<String, Object>();
	      List<AdminMemberDTO> list;
	      List<AdminMemberDTO> allList = memberDAO.allList();

	      int start = (page - 1) * 10;
	      
	      if (state.equals("") == true) {
	    	  logger.info("1");
	          list = memberDAO.list(start);
	          result.put("totalPage", memberDAO.allStateCount()%10>0?memberDAO.allStateCount()/10+1:memberDAO.allStateCount()/10);
	      } else{
	    	  logger.info("2");
	    	  list = memberDAO.listFilterState(start, state);
	          result.put("totalPage", memberDAO.filteringStateCount(state)%10>0?memberDAO.filteringStateCount(state)/10+1:memberDAO.filteringStateCount(state)/10);
	      }

	      result.put("list", list);
	      result.put("allList", allList);
	      return result;
	   
	}
	public Map<String, Object> searchList(int page, String state, String memberSearchWord) {
	
		Map<String, Object> result = new HashMap<String, Object>();
		List<AdminMemberDTO> list;
		List<AdminMemberDTO> allList = memberDAO.allList();

		int start = (page - 1) * 10;

		list = memberDAO.idSearchList(memberSearchWord, start);
		logger.info("멤버 아이디 서치리스트 ");
		result.put("totalPage", memberDAO.searchMemberIdCount(memberSearchWord));
		

		result.put("list", list);
		result.put("allList", allList);
		return result;
	}
	
}