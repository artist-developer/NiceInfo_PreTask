package com.artistdeveloper.api.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.artistdeveloper.api.domain.Meta;
import com.artistdeveloper.api.domain.bldRgstEntity;
import com.artistdeveloper.api.service.apiService;
import com.artistdeveloper.api.util.Common;
import com.artistdeveloper.api.util.Util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

@Controller
public class ApiController {
	
	@Inject private apiService service;
	
	@RequestMapping(value = "/apiList", method= {RequestMethod.GET})
	@ResponseBody
	public ResponseEntity<Map<String, Object>> apiList(HttpServletRequest request, Model model, HttpSession session){
		
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		Meta meta = new Meta();
		
//,@RequestHeader String authToken
		HashMap<String, Object> rst = new HashMap<String, Object>();
		
		String authToken = "test";
		
		if(null == authToken || "".equals(authToken)) {
			
			meta.setResult_code(500);
			meta.setResult_msg("로그인정보가 없습니다.");
			map.put("result", meta);
			map.put("data", null);
			
		}else {
			
			List<Object> vo = null;
			vo = service.apiList();
			if (vo.size() > 0) {
				rst.put("list", vo);
				meta.setResult_code(200);
				meta.setResult_msg("리스트를 가져왔습니다.");
			} else {
				meta.setResult_code(500);
				meta.setResult_msg("리스트가 없습니다.");
			}
			
			map.put("result", meta);
			map.put("data", vo);
			
		    
			
		}
		
		try {entity = new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);} 
	    catch (Exception e) {entity = new ResponseEntity<Map<String, Object>>(map,HttpStatus.BAD_REQUEST);}
	    Common.returnPrint(Common.GmakeDynamicValueObject(entity));
		
		
		return entity;
	}
	
	JsonObject test = new JsonObject();
	
    public static void test2() throws IOException {
      StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1611000/BldRgstService/getBrBasisOulnInfo"); /*URL*/
      urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=wosQ8ctifx4S%2Fmc%2BIiBoNgtlWbVqfrvxJ2Eyv9X2DpbHwpyuwkrfQooWRjdhTN15sIs4oL4V%2FpMh8M%2FlGh5Fkw%3D%3D"); /*Service Key*/
      urlBuilder.append("&" + URLEncoder.encode("bjdongCd","UTF-8") + "=" + URLEncoder.encode("11800", "UTF-8")); /*행정표준코드*/
      urlBuilder.append("&" + URLEncoder.encode("platGbCd","UTF-8") + "=" + URLEncoder.encode("0", "UTF-8")); /*0:대지 1:산 2:블록*/
//      urlBuilder.append("&" + URLEncoder.encode("bun","UTF-8") + "=" + URLEncoder.encode("0012", "UTF-8")); /*번*/
//      urlBuilder.append("&" + URLEncoder.encode("ji","UTF-8") + "=" + URLEncoder.encode("0000", "UTF-8")); /*지*/
      urlBuilder.append("&" + URLEncoder.encode("startDate","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*YYYYMMDD*/
      urlBuilder.append("&" + URLEncoder.encode("endDate","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*YYYYMMDD*/
      urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("6000", "UTF-8")); /*페이지당 목록 수*/
      urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
      urlBuilder.append("&" + URLEncoder.encode("sigunguCd","UTF-8") + "=" + URLEncoder.encode("11560", "UTF-8")); /*행정표준코드*/
      urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*행정표준코드*/
      URL url = new URL(urlBuilder.toString());
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Content-type", "application/json;charset=UTF-8");
      System.out.println("Response code: " + conn.getResponseCode());
      BufferedReader rd;
      if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
          rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
      } else {
          rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
      }
      StringBuilder sb = new StringBuilder();
      String line;
      while ((line = rd.readLine()) != null) {
          sb.append(line);
      }
      rd.close();
      conn.disconnect();
      
      bldRgstEntity entity = null;
      List<bldRgstEntity> list = null;
      JSONParser jsonParse = new JSONParser(); 
      
      //JSONParse에 json데이터를 넣어 파싱한 다음 JSONObject로 변환한다. 
      try {
			JSONObject json = (JSONObject) jsonParse.parse(sb.toString());
			JSONObject body = (JSONObject) json.get("response");
			JSONObject items = (JSONObject) body.get("body");
			JSONObject item = (JSONObject) items.get("items");
			
			JSONArray personArray = (JSONArray) item.get("item"); 
			Gson gson = new Gson();
			
			
			//Json 값을 Entity 객체로 변환
			
			// 방법1 
			//bldRgstEntity[] array = gson.fromJson(personArray.toJSONString(), bldRgstEntity[].class); 
			//List<Member> list = Arrays.asList(array); 
			// 방법2 
			list = gson.fromJson(personArray.toJSONString(), new TypeToken<List<bldRgstEntity>>(){}.getType());
			
			/*
			 * for (int i = 0; i < personArray.size(); i++) { entity =
			 * gson.fromJson(personArray.get(i).toString(), bldRgstEntity.class);
			 * list.add(entity); }
			 */
			
			System.out.println("toJSONString : "+personArray.toJSONString());
			//System.out.println("toString : "+personArray.toString());
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      //System.out.println(list.toString());
    }
	
	
	
}
