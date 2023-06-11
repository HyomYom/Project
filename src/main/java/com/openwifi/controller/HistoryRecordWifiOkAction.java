package com.openwifi.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.openwifi.action.Action;
import com.openwifi.action.ActionTo;
import com.openwifi.dao.WifiDAO;

public class HistoryRecordWifiOkAction implements Action {

	@Override
	public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		WifiDAO wifiDAO = new WifiDAO();
		String lat = req.getParameter("lat");
		String lnt = req.getParameter("lnt");
		String date = req.getParameter("date").substring(0,19);
		if(wifiDAO.hisRecord(lat,lnt,date)) {
			resp.getWriter().write("{\"result\" : \"O\"}");
		} else {
			resp.getWriter().write("{\"result\" : \"X\"}");
		}
		return null;
	}

}
