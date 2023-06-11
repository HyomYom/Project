package com.openwifi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.openwifi.action.Action;
import com.openwifi.action.ActionTo;
import com.openwifi.dao.WifiDAO;

public class CheckBookMarkOkAction implements Action {
	@Override
	public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		WifiDAO wifiDAO = new WifiDAO();
		String groupName = req.getParameter("groupName");
		int order = Integer.parseInt(req.getParameter("order"));
		if (req.getParameter("postName") != null && req.getParameter("postOrder") != null) {
			String postName = req.getParameter("postName");
			int postOrder = Integer.parseInt(req.getParameter("postOrder"));
			if ((groupName.equals(postName) && order != postOrder)
					|| (!groupName.equals(postName) && order == postOrder)) {

				if (wifiDAO.checkBookMark(groupName, order) <= 1) {
					resp.getWriter().write("{\"result\" : \"O\"}");
				} else {
					resp.getWriter().write("{\"result\" : \"X\"}");
				}
				
			} else {
				if (wifiDAO.checkBookMark(groupName, order) < 1) {
					resp.getWriter().write("{\"result\" : \"O\"}");
				} else {
					resp.getWriter().write("{\"result\" : \"X\"}");
				}
			}
		} else {
			if (wifiDAO.checkBookMark(groupName, order) < 1) {
				resp.getWriter().write("{\"result\" : \"O\"}");
			} else {
				resp.getWriter().write("{\"result\" : \"X\"}");
			}
		}
		return null;
	}
}
