package com.back.admin.dao;

import java.util.List;
import java.util.Map;

import com.back.admin.dto.AdminCourtDTO;
import com.back.admin.dto.AdminWriteDTO;

public interface AdminWriteDAO {

	List<AdminCourtDTO> callCourtList();

	List<AdminWriteDTO> officialList();

	AdminCourtDTO callCourtInfo(String courtIdx);

	List<String> fileNameList(String courtIdx);

	List<String> bookingStartTime(String courtIdx, String selectDate);

	Boolean officialWrite(Map<String, Object> param);

	AdminWriteDTO officialUpdateGo(String officialIdx);

	Boolean officialUpdate(Map<String, Object> param);


}
