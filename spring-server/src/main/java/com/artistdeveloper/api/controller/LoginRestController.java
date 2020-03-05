package com.artistdeveloper.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.artistdeveloper.api.domain.MemberEntity;
import com.artistdeveloper.api.domain.Meta;
import com.artistdeveloper.api.service.LoginService;
import com.artistdeveloper.api.util.Common;
import com.artistdeveloper.api.util.Util;


/**
 * @version : java1.8 
 * @author : ohs
 * @date : 2018. 8. 8.
 * @class :
 * @message :
 * @constructors :
 * @method :
 */
@RestController
public class LoginRestController {
	
	
	
	@Inject LoginService loginService;
	Common common = new Common();
	
	/**
	 * 
	 * @msg 로그인
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 * @Explain : POST 방식으로 request 를 받아 JWT로 해당 계정의 토큰을 조회후 Meta Class에 code값을 반환
	 */
	@RequestMapping(value = "/login", method = {RequestMethod.POST})
	public ResponseEntity<Map<String, Object>> login(HttpServletRequest request, Model model, HttpSession session, @RequestBody MemberEntity vo) throws Exception {
		
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		Meta meta = new Meta();
		
		HashMap<String, Object> rst = new HashMap<String, Object>();
		
		String login_rst = loginService.login(vo);
		
		if(Util.chkNullData(login_rst, "200")) {
			String push_token = vo.getPush_token();
			
			vo = loginService.user_info(vo);
			String token = common.makeToken(vo.getId(), vo.getPassword(), String.valueOf(vo.getMember_seq()));
			rst.put("member", vo);
			rst.put("authToken", token);
			
			vo = loginService.user_info(vo);
			session.setAttribute("MemberEntity", vo);
			meta.setResult_code(200);  //계정불일치 
			meta.setResult_msg("로그인에 성공하셨습니다.");
		} else if(Util.chkNullData(login_rst, "501")){
			meta.setResult_code(500);  //계정불일치 
			meta.setResult_msg("아이디가 틀렸습니다.");
		} else {
			meta.setResult_code(500); //패스워드 불일치
			meta.setResult_msg("비밀번호가 틀렸습니다.");
		}
		
	    map.put("result", meta);
	    map.put("data", rst);
	    
	    try {entity = new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);} 
	    catch (Exception e) {entity = new ResponseEntity<Map<String, Object>>(map,HttpStatus.BAD_REQUEST);}
	    Common.returnPrint(Common.GmakeDynamicValueObject(entity));
	    return entity;
	}
}
