package com.openwifi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.openwifi.action.Action;
import com.openwifi.action.ActionTo;
import com.openwifi.dao.WifiDAO;

public class CheckToBookMarkAction implements Action {
	@Override
	public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		WifiDAO wifiDAO = new WifiDAO();
		String wifiId = req.getParameter("wifiId");
		String groupName = req.getParameter("groupName");
		if(wifiDAO.checkBookMarkList(wifiId,groupName) == 0) {
			resp.getWriter().write("{\"result\" : \"O\"}");
		} else {
			resp.getWriter().write("{\"result\" : \"X\"}");
		}	
		
		return null;
	}
}
