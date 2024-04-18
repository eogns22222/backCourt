package com.back.header.dao;

import java.util.List;

import com.back.header.dto.HeaderDTO;

public interface HeaderDAO {

	List<HeaderDTO> teamList(String id);

	List<HeaderDTO> noticeList(String id);

	void delete(String idx);

}
