package com.back.teammate.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.teammate.dao.TeammateDAO;
import com.back.teammate.dto.TeammateDTO;

@Service
public class TeammateService {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired TeammateDAO teammateDAO;
	
	public Map<String, Object> pageList(int currPage, int pagePerCnt) {

	int start = (currPage-1) * pagePerCnt;
		
		Map<String, Object> result = new HashMap<String, Object>();
		List<TeammateDTO> pageList= teammateDAO.pageList(pagePerCnt,start);
		logger.info("팀원모집 list size : "+pageList.size());
		result.put("pageList", pageList);
		result.put("currPage", currPage);
		result.put("totalPages", teammateDAO.allCount(pagePerCnt));
		
		return result;
	}
	
	public Map<String, Object> teamJoinList() {
		
		Map<String, Object> result = new HashMap<String, Object>();
		List<TeammateDTO> teamJoinList = teammateDAO.teamJoinList();
		
		result.put("teamJoinList", teamJoinList);
		
		return result;
	}
	
	public Map<String, Object> addrList(String address) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		List<TeammateDTO> addrList = teammateDAO.listFilterAddress(address);
		
		result.put("addrlist", addrList);
		
		return result;
	}

}
