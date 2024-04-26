package com.back.admin.dao;

import java.util.List;
import java.util.Map;

import com.back.admin.dto.AdminCourtDTO;

public interface AdminWriteDAO {

	List<AdminCourtDTO> callCourtList();

	AdminCourtDTO callCourtInfo(String courtIdx);

	List<String> fileNameList(String courtIdx);

	List<String> bookingStartTime(String courtIdx, String selectDate);

	Boolean officialWrite(Map<String, Object> param);

}
