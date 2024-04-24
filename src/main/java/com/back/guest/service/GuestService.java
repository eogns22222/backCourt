package com.back.guest.service;

import java.util.HashMap;
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

	public Map<String, GuestDTO> guestModify(int guestIdx) {
		Map<String, GuestDTO> map = new HashMap<String, GuestDTO>();
		GuestDTO dto = guestDAO.guestModify(guestIdx);
		logger.info(dto.getCourt_name()+" ");
		map.put("guestInfo", dto);
		return map;
	}

	public Map<String, Object> writeUpdate(String guest_info, String guest_level, String guest_position,
			String guest_gender, String guest_to, int guest_fee, String guestIdx) {
		guestDAO.writeUpdate(guest_info, guest_level, guest_position, guest_gender, guest_to, guest_fee, guestIdx);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
	
}
