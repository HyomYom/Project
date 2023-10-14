<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page import="com.openwifi.dto.JsonDTO" %>
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
<link rel="stylesheet" href="${cp}/css/index.css?ver=1.0.0"
	type="text/css" />
</head>
<body>
	<h1>와이파이 정보 구하기</h1>
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
	<div class="searchForm">
		LAT:<input id="LAT" type="text" name="LAT"
			value="<%=request.getParameter("lat") == null ? 0.0 : request.getParameter("lat")%>" />
		, LNT:<input id="LNT" type="text" name="LNT"
			value="<%=request.getParameter("lnt") == null ? 0.0 : request.getParameter("lnt")%>" />
		<button id='find-me'>내 위치 가져오기</button>
		<button id="wifiInfo">근처 WIPI 정보 보기</button>
	</div>
	<Table>
		<colgroup>
			<col />
			<col style="width: 5%"/>
			<col style="width: 3%"/>
			<col style="width: 3%"/>
			<col style="width: 8%"/>
			<col />
			<col />
			<col />
			<col />
			<col />
			<col />
			<col />
			<col />
			<col />
			<col />
			<col />
			<col style="width: 5%"/>
		</colgroup>
		<thead>
			<tr>
				<th>거리(Km)</th>
				<th>관리번호</th>
				<th>자치구</th>
				<th>와이파이명</th>
				<th>도로명주소</th>
				<th>상세주소</th>
				<th>설치위치(층)</th>
				<th>설치유형</th>
				<th>설치기관</th>
				<th>서비스구분</th>
				<th>망종류</th>
				<th>설치년도</th>
				<th>실내외구분</th>
				<th>WIFI접속환경</th>
				<th>X좌표</th>
				<th>Y좌표</th>
				<th>작업일자</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${list != null}">
					<%
						List<JsonDTO> list = (List<JsonDTO>) request.getAttribute("list");
								for (JsonDTO item : list) {
									pageContext.setAttribute("item", item);
					%>
					<tr>
						<td id="1">${item.getDistance()}</td>
						<td id="2">${item.getX_SWIFI_MGR_NO()}</td>
						<td id="3">${item.getX_SWIFI_WRDOFC()}</td>
						<td id="4"><a
							href="detail.wifi?wifiId=${item.getX_SWIFI_MGR_NO()}&lat=${param.lat}&lnt=${param.lnt}">${item.getX_SWIFI_MAIN_NM()}</a></td>
						<td id="5">${item.getX_SWIFI_ADRES1()}</td>
						<td id="6">${item.getX_SWIFI_ADRES2()}</td>
						<td id="7">${item.getX_SWIFI_INSTL_FLOOR()}</td>
						<td id="8">${item.getX_SWIFI_INSTL_TY()}</td>
						<td id="9">${item.getX_SWIFI_INSTL_MBY()}</td>
						<td id="10">${item.getX_SWIFI_SVC_SE()}</td>
						<td id="11">${item.getX_SWIFI_CMCWR()}</td>
						<td id="12">${item.getX_SWIFI_CNSTC_YEAR()}</td>
						<td id="13">${item.getX_SWIFI_INOUT_DOOR()}</td>
						<td id="14">${item.getX_SWIFI_REMARS3()}</td>
						<td id="16">${item.getLNT()}</td>
						<td id="15">${item.getLAT()}</td>
						<td id="17">${item.getWORK_DTTM()}</td>
					</tr>
					<%
						}
					%>

				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="17">위치 정보를 입력한 후에 조회해 주세요.</td>
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
						Map<String, Object> result = (Map<String, Object>) request.getAttribute("result");
								List<Integer> pageList = (List<Integer>) result.get("pageList");
					%>
					<%
						if ((boolean) result.get("isPrevExist")) {
					%>
					<p>
						<a id="lt"
							href="searchWifi.wifi?lat=${result.lat}&lnt=${result.lnt}&pageNum=${result.blockFirstPageNum - 1}">&lt;</a>
					</p>

					<%
						}
					%>
					<%
						for (int i = 0; i < pageList.size(); i++) {
									pageContext.setAttribute("pageList", pageList.get(i));
					%>

					<p>
						<a
							href="searchWifi.wifi?lat=${result.lat}&lnt=${result.lnt}&pageNum=${pageList}">${pageList}</a>
					</p>

					<%
						}
					%>

					<%
						if ((boolean) result.get("isNextExist")) {
					%>
					<p>
						<a id="gt"
							href="searchWifi.wifi?lat=${result.lat}&lnt=${result.lnt}&pageNum=${result.blockLastPageNum + 1}">&gt;</a>
					</p>
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
	function geoFindMe() {

		function success(position) {

			const latitude = position.coords.latitude;
			const longitude = position.coords.longitude;

			var today = new Date();
			var date = today.toISOString();

			$('#LAT').attr("value", latitude.toFixed(8));
			$('#LNT').attr("value", longitude.toFixed(8));

			$.ajax({
				url : "/historyRecord.wifi",
				data : {
					lat : latitude,
					lnt : longitude,
					date : date
				},
				success : function(res) {
					var parsed = JSON.parse(res);
					if (parsed.result == "O") {
						alert("위치정보가 기록되었습니다.");
					} else {
						alert("위치정보 기록 중 오류가 발생하였습니다.");

					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
				}

			})
		}

		function error(e) {
			alert("Geolocation 오류 " + e.code + " : " + e.message);

		}
		if (!navigator.geolocation) {
			alert("브라우저가 위치 정보를 지원하지 않음");
		} else {
			navigator.geolocation.getCurrentPosition(success, error);
		}

	}

	function searchWifi() {
		let lat = document.getElementById("LAT").value;
		let lnt = document.getElementById("LNT").value;
		location.href = "searchWifi.wifi?lat=" + lat + "&lnt=" + lnt;
	}

	document.querySelector('#wifiInfo').addEventListener("click", searchWifi);
	document.querySelector('#find-me').addEventListener("click", geoFindMe);
</script>
</html>