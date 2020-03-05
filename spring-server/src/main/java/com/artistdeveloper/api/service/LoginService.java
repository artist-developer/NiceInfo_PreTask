package com.artistdeveloper.api.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.artistdeveloper.api.domain.MemberEntity;
import com.artistdeveloper.api.persistence.LoginDao;


/**
 * @version : java1.8 
 * @author : ohs
 * @date : 2018. 8. 8.
 * @class :
 * @message :
 * @constructors :
 * @method :
*/
@Service
public class LoginService {
	
	@Inject LoginDao loginDao;
	
	public String login(MemberEntity dy){
		return loginDao.login(dy);
	}
	public int join(MemberEntity dy){
		return loginDao.join(dy);
	}
	public MemberEntity user_info(MemberEntity dy){
		return loginDao.user_info(dy);
	}
	public MemberEntity user_info_member_seq(MemberEntity dy){
		return loginDao.user_info_member_seq(dy);
	}
	public int email_check(MemberEntity dy){
		return loginDao.email_check(dy);
	}
	public int phone_check(MemberEntity dy){ 
		return loginDao.phone_check(dy);
	}
	public int nick_check(MemberEntity dy){ 
		return loginDao.nick_check(dy);
	}
	public int push_token_update(MemberEntity dy){
		return loginDao.push_token_update(dy); 
	}
	
	public int joinGuest(MemberEntity dy){
		return loginDao.joinGuest(dy); 
	}
}
