package com.openwifi.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.openwifi.action.Action;
import com.openwifi.action.ActionTo;
import com.openwifi.dao.WifiDAO;

public class AddBookMarOkAction implements Action{
	@Override
	public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String groupName = req.getParameter("groupName");
		int order = Integer.parseInt(req.getParameter("order"));
		System.out.println(groupName);
		System.out.println(order);
		WifiDAO wifiDAO = new WifiDAO();
		PrintWriter out = resp.getWriter();
		if(wifiDAO.addBookMark(groupName,order)) {
			out.write("<script>");
			out.write("alert(\"그룹이 추가되었습니다.\")");
			out.write("</script>");
			out.write("<script>");
			out.write("location.href=\"/bookmark-group.wifi\"");
			out.write("</script>");
			
		}
		
		return null;
	}
}
