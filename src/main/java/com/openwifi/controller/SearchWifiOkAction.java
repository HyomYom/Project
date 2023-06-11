package com.openwifi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.openwifi.action.Action;
import com.openwifi.action.ActionTo;
import com.openwifi.dao.WifiDAO;
import com.openwifi.dto.JsonDTO;

public class SearchWifiOkAction implements Action {
	

	private int setTotalLastPageNum(long totalPostCount, int postsPerPage) {
		int totalLastPageNum = 0 ;
		if(totalPostCount == 0) {
			totalLastPageNum = 1;
		} else {
			totalLastPageNum = (int)Math.ceil((double)totalPostCount / postsPerPage);
		}
		
		return totalLastPageNum;
	}
	@Override
	public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ActionTo transfer = null;
		WifiDAO wifiDAO = new WifiDAO();
		
		int pagesPerBlock = 10;
		int postsPerPage = 20;
		long totalPostCount = wifiDAO.wifiCnt();
		int totalLastPageNum = setTotalLastPageNum(totalPostCount, postsPerPage);;
		
		int blockLastPageNum = totalLastPageNum;
		int blockFirstPageNum = 1;
		
		int mod = totalLastPageNum%pagesPerBlock;
		int currentPageNum = req.getParameter("pageNum") == null? 
				1 : Integer.parseInt(req.getParameter("pageNum"));
		
		int postStartNum = currentPageNum==1? 1 : 1 + (currentPageNum - 1)*postsPerPage;
				
		System.out.println(currentPageNum);
		if(totalLastPageNum - mod >= currentPageNum) {
			blockLastPageNum = (int) (Math.ceil((double)currentPageNum /pagesPerBlock)* pagesPerBlock);
			blockFirstPageNum = blockLastPageNum - (pagesPerBlock - 1);
			// 마지막 페이지가 0으로 끝나는 구간은 무조건 이 조건 안으로 들어온다.
		} else {
			blockFirstPageNum = (int) (int) (Math.ceil((double)currentPageNum /pagesPerBlock)* pagesPerBlock)
					- (pagesPerBlock -1);
		}
		
		// 페이지 번호 할당
		List<Integer> pageList = new ArrayList<Integer>();
		for(int i = 0, val = blockFirstPageNum; val <= blockLastPageNum; i++, val++) {
			pageList.add(i, val);
		}
		
		String x = req.getParameter("lat");
		System.out.println(x);
		String y = req.getParameter("lnt");
		System.out.println(y);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("isPrevExist", currentPageNum > pagesPerBlock);
		result.put("isNextExist", blockLastPageNum != 1 ? blockLastPageNum != totalLastPageNum : false);
		result.put("totalLastPageNum", totalLastPageNum);
		result.put("blockFirstPageNum",blockFirstPageNum);
		result.put("blockLastPageNum", blockLastPageNum);
		result.put("currentPageNum", currentPageNum);
		result.put("totalPostCount", totalPostCount);
		result.put("pagesPerBlock", pagesPerBlock);
		result.put("postsPerPage", postsPerPage);
		result.put("pageList", pageList);
		result.put("lat", x);
		result.put("lnt", y);
		
		
		List<JsonDTO> list = new ArrayList<JsonDTO>();
		
		
		
		list = wifiDAO.getWifiList(x,y,postStartNum, postsPerPage);
		result.put("list", list);
		if(list!=null) {
			transfer = new ActionTo();
		req.setAttribute("list", list);
		req.setAttribute("result", result);
		transfer.setPath("/");
		transfer.setRedirect(false);
		
		}
		return transfer;
	}
		
}
