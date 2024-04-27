package com.back.admin.service;

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

import com.back.admin.dao.AdminWriteDAO;
import com.back.admin.dto.AdminCourtDTO;
import com.back.admin.dto.AdminWriteDTO;

@Service
public class AdminWriteService {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	AdminWriteDAO adminWriteDAO;

	public Map<String, Object> callCourtList() {

		logger.info("::::::::::::callCourtList service in:::::::::::::");
		Map<String, Object> result = new HashMap<String, Object>();
		logger.info(":::::::::::::Input List:::::::::::::::::::::::::");
		List<AdminCourtDTO> list = adminWriteDAO.callCourtList();
		logger.info("::::::::::::::::::::::::::::::::::::::");
		for (AdminCourtDTO adminCourtDTO : list) {
			logger.info(
					"courtIdx = "+adminCourtDTO.getCourtIdx()+"\n"
					+"courtName = "+adminCourtDTO.getCourtName()+"\n"
					+"courtAddress = "+adminCourtDTO.getCourtAddress()+"\n"
					);
		}
		logger.info("::::::::::::::::::::::::::::::::::::::");
		result.put("list", list);

		logger.info("::::::::::::callCourtList service out:::::::::::::");
		return result;
	}

	// official 리스트
	public Map<String, Object> officialList(int page, String searchFlag, String searchWord, String address, String level) {

		// 시작 페이지
		int start = (page - 1) * 10;

		Map<String, Object> map = new HashMap<String, Object>();
		
		// 모든 리스트
		List<AdminWriteDTO> list = adminWriteDAO.officialList();
		// 가공될 리스트
		List<AdminWriteDTO> processedList = new ArrayList<AdminWriteDTO>();
		
		// 주소 리스트
		map.put("addressList", addressDeduplication(list));
		
		// 검색
		if (searchFlag.equals("true")) { // 지역명 검색
			processedList = addressProcessedListing(list, searchWord);
		} else if (address.isEmpty() == false || level.isEmpty() == false) { // 필터
			processedList = list;
			if (address.isEmpty() == false) {
				processedList = addressProcessedListing(processedList, address);
			}
			if (level.isEmpty() == false) {
				processedList = levelProcessedListing(processedList, level);
			}
		} else { // 기본
			processedList = list;
		}
		
		// 리스트에 추가
		map.put("list", pagingList(processedList, start));
		
		int totalPage = processedList.size() % 10 > 0 ? processedList.size() / 10 + 1 : processedList.size() / 10;
		// 총 페이지
		map.put("totalPage", totalPage);
		
		return map;
	}
	
	// 주소 구 단위로 자르고 가나다순 정렬
	public List<String> addressDeduplication(List<AdminWriteDTO> list) {
		Set<String> set = new HashSet<String>();

		for (AdminWriteDTO dto : list) {
			set.add(dto.getCourt_address().split(" ")[1]);
		}
		List<String> DeduplicationList = new ArrayList<String>(set);
		Collections.sort(DeduplicationList);
		return DeduplicationList;
	}
	
	// 주소 필터 메서드
	public List<AdminWriteDTO> addressProcessedListing(List<AdminWriteDTO> list, String address) {
		List<AdminWriteDTO> processedList = new ArrayList<AdminWriteDTO>();
		for (AdminWriteDTO dto : list) {
			if (dto.getCourt_address().contains(address)) {
				processedList.add(dto);
			}
		}
		return processedList;
	}
	
	// 레벨 필터 메서드
	public List<AdminWriteDTO> levelProcessedListing(List<AdminWriteDTO> list, String level) {
		List<AdminWriteDTO> processedList = new ArrayList<AdminWriteDTO>();
		for (AdminWriteDTO dto : list) {
			if (dto.getOfficial_match_level().contains(level)) {
				processedList.add(dto);
			}
		}
		return processedList;
	}
	
	// 페이징 처리위해 10개씩만
	public List<AdminWriteDTO> pagingList(List<AdminWriteDTO> processedList, int start) {
		List<AdminWriteDTO> list = new ArrayList<AdminWriteDTO>();
		for (int i = start; i < Math.min(start + 10, processedList.size()); i++) {
			list.add(processedList.get(i));
		}
		return list;
	}

}






















