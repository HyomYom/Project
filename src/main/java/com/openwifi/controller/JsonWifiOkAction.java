package com.openwifi.controller;

import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.openwifi.action.Action;
import com.openwifi.action.ActionTo;
import com.openwifi.dao.WifiDAO;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class JsonWifiOkAction implements Action {

	@Override
	public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		ActionTo transfer = null;
		StringBuilder urlBuilder = new StringBuilder();
		OkHttpClient client = new OkHttpClient();
		
		
		StringBuilder sb = new StringBuilder();
		ArrayList<JsonObject> arrList = new ArrayList<JsonObject>();
		Gson gson = new Gson();
		JsonObject jsonObject;

		int cnt = 0;
		while (cnt != 23304) {
			urlBuilder.append("http://openapi.seoul.go.kr:8088"); /* URL */
			urlBuilder.append("/"
					+ URLEncoder.encode("4b446e5a7770616737315765594c6e", "UTF-8")); /* 인증키 (sample사용시에는 호출시 제한됩니다.) */
			urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); /* 요청파일타입 (xml,xmlf,xls,json) */
			urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8")); /* 서비스명 (대소문자 구분 필수입니다.) */

			if (cnt == 23000) {
				urlBuilder.append(
						"/" + URLEncoder.encode(String.valueOf(cnt), "UTF-8")); /* 요청시작위치 (sample인증키 사용시 5이내 숫자) */
				cnt += 304;
				urlBuilder.append("/"
						+ URLEncoder.encode(String.valueOf(cnt), "UTF-8")); /* 요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨) */
			} else {
				urlBuilder.append(
						"/" + URLEncoder.encode(String.valueOf(cnt), "UTF-8")); /* 요청시작위치 (sample인증키 사용시 5이내 숫자) */
				cnt += 999;
				urlBuilder.append("/"
						+ URLEncoder.encode(String.valueOf(cnt), "UTF-8")); /* 요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨) */
				cnt += 1;
			}
		

			Request.Builder builder = new Request.Builder().url(urlBuilder.toString()).get();
			builder.addHeader("Content-type", "application/json");
			Request request = builder.build();
			Response response = client.newCall(request).execute();
			
			if(response.isSuccessful()) {
				ResponseBody body = response.body();
				sb.append(body.string());
			}
			

			jsonObject = gson.fromJson(sb.toString(), JsonObject.class);
			jsonObject = (JsonObject) jsonObject.get("TbPublicWifiInfo");
			arrList.add(jsonObject);
			urlBuilder = new StringBuilder();
			sb = new StringBuilder();
		}
		
		System.out.println(arrList.size());
		WifiDAO wifiDAO = new WifiDAO();
		
		if(wifiDAO.wifiInsert(arrList)) {
			int total = wifiDAO.wifiCnt();
			transfer = new ActionTo();
			req.setAttribute("total",total);
			transfer.setPath("/finishied.jsp");
			transfer.setRedirect(true);
		}
		System.out.println("완료");
		

		return transfer;
	}

}
