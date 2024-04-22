package com.back.court.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

	public Map<String, Object> list(int page, String address, String searchCategory, String searchWord,
			String searchFlag) {

		// 시작 페이지
		int start = (page - 1) * 10;

		Map<String, Object> map = new HashMap<String, Object>();

		// 모든 리스트
		List<CourtDTO> list = courtDAO.list();
		// 가공될 리스트
		List<CourtDTO> processedList = new ArrayList<CourtDTO>();
		
		// 주소 리스트
		map.put("addressList", addressDeduplication(list));

		// 지역명 검색
		if (searchFlag.equals("true") && searchCategory.equals("courtAddress")) { //지역명 검색
			processedList = addressProcessedListing(list, searchWord);
		} else if (searchFlag.equals("true") && searchCategory.equals("courtName")) {//구장명 검색
			processedList = nameProcessedListing(list, searchWord);
		} else if (address.isEmpty() == false) { // 주소필터
			processedList = addressProcessedListing(list, address);
		} else { // 기본
			processedList = list;
		}

		// 리스트에 추가
		map.put("list", pagingList(processedList, start));
		
		// 총 페이지
		map.put("totalPage", processedList.size());

		return map;
	}
	// 주소 구 단위로 자르고 가나다순 정렬
	public List<String> addressDeduplication(List<CourtDTO> list) {
		Set<String> set = new HashSet<String>();
		
		for (CourtDTO dto : list) {
			set.add(dto.getCourtAddress().split(" ")[1]);
		}
		List<String> DeduplicationList = new ArrayList<String>(set);
		Collections.sort(DeduplicationList);
		return DeduplicationList;
	}

	// 구장명 필터 메서드
	public List<CourtDTO> nameProcessedListing(List<CourtDTO> list, String courtName) {
		List<CourtDTO> processedList = new ArrayList<CourtDTO>();
		for (CourtDTO dto : list) {
			if (dto.getCourtName().contains(courtName)) {
				processedList.add(dto);
			}
		}
		return processedList;
	}

	// 주소 필터 메서드
	public List<CourtDTO> addressProcessedListing(List<CourtDTO> list, String address) {
		List<CourtDTO> processedList = new ArrayList<CourtDTO>();
		for (CourtDTO dto : list) {
			if (dto.getCourtAddress().contains(address)) {
				processedList.add(dto);
			}
		}
		return processedList;
	}

	// 페이징 처리위해 10개씩만 
	public List<CourtDTO> pagingList(List<CourtDTO> processedList, int start) {
		List<CourtDTO> list = new ArrayList<CourtDTO>();
		for (int i = start; i < Math.min(start + 10, processedList.size()); i++) {
			list.add(processedList.get(i));
		}
		return list;
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

	public Map<String, Object> detail(String courtIdx, String selectDate) {
		Map<String, Object> map = new HashMap<String, Object>();

		List<CourtDTO> detail = courtDAO.detail(courtIdx);
		List<String> fileName = courtDAO.fileNameList(courtIdx);
		List<String> bookingStartTime = courtDAO.bookingStartTimeList(courtIdx, selectDate);

		map.put("detail", detail);
		map.put("fileName", fileName);
		map.put("bookingStartTime", bookingStartTime);

		return map;
	}

}
