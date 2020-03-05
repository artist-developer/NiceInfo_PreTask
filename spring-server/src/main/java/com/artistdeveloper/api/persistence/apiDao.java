package com.artistdeveloper.api.persistence;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.artistdeveloper.api.domain.bldRgstEntity;

@Repository
public class apiDao {
	@Inject private SqlSession sqlSession;
	private final String NAMESPACE ="mapper.apiMapper";
	
	public int api(List<bldRgstEntity> list) {
		return sqlSession.insert(NAMESPACE+".apiInsert",list);
		}
	public List<Object> apiList() {
		return sqlSession.selectList(NAMESPACE+".apiList");
	}
	public int apiDelete() {
		return sqlSession.insert(NAMESPACE+".apiDelete");
		}
	public int apiHistoryInsert(HashMap<String, Object> map) {
		return sqlSession.insert(NAMESPACE+".apiHistoryInsert", map);
		}
}
