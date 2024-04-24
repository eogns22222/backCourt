package com.back.court.dao;

import java.util.List;
import java.util.Map;

import com.back.court.dto.CourtDTO;

public interface CourtDAO {

	List<CourtDTO> list();

	void jjim(String id, int courtIdx);

	void jjimRemove(String id, int courtIdx);

	List<CourtDTO> detail(String courtIdx);

	List<String> fileNameList(String courtIdx);

	List<String> bookingStartTimeList(String courtIdx, String selectDate);

	int duplicateCheckBooking(String courtDate, String courtStartTime, String courtIdx);

	int myPoint(String id);

	void insertPointHistory(String id, int pointChange, String pointState, String WriteIdx, String WriteType);


	void insertBooking(Map<String, Object> insertMap);

	void insertPointHistory(Map<String, Object> insertMap);
}
