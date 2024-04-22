package com.back.court.dao;

import java.util.List;

import com.back.court.dto.CourtDTO;

public interface CourtDAO {

	List<CourtDTO> list();

	void jjim(String id, int courtIdx);

	void jjimRemove(String id, int courtIdx);

	List<CourtDTO> detail(String courtIdx);

	List<String> fileNameList(String courtIdx);

	List<String> bookingStartTimeList(String courtIdx, String selectDate);

}
