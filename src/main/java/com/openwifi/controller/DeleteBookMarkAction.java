package com.openwifi.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.openwifi.action.Action;
import com.openwifi.action.ActionTo;
import com.openwifi.dao.WifiDAO;

public class DeleteBookMarkAction implements Action {
	@Override
	public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String groupName = req.getParameter("groupName");
		int order = Integer.parseInt(req.getParameter("order"));
		System.out.println(groupName);
		System.out.println(order);
		WifiDAO wifiDAO = new WifiDAO();
		if(wifiDAO.deleteBookMark(groupName, order)) {
			PrintWriter out = resp.getWriter();
			out.write("<script>");
			out.write("alert(\"삭제되었습니다.\")");
			out.write("</script>");
			out.write("<script>");
			out.write("location.href=\"/bookmark-group.wifi\"");
			out.write("</script>");
		}
		return null;
	}
}
