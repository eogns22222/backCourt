package com.back.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.admin.dao.AdminReportDAO;
import com.back.admin.dto.AdminCourtDTO;
import com.back.admin.dto.AdminReportDTO;

@Service
public class AdminReportService {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	AdminReportDAO adminReportDAO;

	public Map<String, Object> list(String reportState, int page, String reportSearch, String searchFlag) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<AdminReportDTO> list;

		int start = (page - 1) * 10;

		if (searchFlag.equals("true")) {
			logger.info(searchFlag+"service 1st if");
			list = adminReportDAO.searchList(start, reportSearch);
			result.put("totalPage", adminReportDAO.searchReportCount(reportSearch));
		} else {
			if (reportState.equals("")) {
				logger.info(searchFlag+"service 2nd if");
				list = adminReportDAO.list(start);
				result.put("totalPage", adminReportDAO.allReportCount());
			} else {
				logger.info(searchFlag+"service 2nd else");
				list = adminReportDAO.filteringList(start, reportState);
				result.put("totalPage", adminReportDAO.categoryReportCount(reportState));
			}

		}
		result.put("list", list);

		return result;
	}

}
