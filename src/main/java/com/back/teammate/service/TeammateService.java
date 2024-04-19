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
	public Map<String, Object> list(int currPage, int pagePerCnt) {

	int start = (currPage-1) * pagePerCnt;
		
		Map<String, Object> result = new HashMap<String, Object>();
		List<TeammateDTO> list= teammateDAO.list(pagePerCnt,start);
		logger.info("list size : "+list.size());
		result.put("list", list);
		result.put("currPage", currPage);
		result.put("totalPages", teammateDAO.allCount(pagePerCnt));
		
		return result;
	}

}
