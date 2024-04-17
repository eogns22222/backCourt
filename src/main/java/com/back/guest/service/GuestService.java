package com.back.guest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.guest.dao.GuestDAO;

@Service
public class GuestService {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired GuestDAO guestDAO;
	
}
