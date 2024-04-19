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

	public Map<String, Object> list(String address) {

		Map<String, Object> result = new HashMap<String, Object>();
		List<CourtDTO> list;

		if (address.equals("") == true) {
			logger.info("servcie.list / address = {} /",address);
			list = courtDAO.list();
			result.put("totalPage", courtDAO.allCourtCount());

		} else {
			logger.info("servcie.list / address = {} /",address);
			list = courtDAO.listFilterAddress(address);
			result.put("totalPage", courtDAO.addressFilteringCourtCount(address));

		}

		result.put("list", list);

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

}
