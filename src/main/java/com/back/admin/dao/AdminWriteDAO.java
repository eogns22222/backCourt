package com.back.admin.dao;

import java.util.List;

import com.back.admin.dto.AdminCourtDTO;
import com.back.admin.dto.AdminWriteDTO;

public interface AdminWriteDAO {

	List<AdminCourtDTO> callCourtList();

	List<AdminWriteDTO> officialList();

}
