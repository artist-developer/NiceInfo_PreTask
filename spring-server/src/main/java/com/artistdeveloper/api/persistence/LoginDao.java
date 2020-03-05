package com.artistdeveloper.api.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.artistdeveloper.api.domain.MemberEntity;
import com.artistdeveloper.api.util.Common;


/**
 * @version : java1.8 
 * @author : ohs
 * @date : 2018. 8. 8.
 * @class :
 * @message :
 * @constructors :
 * @method :
*/
@Repository
public class LoginDao {
	
	@Inject private SqlSession sqlSession;
	Common common = new Common();
	private final String NAMESPACE ="mapper.LoginMapper";
	
	public SqlSession getSqlSession() {return sqlSession;}
	public void setSqlSession(SqlSession sqlSession) {this.sqlSession = sqlSession;}
	
	public String login(MemberEntity vo){
		return sqlSession.selectOne(NAMESPACE+".login",vo);
	}
	
	public int join(MemberEntity dd){
		return sqlSession.insert(NAMESPACE+".join",dd);
	}
	
	public MemberEntity user_info(MemberEntity vo){
		return sqlSession.selectOne(NAMESPACE+".user_info",vo);
	}
	public MemberEntity user_info_member_seq(MemberEntity vo){
		return sqlSession.selectOne(NAMESPACE+".user_info_member_seq",vo);
	}
	
	public int email_check(MemberEntity vo){
		return sqlSession.selectOne(NAMESPACE+".email_check",vo);
	}
	public int phone_check(MemberEntity vo){
		return sqlSession.selectOne(NAMESPACE+".phone_check", vo);
	}
	public int nick_check(MemberEntity vo){
		return sqlSession.selectOne(NAMESPACE+".nick_check", vo);
	}
	public int push_token_update(MemberEntity dy){  
		return sqlSession.update(NAMESPACE+".push_token_update",dy);
	}
	
	public int joinGuest(MemberEntity dy){  
		int record = sqlSession.insert(NAMESPACE+".joinGuest",dy);
		if(record > 0) {
			record += sqlSession.update(NAMESPACE+".guestUpdate",dy);
		}
		return record == 2 ? 1 : 0;
	}
}
