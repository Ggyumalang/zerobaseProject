package com.api;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.model.WifiInfoDTO;

import java.io.BufferedReader;
import java.io.IOException;

public class GetOpenAPI {
	public WifiInfoDTO getWifiInfo() {

		HttpURLConnection conn = null;
		BufferedReader rd = null;
		StringBuilder sb = new StringBuilder();
		
		try {
			StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
			urlBuilder.append("/" +  URLEncoder.encode("6143456b656b686738377a79427468","UTF-8") ); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
			urlBuilder.append("/" +  URLEncoder.encode("json","UTF-8") ); /*요청파일타입 (xml,xmlf,xls,json) */
			urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
			urlBuilder.append("/" + URLEncoder.encode("1","UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
			urlBuilder.append("/" + URLEncoder.encode("900","UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
			// 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.
			
			URL url = new URL(urlBuilder.toString());
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/xml");
			System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
	
			// 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
			if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
					rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
					rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			
			String line = "";
			while ((line = rd.readLine()) != null) {
					sb.append(line);
			}
			
		}catch(IOException ie) {
			ie.printStackTrace();
		}finally {
			try {
				if(rd!=null) {
					rd.close();
				}
				
				if(conn!=null) {
					conn.disconnect();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		sb.trimToSize();
		System.out.println(sb.toString());
		Gson gson = new Gson();
		WifiInfoDTO infoDTO = null;
		try {
			infoDTO = gson.fromJson(sb.toString(), WifiInfoDTO.class);
			System.out.println("Open API 와이파이 정보 가져오기 성공!");
		} catch (JsonParseException e) {
			System.out.println("Open API 와이파이 정보 가져오기 실패....");
			e.printStackTrace();
		}
		return infoDTO;
	}

}
