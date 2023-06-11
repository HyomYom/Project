<%@page import="com.openwifi.dto.HistoryDTO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.openwifi.dto.JsonDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OpenWifi</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="${cp}/css/history.css?ver=1.0.0"
	type="text/css" />
</head>
<body>
	<h1>북마크 그룹 수정</h1>
	<div id="header">
		<div class="containter">
			<ul>
				<li><a href="index.jsp">홈</a></li>
				<li>|</li>
				<li><a href="historylist.wifi">위치 히스토리 목록</a></li>
				<li>|</li>
				<li><a href="downWifi.wifi">Open API 와이파이 정보 가져오기</a></li>
				<li>|</li>
				<li><a href="">북마크 보기</a></li>
				<li>|</li>
				<li><a href="bookmark-group.wifi">북마크 그룹 관리</a></li>
			</ul>
		</div>

	</div>
	<Table>
		<tbody>
			<tr>
				<td>북마크 이름</td>
				<td><input id="modiName" type="text" value=""></td>
			</tr>
			<tr>
				<td>순서</td>
				<td><input id="modiOrder" type="text" value=""></td>
			</tr>
			<tr>
				<td colspan="2"><a href="bookmark-group.wifi">돌아가기</a> | <input
					class="modiBtn" type="button" value="수정"></td>
			</tr>
		</tbody>
	</Table>
</body>
<script type="text/javascript">
	$(".modiBtn")
			.click(
					function() {
						var modiName = $("#modiName").val();
						var modiOrder = $("#modiOrder").val();
						if (modiName.length == 0 || modiOrder.length == 0) {
							alert("변경하실 그룹이름 또는 순서를 입력해주세요.");
						} else {

							$
									.ajax({
										url : "checkBookMark.wifi",
										data : {
											groupName : modiName,
											order : modiOrder,
											postName : '${groupName}',
											postOrder : '${order}'
										},
										success : function(res) {
											var parsed = JSON.parse(res);
											if (parsed.result == "O") {
												location.href = "modifyBookMark.wifi?groupName=${groupName}&order=${order}&modiName="
														+ modiName
														+ "&modiOrder="
														+ modiOrder;
											} else {
												alert("그룹이름 또는 순서가 이미 존재합니다.\n다른 그룹이름 또는 순서를 입력해주세요.")
												$("#groupName").val("");
												$("#order").val("");

											}
										},
										error : function(XMLHttpRequest,
												textStatus, errorThrown) {
										},

										timeout : 5000,

									})
						}
					})
</script>
</html>