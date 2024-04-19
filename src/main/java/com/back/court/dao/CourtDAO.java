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

	List<CourtDTO> SearchList(String courtSearchCategory, String courtSearchWord, int start);

}
