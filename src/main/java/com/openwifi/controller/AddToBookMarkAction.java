package com.openwifi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.openwifi.action.Action;
import com.openwifi.action.ActionTo;
import com.openwifi.dao.WifiDAO;

public class AddToBookMarkAction implements Action {
	@Override
	public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ActionTo transfer = null;
		String groupName = req.getParameter("groupName");
		String wifiId = req.getParameter("wifiId");
		String wifiName =req.getParameter("wifiName");
		WifiDAO wifiDAO = new WifiDAO();
		if(wifiDAO.addToBookMark(wifiId, groupName, wifiName)) {
			resp.getWriter().write("<script>");
			resp.getWriter().write("location.href = \"bookMarkList.wifi\"");
			resp.getWriter().write("</script>");
		}
		
		return transfer;
	}
}
