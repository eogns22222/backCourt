package com.back.court.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.court.dao.CourtDAO;
import com.back.court.dto.CourtDTO;

@Service
public class CourtService {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	CourtDAO courtDAO;

	public Map<String, Object> list(int page, String address) {

		Map<String, Object> result = new HashMap<String, Object>();
		List<CourtDTO> list;
		List<CourtDTO> allList = courtDAO.allList();

		int start = (page - 1) * 10;

		if (address.equals("") == true) {
//			logger.info("servcie.list / address = {} /", address);
			list = courtDAO.list(start);
			result.put("totalPage", courtDAO.allCourtCount());

		} else {
//			logger.info("servcie.list / address = {} /", address);
			list = courtDAO.listFilterAddress(start, address);
			result.put("totalPage", courtDAO.addressFilteringCourtCount(address));

		}

		result.put("list", list);
		result.put("allList", allList);
		return result;
	}

	public boolean jjim(String id, int courtIdx) {
		try {
			courtDAO.jjim(id, courtIdx);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public Boolean jjimRemove(String id, int courtIdx) {
		try {
			courtDAO.jjimRemove(id, courtIdx);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public Map<String, Object> searchList(String courtSearchCategory, String courtSearchWord, int page,String address) {

		Map<String, Object> result = new HashMap<String, Object>();
		List<CourtDTO> list;
		List<CourtDTO> allList = courtDAO.allList();
		
		int start = (page - 1) * 10;
		
		if(courtSearchCategory.equals("courtAddress")) {
			list = courtDAO.addressSearchList(courtSearchWord,start);
			logger.info("서치리스트 코트어드레스 ");
			result.put("list", list);
			result.put("totalPage", courtDAO.addressFilteringCourtCount(courtSearchWord));
		}else if(courtSearchCategory.equals("courtName")) {
			list = courtDAO.nameSearchList(courtSearchWord,start);
			logger.info("서치리스트 코트네임 ");
			result.put("list", list);
			result.put("totalPage", courtDAO.nameFilteringCourtCount(courtSearchWord));
		}
		
//		if (address.equals("") == true) {
////			logger.info("servcie.list / address = {} /", address);
//			list = courtDAO.SearchList(courtSearchCategory,courtSearchWord,start);
//			result.put("totalPage", courtDAO.allCourtCount());
//
//		} else {
////			logger.info("servcie.list / address = {} /", address);
//			list = courtDAO.listFilterAddress(start, address);
//			result.put("totalPage", courtDAO.addressFilteringCourtCount(address));
//
//		}
		
		result.put("allList", allList);
		return result;
	}

	public Map<String, Object> detail(String courtIdx) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<CourtDTO> detail = courtDAO.detail(courtIdx);
		List<String> fileName = courtDAO.fileNameList(courtIdx);
		List<String> bookingStartTime = courtDAO.bookingStartTimeList(courtIdx);
		
		
		
		map.put("detail", detail);
		map.put("fileName", fileName);
		map.put("bookingStartTime", bookingStartTime);
		
		return map;
	}


}
