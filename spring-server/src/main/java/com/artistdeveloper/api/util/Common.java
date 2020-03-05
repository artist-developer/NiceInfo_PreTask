package com.artistdeveloper.api.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * @version : java1.8
 * @author : ohs
 * @date : 2018. 8. 8.
 * @class :
 * @message : API 서버 공통 소스
 * @constructors :
 * @method :
 */
public class Common {
	public static final int JWT_EX_TIME = 4320 ; //24시간 유효함 / 카카오톡 access_token = 12-24시간, refresh_token 한달 
	
	
	//-----------------      JWT 토큰      --------------------------------------------------//
	private String SALT = "secret";
	private String AUTHORIZATION = "authToken";
	/**
	 * 로그인시 JWT token 생성
	 */
	public String makeToken(String email, String pw, String i){
		long currentTime = System.currentTimeMillis()+(1000*60*JWT_EX_TIME); 
		
		String jwt = "";
		jwt = Jwts.builder().
			setSubject(pw).
			setExpiration(new Date(currentTime)).
			claim("email", email).
			claim("member_seq", i).
			signWith(SignatureAlgorithm.HS256, this.generateKey()).
			compact();
		return jwt;
	}
	
	/**
	 * 로그인시 JWT token 생성
	 */
	public String makeToken(String member_seq, String type){
		int time = 0;
		
		long currentTime = System.currentTimeMillis()+(1000*60*time); 
		String jwt = "";
		jwt = Jwts.builder().
				setSubject(member_seq).
				setExpiration(new Date(currentTime)).
				claim("member_seq", member_seq).
				signWith(SignatureAlgorithm.HS256, this.generateKey()).
				compact();
		return jwt;
	}
	
	/**
	 * 로그인시 JWT token 생성
	 */
	public String makeToken(String member_seq){
		long currentTime = System.currentTimeMillis()+(1000*60*JWT_EX_TIME); 
		String jwt = "";
		jwt = Jwts.builder().
				setSubject(member_seq).
				setExpiration(new Date(currentTime)).
				claim("member_seq", member_seq).
				signWith(SignatureAlgorithm.HS256, this.generateKey()).
				compact();
		return jwt;
	}
	
	/**
	 * 토큰 유효성 체크
	 */
	public int checkToken(HttpServletRequest request){
		final String TOKEN = request.getHeader(AUTHORIZATION);
		if(Util.chkNull(TOKEN)) return 24;
		
		int rst_code = 0;
		try{
			Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(TOKEN);
		}catch (ExpiredJwtException e1){
			rst_code = 20;
		}catch (UnsupportedJwtException  e1){
			rst_code = 21;
		}catch (MalformedJwtException  e1){
			rst_code = 22;
		}catch (SignatureException  e1){
			rst_code = 23;
		}catch (Exception e1){
			rst_code = -1;
		}
		return rst_code;
	}
	/**
	 * 토큰 유효성 체크
	 */
	public int checkToken(String TOKEN){
		if(Util.chkNull(TOKEN)) return 24;
		
		int rst_code = 0;
		try{
			Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(TOKEN);
		}catch (ExpiredJwtException e1){
			rst_code = 20;
		}catch (UnsupportedJwtException  e1){
			rst_code = 21;
		}catch (MalformedJwtException  e1){
			rst_code = 22;
		}catch (SignatureException  e1){
			rst_code = 23;
		}catch (Exception e1){
			rst_code = -1;
		}
		return rst_code;
	}
	
	/**
	 * 토큰 가져오기
	 */
	public String getToken(HttpServletRequest request){
		return request.getHeader(AUTHORIZATION);
	}
	
	/**
	 * 토큰 내부의 object 가져오기
	 */
	public Jws<Claims> getTokenInfo(HttpServletRequest request){
		String token = getToken(request);
		return Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(token);
	}
	public String getTokenInfo(HttpServletRequest request, String key){
		String token = getToken(request);
		return (String) Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(token).getBody().get(key);
	}
	public String getTokenInfo(String token, String key){
		return (String) Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(token).getBody().get(key);
	}
	
	/**
	 * 토큰 생성 및 확인시 인코딩 sha 256형식으로 json 데이터를 바인딩한다
	 */
	private byte[] generateKey() {
		byte[] key = null;
		try {
			key = SALT.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return key;
	}
	
	//-----------------      JWT 토큰      --------------------------------------------------//
	static Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
	public static String GmakeDynamicValueObject(ResponseEntity<Map<String, Object>> entity){
		String ss = "";
		ss += gson.toJson(entity);
		return ss;
	}
	
	public static void returnPrint(String str){
		
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(str);
		str = gson.toJson(je);
		
		System.out.println("--------------------      Response Param     ---------------------------");
		System.out.println(str);
		System.out.println("------------------------------------------------------------------------");
	}
}
