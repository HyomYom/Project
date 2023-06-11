package com.openwifi.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.openwifi.action.Action;
import com.openwifi.action.ActionTo;
import com.openwifi.dao.WifiDAO;

public class ModifyBookMarkAction implements Action{
	@Override
	public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ActionTo transfer = null;
		WifiDAO wifiDAO = new WifiDAO();
		String groupName = req.getParameter("groupName");
		int order = Integer.parseInt(req.getParameter("order"));
		if(req.getParameter("modiName")==null && req.getParameter("modiOrder") == null) {
			transfer = new ActionTo();
			req.setAttribute("groupName", groupName);
			req.setAttribute("order", order);
			transfer.setPath("/bookmark-group-modi.jsp");
			transfer.setRedirect(false);
			
		} else {
			System.out.println("hi!");
			PrintWriter out = resp.getWriter();
			String modiName = req.getParameter("modiName");
			int modiOrder = Integer.parseInt(req.getParameter("modiOrder"));
			if(wifiDAO.modifyBookMark(groupName,order,modiName,modiOrder)) {
				out.write("<script>");
				out.write("alert(\"수정되었습니다.\")");
				out.write("</script>");
				out.write("<script>");
				out.write("location.href=\"/bookmark-group.wifi\"");
				out.write("</script>");
			}
			
		}
		
		return transfer;
	}
}
