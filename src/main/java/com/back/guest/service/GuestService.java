package com.back.guest.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.guest.dao.GuestDAO;
import com.back.guest.dto.GuestDTO;

@Service
public class GuestService {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired GuestDAO guestDAO;
	public int guestWrite(Map<String, Object> map) {
		logger.info("guest-service map={}",map);
		return guestDAO.guestWrite(map);
	}
	
	public List<GuestDTO> courtList(String id) {
		return guestDAO.courtList(id);
	}

	public int callmyteam(String id) {
		return guestDAO.callmyteam(id);
	}
	
}
