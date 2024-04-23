package com.back.team.dao;

import java.util.List;

import com.back.team.dto.TeamDTO;

public interface TeamDAO {

	TeamDTO teamDetail(int team_idx);

	List<TeamDTO> listTeam(int start, int team_idx);

	int teamTotal(int team_idx);

	List<TeamDTO> listAppli(int start, int team_idx);

	int appliTotal(int team_idx);

	List<TeamDTO> listWriteTeam(int team_idx, String id);

	List<TeamDTO> listWriteGuest(int start, int team_idx, String id, int limitNum);

	int writeTotal(int team_idx, String id);

	List<TeamDTO> listDrop(int start, int team_idx);

	int dropTotal(int team_idx);

	TeamDTO userPop(String userId);

}












