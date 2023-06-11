package com.openwifi.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.openwifi.action.Action;
import com.openwifi.action.ActionTo;
import com.openwifi.dao.WifiDAO;
import com.openwifi.dto.HistoryDTO;

public class DeleteHistoryOkAction implements Action{
	@Override
	public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String rowid = req.getParameter("rowid");
		WifiDAO wifiDAO = new WifiDAO();
		if(wifiDAO.deleteHistory(rowid)) {
			PrintWriter out = resp.getWriter();
			
			out.write("<script>");
			out.write("alert(\"데이터가 삭제되었습니다\")");
			out.write("</script>");
			out.write("<script>");
			out.write("location.href=\"historylist.wifi\"");
			out.write("</script>");
		}
		return null;
	}
}
