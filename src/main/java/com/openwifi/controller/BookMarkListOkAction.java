package com.openwifi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.openwifi.action.Action;
import com.openwifi.action.ActionTo;
import com.openwifi.dao.WifiDAO;
import com.openwifi.dto.BookMarkListDTO;

public class BookMarkListOkAction implements Action {
	@Override
	public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ActionTo transfer = null;
		WifiDAO wifiDAO = new WifiDAO();
		System.out.println("hello");
		List<BookMarkListDTO> bookMarkListDTOs = wifiDAO.getBookMarkList();
		if(bookMarkListDTOs != null) {
			transfer = new ActionTo();
			if (bookMarkListDTOs.size() != 0) {
				System.out.println("위");
				req.setAttribute("bookMarkListDTOs", bookMarkListDTOs);
			} else {
				bookMarkListDTOs = null;
				req.setAttribute("bookMarkListDTOs", bookMarkListDTOs);
				System.out.println("아래");
			}
			transfer.setPath("/bookmark-list.jsp");
			transfer.setRedirect(false);
		}
		
		return transfer;
	}

}
