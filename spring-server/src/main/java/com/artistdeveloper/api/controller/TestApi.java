package com.artistdeveloper.api.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.artistdeveloper.api.controller.ApiController;
import com.artistdeveloper.api.domain.bldRgstEntity;
import com.artistdeveloper.api.service.apiService;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


public class TestApi {

	public static void main(String[] args) throws IOException {
		
		
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1611000/BldRgstService/getBrBasisOulnInfo"); /*URL*/
	      urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=wosQ8ctifx4S%2Fmc%2BIiBoNgtlWbVqfrvxJ2Eyv9X2DpbHwpyuwkrfQooWRjdhTN15sIs4oL4V%2FpMh8M%2FlGh5Fkw%3D%3D"); /*Service Key*/
	      urlBuilder.append("&" + URLEncoder.encode("bjdongCd","UTF-8") + "=" + URLEncoder.encode("11800", "UTF-8")); /*행정표준코드*/
	      urlBuilder.append("&" + URLEncoder.encode("platGbCd","UTF-8") + "=" + URLEncoder.encode("0", "UTF-8")); /*0:대지 1:산 2:블록*/
//	      urlBuilder.append("&" + URLEncoder.encode("bun","UTF-8") + "=" + URLEncoder.encode("0012", "UTF-8")); /*번*/
//	      urlBuilder.append("&" + URLEncoder.encode("ji","UTF-8") + "=" + URLEncoder.encode("0000", "UTF-8")); /*지*/
	      urlBuilder.append("&" + URLEncoder.encode("startDate","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*YYYYMMDD*/
	      urlBuilder.append("&" + URLEncoder.encode("endDate","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*YYYYMMDD*/
	      urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("200", "UTF-8")); /*페이지당 목록 수*/
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
	      ArrayList<bldRgstEntity> list = new ArrayList<bldRgstEntity>();
	      JSONParser jsonParse = new JSONParser(); 
	      
	      //JSONParse에 json데이터를 넣어 파싱한 다음 JSONObject로 변환한다. 
	      try {
				JSONObject json = (JSONObject) jsonParse.parse(sb.toString());
				JSONObject body = (JSONObject) json.get("response");
				JSONObject items = (JSONObject) body.get("body");
				JSONObject item = (JSONObject) items.get("items");
				
				JSONArray personArray = (JSONArray) item.get("item"); 
				Gson gson = new Gson();
				
				System.out.println("personArray.toJSONString() : "+personArray.toJSONString());
				System.out.println("personArray.toString() : "+personArray.toString());
				
				// 방법1 
				//bldRgstEntity[] array = gson.fromJson(personArray.toJSONString(), bldRgstEntity[].class); 
				//List<Member> list = Arrays.asList(array); 
				// 방법2 
				//List<Member> list2 = gson.fromJson(jsonString, new TypeToken<List<Member>>(){}.getType());

				
				
				
			    for (int i = 0; i < personArray.size(); i++) {
			        entity = gson.fromJson(personArray.get(i).toString(), bldRgstEntity.class);
			        list.add(entity);
			    }
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      System.out.println(list.toString());
		
		
	}
	
}
