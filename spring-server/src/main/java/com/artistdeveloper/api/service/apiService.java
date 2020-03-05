package com.artistdeveloper.api.service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.artistdeveloper.api.domain.bldRgstEntity;
import com.artistdeveloper.api.persistence.apiDao;

@Service
public class apiService {
	@Inject apiDao dao;
	
	public int api(List<bldRgstEntity> list){
		return dao.api(list);
	}
	
	public List<Object> apiList() {
		return dao.apiList();
	}
	
	public int apiDelete(){
		return dao.apiDelete();
	}
	public int apiHistoryInsert(HashMap<String,Object> map){
		return dao.apiHistoryInsert(map);
	}
	
}
