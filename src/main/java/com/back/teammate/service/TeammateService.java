package com.back.teammate.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.back.teammate.dao.TeammateDAO;
import com.back.teammate.dto.TeammateDTO;

@Service
public class TeammateService {
   Logger logger = LoggerFactory.getLogger(getClass());
   @Autowired TeammateDAO teammateDAO;
   
   public Map<String, Object> pageList(int page, String address, String position, String level) {

      Map<String, Object> result = new HashMap<String, Object>();
      List<TeammateDTO> list = null;
      List<TeammateDTO> allList = teammateDAO.allList();

      int start = (page - 1) * 10;
      
      if (address.equals("") == true && position.equals("") == true && level.equals("") == true) {
    	  logger.info("1");
          list = teammateDAO.list(start);
          result.put("totalPage", teammateDAO.allTeammateCount());
      } else if (address.equals("") == true && position.equals("") == true && level.equals("") == false) {
    	  logger.info("2");
    	  list = teammateDAO.listFilterLevel(start ,level);
          result.put("totalPage", teammateDAO.levelFilteringTeammateCount(level));
      } else if (address.equals("") == true && position.equals("") == false && level.equals("") == true) {
    	  logger.info("3");
    	  list = teammateDAO.listFilterPosition(start ,position);
          result.put("totalPage", teammateDAO.positionFilteringTeammateCount(position));
      } else if (address.equals("") == true && position.equals("") == false && level.equals("") == false) {
    	  logger.info("4");
    	  list = teammateDAO.listFilterPosotionLevel(start ,position,level);
          result.put("totalPage", teammateDAO.positionLevelFilteringTeammateCount(position,level));
      } else if (address.equals("") == false && position.equals("") == true && level.equals("") == true) {
    	  logger.info("5");
    	  list = teammateDAO.listFilterAddress(start ,address);
          result.put("totalPage", teammateDAO.addressFilteringTeammateCount(address));      
      } else if (address.equals("") == false && position.equals("") == true && level.equals("") == false) {
    	  logger.info("6");
    	  list = teammateDAO.listFilterAddressLevel(start ,address, level);
          result.put("totalPage", teammateDAO.addressLevelFilteringTeammateCount(address, level));
      } else if (address.equals("") == false && position.equals("") == false && level.equals("") == true) {
    	  logger.info("7");
    	  list = teammateDAO.listFilterAddressPosition(start ,address, position);
          result.put("totalPage", teammateDAO.addressPositionFilteringTeammateCount(address, position));
      } else if (address.equals("") == false && position.equals("") == false && level.equals("") == false) {
    	  logger.info("8");
    	  list = teammateDAO.listFilterAddressPositionLevel(start ,address, position, level);
          result.put("totalPage", teammateDAO.allFilteringTeammateCount(address, position,level));
      }
			

      result.put("list", list);
      result.put("allList", allList);
      return result;
   }

public Map<String, Object> searchList(String teammateSearchCategory, String teammateSearchWord, int page, String address, String id, String teamName) {
	Map<String, Object> result = new HashMap<String, Object>();
	List<TeammateDTO> list = new ArrayList<TeammateDTO>();
	List<TeammateDTO> allList = teammateDAO.allList();
	
	int start = (page - 1) * 10;
	logger.info(teammateSearchCategory);
	if(teammateSearchCategory.equals("teamJoinLoc")) {
		list = teammateDAO.addressSearchList(teammateSearchWord,start);
		logger.info("서치리스트 주소 ");
		result.put("list", list);
		result.put("totalPage", teammateDAO.addressFilteringCount(teammateSearchWord));
	}else if(teammateSearchCategory.equals("teamJoinName")) {
		list = teammateDAO.teamnameSearchList(teammateSearchWord,start);
		logger.info("서치리스트 팀명 ");
		result.put("list", list);
		result.put("totalPage", teammateDAO.teamnameFilteringCount(teammateSearchWord));
	}else if(teammateSearchCategory.equals("teamJoinRepresent")) {
		list = teammateDAO.representSearchList(teammateSearchWord,start);
		logger.info("서치리스트 팀장ID ");
		result.put("list", list);
		result.put("totalPage", teammateDAO.representFilteringCount(teammateSearchWord));
	}


	result.put("list", list);
	result.put("allList", allList);
	return result;
}

public void teammateDetail(String join_team_idx, Model model) {
	logger.info("팀원모집디테일서비스");
	TeammateDTO teammateDetail = teammateDAO.teammateDetail(join_team_idx);
	model.addAttribute("teammateDetail", teammateDetail);
}

public Map<String, Object> teammateJoin(String joinTeamIdx, String id, String join_state) {
    Map<String, Object> result = new HashMap<>();
    result.put("result", false);
    logger.info("팀원가입 서비스에 들어왔다.");
    logger.info("가입할 팀 = " + joinTeamIdx);
    int row = teammateDAO.duplicateChk(joinTeamIdx,id);
    if(row>0) {
    	return result;
    }
    result.put("result", teammateDAO.teammateJoin(joinTeamIdx, id, join_state));
    
    
    return result;
}

public int callMyteamInfo(String id) {
	logger.info("callMyteamInfo-service id={}",id);
	return teammateDAO.callMyteamInfo(id);
}

public int teammateWrite(TeammateDTO dto) {
	logger.info("teammateWrite-service map={}",dto);
	return teammateDAO.teammateWrite(dto);
}

public TeammateDTO teammateWriteInfo(int team_idx) {
	logger.info("teammateWriteInfo-service ={}", team_idx);
	return teammateDAO.teammateWriteInfo(team_idx);
}

public Map<String, TeammateDTO> teammateModify(int join_team_idx) {
	Map<String, TeammateDTO> map = new HashMap<String, TeammateDTO>();
	TeammateDTO dto = teammateDAO.teammateModify(join_team_idx);
	logger.info(dto.getTeam_name() + " ");
	map.put("teammateInfo", dto);
	return map;
}


   
   

}