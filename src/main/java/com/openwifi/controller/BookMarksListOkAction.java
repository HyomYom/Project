package com.openwifi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.openwifi.action.Action;
import com.openwifi.action.ActionTo;
import com.openwifi.dao.WifiDAO;
import com.openwifi.dto.BookMarkDTO;

public class BookMarksListOkAction implements Action{
	@Override
	public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ActionTo transfer = null;
		WifiDAO wifiDAO = new WifiDAO();
		List<BookMarkDTO> bookMarkDTOs = null;
		if (wifiDAO.getBooksMarkList() != null) {
			transfer = new ActionTo();
			System.out.println("hi");
			bookMarkDTOs = wifiDAO.getBooksMarkList();
			if (bookMarkDTOs.size() != 0) {
				System.out.println("위");
				req.setAttribute("bookMarkDTOs", bookMarkDTOs);
			} else {
				bookMarkDTOs = null;
				req.setAttribute("bookMarkDTOs", bookMarkDTOs);
				System.out.println("아래");
			}
			transfer.setPath("/bookmark-group.jsp");
		}
		transfer.setRedirect(false);
		
		return transfer;
				
	}
}
