package com.openwifi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.openwifi.action.Action;
import com.openwifi.action.ActionTo;
import com.openwifi.dao.WifiDAO;
import com.openwifi.dto.BookMarkDTO;
import com.openwifi.dto.JsonDTO;

public class DetailWifiOkAction implements Action {

	@Override
	public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		ActionTo transfer = null;
		String x = "0";
		x = req.getParameter("lat");
		String y = "0";
		y = req.getParameter("lnt");
		String wifiId = req.getParameter("wifiId");

		WifiDAO wifiDAO = new WifiDAO();
		if (wifiDAO.detail(wifiId, x, y) != null) {
			transfer = new ActionTo();
			JsonDTO result = wifiDAO.detail(wifiId, x, y);
			List<BookMarkDTO> bookMarkDTOs = wifiDAO.getBooksMarkList();
			req.setAttribute("result", result);
			req.setAttribute("bookMarkDTOs", bookMarkDTOs);
			transfer.setPath("/detail.jsp");
			transfer.setRedirect(false);
		}
		return transfer;
	}

}
