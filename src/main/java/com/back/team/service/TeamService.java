package com.back.team.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.team.dao.TeamDAO;
import com.back.team.dto.TeamDTO;

@Service
public class TeamService {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired TeamDAO teamDAO;
	
	public TeamDTO teamDetail(int team_idx) {
		
		return teamDAO.teamDetail(team_idx);
	}
	
	public Map<String, Object> list(int page, int team_idx, String id) {
		int start = (page - 1) * 10;
		int limitNum = 0;
		if(start == 0) {
			limitNum = 9;
		}else {
			limitNum = 10;
		}
		Map<String, Object> result = new HashMap<String, Object>();
		List<TeamDTO> listTeam = teamDAO.listTeam(start, team_idx);
		List<TeamDTO> listAppli = teamDAO.listAppli(start, team_idx);
		List<TeamDTO> listWriteTeam = teamDAO.listWriteTeam(team_idx, id);
		List<TeamDTO> listWriteGuest = teamDAO.listWriteGuest(start, team_idx, id, limitNum);
		List<TeamDTO> listDrop = teamDAO.listDrop(start, team_idx);
		
		result.put("listTeam", listTeam);
		result.put("totalPageTeam", teamDAO.teamTotal(team_idx));
		result.put("listAppli", listAppli);
		result.put("totalPageAppli", teamDAO.appliTotal(team_idx));
		result.put("listWriteTeam", listWriteTeam);
		result.put("listWriteGuest", listWriteGuest);
		result.put("totalPageWrite", teamDAO.writeTotal(team_idx, id));
		result.put("listDrop", listDrop);
		result.put("totalPageDrop", teamDAO.dropTotal(team_idx));
		
		return result;
	}

	public TeamDTO userPop(String userId) {
		return teamDAO.userPop(userId);
	}
	
}

















