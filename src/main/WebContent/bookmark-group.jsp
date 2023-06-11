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
<link rel="stylesheet" href="${cp}/css/bookGroup.css?ver=1.0.0" type="text/css"/>
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
		<div class="button">
			<input class ="addBtn" type="button" value="북마크 그룹 이름 추가">
		</div>
	</div>
	<Table>
		<thead>
			<tr>
				<th>ID</th>
				<th>북마크 이름</th>
				<th>순서</th>
				<th>등록일자</th>
				<th>수정일자</th>
				<th>비고</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${bookMarkDTOs != null}">
					<%
						List<BookMarkDTO> list = (List<BookMarkDTO>) request.getAttribute("bookMarkDTOs");
								for (BookMarkDTO item : list) {
									pageContext.setAttribute("item", item);
					%>
					<tr>
						<td id="1">${item.getROWID()}</td>
						<td id="2">${item.getGROUP_NAME()}</td>
						<td id="3">${item.getORDER()}</td>
						<td id="4">${item.getADD_DATE()}</td>
						<td id="5">${item.getMODI_DATE()}</td>
						<td id="6"><a class="modiBtn"  href="javascript:void(0)">수정</a> <a class="delBtn" href="javascript:void(0)">삭제</a></td>
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
	$(".addBtn").click(function(){
		location.href="bookmark-group-add.jsp"
	})

	$(".modiBtn").click(function(){
		var modiBtn = $(this);
		var tr = modiBtn.parent().parent();
		var td = tr.children();
		var groupName = td.eq(1).text();
		var order = td.eq(2).text();
		console.log(groupName);
		console.log(order);
		location.href="modifyBookMark.wifi?groupName="+groupName+"&order="+order;
	})
	
	$(".delBtn").click(function(){
		var delBtn = $(this);
		var tr = delBtn.parent().parent();
		var td = tr.children();
		var groupName = td.eq(1).text();
		var order = td.eq(2).text();
		location.href="deleteBookMark.wifi?groupName="+groupName+"&order="+order;
	})
	
</script>
</html>