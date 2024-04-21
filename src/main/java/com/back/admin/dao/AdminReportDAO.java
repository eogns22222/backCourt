package com.back.admin.dao;

import java.util.List;

import com.back.admin.dto.AdminReportDTO;

public interface AdminReportDAO {

	List<AdminReportDTO> searchList(int start, String searchWord);

	int allReportCount();

	int searchReportCount(String searchWord);

	List<AdminReportDTO> list(int start);

	List<AdminReportDTO> filteringList(int start, String reportState);

	int categoryReportCount(String reportState);

}
