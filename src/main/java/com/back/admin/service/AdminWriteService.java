package com.back.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.admin.dao.AdminWriteDAO;
import com.back.admin.dto.AdminCourtDTO;

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

}
