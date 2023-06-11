package com.openwifi.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.openwifi.action.ActionTo;

@WebServlet("*.wifi")
public class OpenWifiController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=utf-8");
		req.setCharacterEncoding("utf-8");
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=utf-8");
		req.setCharacterEncoding("utf-8");
		doProcess(req, resp);
	}

	private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestURI = req.getRequestURI();
		System.out.println(requestURI);
		String contextPath = req.getContextPath();
		System.out.println(contextPath);
		String command = requestURI.substring(contextPath.length());
		System.out.println(command);
		ActionTo transfer = null;

		switch (command) {
		case "/infoRequest.wifi":
			transfer = new ActionTo();
			transfer.setPath("/");
			transfer.setRedirect(true);
			System.out.println(req.getParameter("name"));
			break;

		case "/downWifi.wifi":
			try {
				transfer = new JsonWifiOkAction().execute(req, resp);

			} catch (Exception e) {
				System.out.println("downWifi 오류 : "+e);
			}
			break;
			
		case "/deletehistory.wifi":
			try {
				transfer = new DeleteHistoryOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("deletehistory 오류 : "+e);
			}
			break;
			
		case "/searchWifi.wifi":
			try {
				transfer = new SearchWifiOkAction().execute(req, resp);
			} catch (Exception e) {
				System.out.println("searchWifi 오류 : "+e);
			}
			break;
		case "/detail.wifi":
			try {
				transfer = new DetailWifiOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("detail 오류 : "+e);
			}
			break;
		case"/historyRecord.wifi":
			try {
				transfer = new HistoryRecordWifiOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("historyRecord 오류 : "+e);
			}
			break;
			
		case "/historylist.wifi":
			try {
				transfer = new HistoryListOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("historylist 오류 : "+e);
			}
			
			break;
			
		case "/addBookMark.wifi":
			try {
				transfer = new AddBookMarOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("addBookMark 오류 : "+e);
			}
			break;
			
		case "/bookmark-group.wifi":
			try {
				transfer = new BookMarksListOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("bookMark-List 오류 : "+e);
			}
			break;
		case "/checkBookMark.wifi":
			try {
				transfer = new CheckBookMarkOkAction().execute(req,resp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/modifyBookMark.wifi":
			try {
				transfer = new ModifyBookMarkAction().execute(req,resp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
		case "/deleteBookMark.wifi":
			 try {
				transfer = new DeleteBookMarkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("deleteBookMark 오류 : "+e);
			}
			break;
			
		case "/checkToBookMark.wifi":
			try {
				transfer = new CheckToBookMarkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("addToBookMark 오류 : "+e);
			}
			break;
			
		case "/addToBookMark.wifi":
			try {
				transfer = new AddToBookMarkAction().execute(req,resp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "/bookMarkList.wifi":
			try {
				transfer = new BookMarkListOkAction().execute(req,resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/delBookMarkList.wifi":
			try {
				transfer = new DelBookMarkListOkAction().execute(req, resp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (transfer != null) {
			if (transfer.isRedirect()) {
				resp.sendRedirect(transfer.getPath());
			} else {
				req.getRequestDispatcher(transfer.getPath()).forward(req, resp);
			}
		}

	}

}
