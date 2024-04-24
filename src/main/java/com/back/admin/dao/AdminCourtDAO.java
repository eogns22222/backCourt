package com.back.admin.dao;

import java.util.List;

import com.back.admin.dto.AdminCourtDTO;

public interface AdminCourtDAO {

	List<AdminCourtDTO> allList();

	List<AdminCourtDTO> list(int start);

	Object allCourtCount();
 
	List<AdminCourtDTO> listFilterAddress(int start, String address);

	Object addressFilteringCourtCount(String address);
	List<AdminCourtDTO> addressSearchList(String courtSearchWord, int start);

	List<AdminCourtDTO> nameSearchList(String courtSearchWord, int start);

	Object nameFilteringCourtCount(String courtSearchWord);

	Boolean write(AdminCourtDTO dto);

	void courtImageUpload(String idx, String newFileName);


}
