package com.back.teammate.dao;

import java.util.List;

import com.back.teammate.dto.TeammateDTO;

public interface TeammateDAO {

	List<TeammateDTO> list(int pagePerCnt, int start);

	int allCount(int pagePerCnt);

	



}
