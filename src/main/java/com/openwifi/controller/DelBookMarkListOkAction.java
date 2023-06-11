package com.openwifi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.openwifi.action.Action;
import com.openwifi.action.ActionTo;
import com.openwifi.dao.WifiDAO;

public class DelBookMarkListOkAction implements Action {
	@Override
	public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ActionTo transfer = null;
		int rowId = Integer.parseInt(req.getParameter("rowId"));
		String wifiId = req.getParameter("wifiId");
		WifiDAO wifiDAO = new WifiDAO();
		System.out.println("여기까지");
		
		if(wifiDAO.deleteBookMarkList(rowId,wifiId)) {
			System.out.println("삭제 성공");
			resp.getWriter().write("<script>");
			resp.getWriter().write("alert(\"데이터가 삭제 되었습니다.\")");
			resp.getWriter().write("</script>");
			resp.getWriter().write("<script>");
			resp.getWriter().write("location.href=\"bookMarkList.wifi\"");
			resp.getWriter().write("</script>");
		} else {
			resp.getWriter().write("<script>");
			resp.getWriter().write("alert(\"데이터가 삭제 되지 않았습니다.\")");
			resp.getWriter().write("location.href=\"/bookMarkList.wifi\"");
			resp.getWriter().write("</script>");
		}
		
		return null;
	}
}
