package com.back.admin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.back.admin.dao.AdminWriteDAO;
import com.back.admin.dto.AdminCourtDTO;
import com.back.admin.dto.AdminWriteDTO;

@Service
public class AdminWriteService {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	AdminWriteDAO adminWriteDAO;

	public Map<String, Object> callCourtList(int currentPage, String searchWord) {
		int start = (currentPage - 1) * 10;
		logger.info("::::::::::::callCourtList service in:::::::::::::");
		Map<String, Object> result = new HashMap<String, Object>();

		logger.info(":::::::::::::Input List:::::::::::::::::::::::::");
		List<AdminCourtDTO> list = adminWriteDAO.callCourtList();
		List<AdminCourtDTO> processedList = new ArrayList<AdminCourtDTO>();
		logger.info("::::::::::::::::::::::::::::::::::::::");
		for (AdminCourtDTO adminCourtDTO : list) {
			logger.info("courtIdx = " + adminCourtDTO.getCourtIdx() + "\n" + "courtName = "
					+ adminCourtDTO.getCourtName() + "\n" + "courtAddress = " + adminCourtDTO.getCourtAddress() + "\n");
		}
		processedList = list;
		logger.info("::::::::::::::::::::::::::::::::::::::");
		if (searchWord.isEmpty() == false) {
			processedList = addressProcessedList(processedList, searchWord);
		}

		int totalPage = processedList.size() % 10 > 0 ? processedList.size() / 10 + 1 : processedList.size() / 10;
		result.put("totalPage", totalPage);

		processedList = pagingList(processedList, start);
		result.put("list", processedList);

		logger.info("::::::::::::callCourtList service out:::::::::::::");
		return result;
	}

	private List<AdminCourtDTO> pagingList(List<AdminCourtDTO> processedList, int start) {
		List<AdminCourtDTO> list = new ArrayList<AdminCourtDTO>();
		for (int i = start; i < Math.min(start + 10, processedList.size()); i++) {
			list.add(processedList.get(i));
		}
		return list;
	}

	public List<AdminCourtDTO> addressProcessedList(List<AdminCourtDTO> processedList, String searchWord) {
		List<AdminCourtDTO> list = new ArrayList<AdminCourtDTO>();
		for (AdminCourtDTO dto : list) {
			if (dto.getCourtAddress().contains(searchWord)) {
				list.add(dto);
			}
		}
		return list;
	}

	public Map<String, Object> callCourtInfo(String courtIdx, String selectDate) {
		logger.info("callCourtInfo service in :::::::::::::::::");
		logger.info("courtIdx = " + courtIdx);
		logger.info("selectDate = " + selectDate);
		logger.info("callCourtInfo service in :::::::::::::::::");
		Map<String, Object> result = new HashMap<String, Object>();
		AdminCourtDTO dto = adminWriteDAO.callCourtInfo(courtIdx);

		List<String> fileName = adminWriteDAO.fileNameList(courtIdx);
		List<String> bookingStartTime = adminWriteDAO.bookingStartTime(courtIdx, selectDate);
		result.put("fileName", fileName);
		result.put("courtInfo", dto);
		for (String string : bookingStartTime) {
			logger.info("bookingStartTime = " + string);
		}
		result.put("bookingStartTime", bookingStartTime);
		return result;
	}

	public Map<String, Boolean> officialWrite(Map<String, Object> param) {
		int startTime = Integer.parseInt((String) param.get("officialStartTime"));

		param.put("officialEndTime", startTime + 2);
		logger.info("params: {}", param);
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		result.put("result", adminWriteDAO.officialWrite(param));
		return result;
	}

	public void officialUpdateGo(Model model, String officialIdx) {

		AdminWriteDTO dto = adminWriteDAO.officialUpdateGo(officialIdx);
		model.addAttribute("officialInfo", dto);
	}

	public Map<String, Boolean> officialUpdate(Map<String, Object> param) {
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		result.put("result", adminWriteDAO.officialUpdate(param));
		return result;
	}

}
