package com.back.team.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.team.dao.TeamDAO;

@Service
public class TeamService {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired TeamDAO teamDAO;
	
}
