package com.back.official.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.official.dao.OfficialDAO;
import com.back.official.dto.OfficialDTO;

@Service
public class OfficialService {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired OfficialDAO officialDAO;
	
	public Map<String, Object> list(int page, String address, String level) {

		Map<String, Object> result = new HashMap<String, Object>();
		List<OfficialDTO> list = null;
		List<OfficialDTO> allList = officialDAO.allList();

		int start = (page - 1) * 10;
		
		if (address.equals("") == true && level.equals("") == true) {
//			logger.info("servcie.list / address = {} /", address);
			list = officialDAO.list(start);
			result.put("totalPage", officialDAO.allMatchCount());

		}else if(address.equals("") == false && level.equals("") == true) {
//			logger.info("servcie.list / address = {} /", address);
			list = officialDAO.listFilterAddress(start, address);
			result.put("totalPage", officialDAO.addressFilteringMatchCount(address));
			
		}else if(address.equals("") == true && level.equals("") == false) {
//			logger.info("servcie.list / address = {} /", address);
			list = officialDAO.listFilterLevel(start, level);
			result.put("totalPage", officialDAO.levelFilteringMatchCount(level));

		}else if(address.equals("") == false && level.equals("") == false) {
//			logger.info("servcie.list / address = {} /", address);
			list = officialDAO.listFilterAll(start, address, level);
			result.put("totalPage", officialDAO.allFilteringMatchCount(address, level));

		}

		result.put("list", list);
		result.put("allList", allList);
		return result;
	}
	
}





















