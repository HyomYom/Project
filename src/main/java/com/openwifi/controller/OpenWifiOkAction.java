//package com.openwifi.controller;
//
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//import com.openwifi.action.Action;
//import com.openwifi.action.ActionTo;
//import com.openwifi.dao.WifiDAO;
//import com.openwifi.dto.WifiDTO;
//
//
//public class OpenWifiOkAction implements Action{
//	
//	
//	@Override
//	public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//		
//		ActionTo transfer = null;
//		System.out.println("OpenWifiOkAction 단");
//		
//		StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
//		urlBuilder.append("/" +  URLEncoder.encode("4b446e5a7770616737315765594c6e","UTF-8") ); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
//		urlBuilder.append("/" +  URLEncoder.encode("json","UTF-8") ); /*요청파일타입 (xml,xmlf,xls,json) */
//		urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
//		urlBuilder.append("/" + URLEncoder.encode("1","UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
//		urlBuilder.append("/" + URLEncoder.encode("1000","UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
//		// 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.
//		
//		// 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에 자세히 나와 있습니다.
////		urlBuilder.append("/" + URLEncoder.encode("20220301","UTF-8")); /* 서비스별 추가 요청인자들*/
//		
//		URL url = new URL(urlBuilder.toString());
//		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		conn.setRequestMethod("GET");
//		conn.setRequestProperty("Content-type", "application/json");
//		System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
//		BufferedReader br;
//
//		// 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
//		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//				br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//		} else {
//				br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//		}
//		StringBuilder sb = new StringBuilder();
//		JSONObject result = null;
//		String line;
//		while ((line = br.readLine()) != null) {
//				sb.append(line);
//		}
//		try {
//			result = (JSONObject) new JSONParser().parse(sb.toString());
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		
//		JSONObject data = (JSONObject) result.get("TbPublicWifiInfo");
//		br.close();
//		conn.disconnect();
//		List<WifiDTO> list = new ArrayList<WifiDTO>();
//		JSONArray arr = (JSONArray) data.get("row");
//		System.out.println(arr.size());
//		for(int i = 0 ;  i < arr.size(); i++) {
//			JSONObject tmp = (JSONObject) arr.get(i);
//			String wifiId = ((String) tmp.get("X_SWIFI_MGR_NO")).substring(3);
//			String district = (String)	tmp.get("X_SWIFI_WRDOFC");
//			String wifiName = (String) tmp.get("X_SWIFI_MAIN_NM");
//			String addr1 = (String) tmp.get("X_SWIFI_ADRES1");
//			String addr2 = (String)	tmp.get("X_SWIFI_ADRES2");
//			String instFlo = (String) tmp.get("X_SWIFI_INSTL_FLOOR");
//			String instType = (String) tmp.get("X_SWIFI_INSTL_TY");
//			String instAuthority = (String) tmp.get("X_SWIFI_INSTL_MBY");
//			String serviceDivision = (String) tmp.get("X_SWIFI_SVC_SE");
//			String serviceVariety = (String) tmp.get("X_SWIFI_CMCWR");
//			int instYear  = Integer.parseInt((String) tmp.get("X_SWIFI_CNSTC_YEAR"));
//			String instLoc = (String) tmp.get("X_SWIFI_INOUT_DOOR");
//			String wifiEnvironment = (String) tmp.get("X_SWIFI_REMARS3");
//			String wifiXCod = (String) tmp.get("LAT");
//			String wifiYCod = (String) tmp.get("LNT");
//			String instDate = (String) tmp.get("WORK_DTTM");
//			
//			WifiDTO wifiDto = new WifiDTO(wifiId,district, wifiName,addr1, addr2, instFlo,instType, instAuthority,
//					serviceDivision, serviceVariety, instYear, instLoc, wifiEnvironment, wifiXCod, wifiYCod, instDate);
//			
//			list.add(wifiDto);
//		}
//		WifiDAO wifiDAO = new WifiDAO();
//		wifiDAO.wifiInsert(list);
//		System.out.println(list.size());
//		
//		return transfer;
//	}
//	
//}
