package com.back.header.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.header.dao.HeaderDAO;
import com.back.header.dto.HeaderDTO;

@Service
public class HeaderService {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired HeaderDAO headerdao;
	
	public List<HeaderDTO> teamList(String id) {
		
		return headerdao.teamList(id);
	}

	public List<HeaderDTO> noticeList(String id) {
		
		return headerdao.noticeList(id);
	}

	public void delete(String idx) {
		
		headerdao.delete(idx);
	}
	
}
















