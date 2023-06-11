package com.openwifi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.openwifi.action.Action;
import com.openwifi.action.ActionTo;
import com.openwifi.dao.WifiDAO;
import com.openwifi.dto.HistoryDTO;

public class HistoryListOkAction implements Action {

	@Override
	public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ActionTo transfer = null;
		List<HistoryDTO> historyDTOs = null;

		WifiDAO wifiDAO = new WifiDAO();
		if (wifiDAO.getHistoryList() != null) {
			transfer = new ActionTo();
			historyDTOs = wifiDAO.getHistoryList();
			if (historyDTOs.size() != 0) {
				System.out.println("위");
				req.setAttribute("historyDTOs", historyDTOs);
			} else {
				historyDTOs = null;
				req.setAttribute("historyDTOs", historyDTOs);
				System.out.println("아래");
			}
		}
		transfer.setPath("/history.jsp");
		transfer.setRedirect(false);
		return transfer;
	}

}
