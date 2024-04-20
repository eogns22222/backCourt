package com.back.official.dao;

import java.util.List;

import com.back.official.dto.OfficialDTO;

public interface OfficialDAO {

	List<OfficialDTO> list(int start);

	List<OfficialDTO> listFilterAddress(int start, String address);

	List<OfficialDTO> listFilterLevel(int start, String address);
	
	List<OfficialDTO> listFilterAll(int start, String address, String level);
	
	List<OfficialDTO> allList();

	int allMatchCount();

	int addressFilteringMatchCount(String address);

	int levelFilteringMatchCount(String level);

	int allFilteringMatchCount(String address, String level);

	List<OfficialDTO> searchList(int start, String courtSearchWord);

	Object searchMatchCount(String courtSearchWord);



}
