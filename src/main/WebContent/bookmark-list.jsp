<%@page import="com.openwifi.dto.BookMarkListDTO"%>
<%@page import="com.openwifi.dto.BookMarkDTO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.openwifi.dto.JsonDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OpenWifi</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="${cp}/css/index.css?ver=1.0.0" type="text/css"/>
</head>
<body>
	<h1>북마크 그룹</h1>
	<div id="header">
		<div class="containter">
			<ul>
				<li><a href="index.jsp">홈</a></li>
				<li>|</li>
				<li><a href="historylist.wifi">위치 히스토리 목록</a></li>
				<li>|</li>
				<li><a href="downWifi.wifi">Open API 와이파이 정보 가져오기</a></li>
				<li>|</li>
				<li><a href="bookMarkList.wifi">북마크 보기</a></li>
				<li>|</li>
				<li><a href="bookmark-group.wifi">북마크 그룹 관리</a></li>
			</ul>
		</div>
	</div>
	<Table>
		<thead>
			<tr>
				<th>ID</th>
				<th>북마크 이름</th>
				<th>와이파이명</th>
				<th>등록일자</th>
				<th>비고</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${bookMarkListDTOs != null}">
					<%
						List<BookMarkListDTO> list = (List<BookMarkListDTO>) request.getAttribute("bookMarkListDTOs");
								for (BookMarkListDTO item : list) {
									pageContext.setAttribute("item", item);
					%>
					<tr>
						<td id="1">${item.getGROUP_ID()}</td>
						<td id="2">${item.getGROUP_NAME()}</td>
						<td id="3"><a class="linkBtn" href="javascript:void(0)">${item.getWIFI_NAME()}</a></td>
						<td id="4">${item.getADD_DATE()}</td>
						<td id="5"><input class="delBtn" type="button" value = "삭제"></td>
						<td style="display:none">${item.getROWID()}</td>
						<td style="display:none">${item.getWIFI_ID()}</td>
					</tr>
					<%
						}
					%>

				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="6">데이터가 존재하지 않습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</Table>
	<div class="pageNumList">
	<c:choose>
	 <c:when test="${result != null}">
	 <div>
		<%
			Map<String, Object> result = (Map<String,Object>) request.getAttribute("result");
			List<Integer> pageList = (List<Integer>)result.get("pageList");
		%>
		<%
			if((boolean)result.get("isPrevExist")){%>
			<p><a id="lt" href="searchWifi.wifi?lat=${result.lat}&lnt=${result.lnt}&pageNum=${result.blockFirstPageNum - 1}">&lt;</a></p>
				
		<%		
			}
		%>	
		<%	for(int i = 0 ;  i < pageList.size(); i++){
				pageContext.setAttribute("pageList", pageList.get(i));
		%>			
		
			<p><a href="searchWifi.wifi?lat=${result.lat}&lnt=${result.lnt}&pageNum=${pageList}">${pageList}</a></p>

		<%
			}
		%>
		
		<%
			if((boolean)result.get("isNextExist")){
		%>
			<p><a id="gt" href="searchWifi.wifi?lat=${result.lat}&lnt=${result.lnt}&pageNum=${result.blockLastPageNum + 1}">&gt;</a></p>
		<%
			}
		%>
	 </div>
	 </c:when>
	 <c:otherwise>
	 
	 </c:otherwise>
	</c:choose>
	</div>
</body>
<script type="text/javascript">
	function delBookMarkList (){
		var delBtn = $(this);
		var tr = delBtn.parent().parent();
		var td = tr.children();
		var rowId = td.eq(5).text();
		var wifiId = td.eq(6).text();
		location.href="delBookMarkList.wifi?rowId="+rowId+"&wifiId="+wifiId;
	};
	
	$('.linkBtn').click(function detailInfo (){
		var linkBtn = $(this);
		var tr = linkBtn.parent().parent();
		var td = tr.children();
		var wifiId = td.eq(6).text();
		location.href="detail.wifi?wifiId="+wifiId;
	})
	

	$('.delBtn').on('click', delBookMarkList);
</script>
</html>