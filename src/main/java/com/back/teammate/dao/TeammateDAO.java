package com.back.teammate.dao;

import java.util.List;

import com.back.teammate.dto.TeammateDTO;

public interface TeammateDAO {

	List<TeammateDTO> teamJoinList();

	List<TeammateDTO> pageList(int pagePerCnt, int start);

	int allCount(int pagePerCnt);

	List<TeammateDTO> listFilterAddress(String address);


	



}
