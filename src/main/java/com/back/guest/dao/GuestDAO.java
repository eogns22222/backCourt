package com.back.guest.dao;

import java.util.List;
import java.util.Map;

import com.back.guest.dto.GuestDTO;

public interface GuestDAO {

	int guestWrite(Map<String, Object> map);

	List<GuestDTO> courtList(String id);

	int callmyteam(String id);

}
