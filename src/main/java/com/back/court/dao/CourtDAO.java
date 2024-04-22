package com.back.court.dao;

import java.util.List;

import com.back.court.dto.CourtDTO;

public interface CourtDAO {

	List<CourtDTO> list(int page);

	void jjim(String id, int courtIdx);

	void jjimRemove(String id, int courtIdx);


	List<CourtDTO> listFilterAddress(int page, String address);

	int allCourtCount();

	int addressFilteringCourtCount(String address);

	List<CourtDTO> allList();


	List<CourtDTO> addressSearchList(String courtSearchWord, int start);

	List<CourtDTO> nameSearchList(String courtSearchWord, int start);


	int nameFilteringCourtCount(String courtSearchWord);

	List<CourtDTO> detail(String courtIdx);

	List<String> fileNameList(String courtIdx);

	List<String> bookingStartTimeList(String courtIdx);

}
