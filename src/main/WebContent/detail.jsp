<%@page import="com.openwifi.dto.BookMarkDTO"%>
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
<link rel="stylesheet" href="${cp}/css/detail.css?ver=1.0.0"
	type="text/css" />
</head>
<%
	JsonDTO item = (JsonDTO) request.getAttribute("result");
	pageContext.setAttribute("item", item);
	List<BookMarkDTO> bookMarkDTOs = (List<BookMarkDTO>) request.getAttribute("bookMarkDTOs");
%>
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
	<div>
		<select id="listBtn" name="북마크 리스트">
			<option value="none" selected>북마크 그롭 이름 선택</option>
			<%
				for (BookMarkDTO item2 : bookMarkDTOs) {
					pageContext.setAttribute("item2", item2);
			%>
			<option value="${item2.getGROUP_NAME()}">${item2.getGROUP_NAME()}</option>
			<%
				}
			%>
		</select> <input class="addBtn" type="button" value="북마크 추가하기">
	</div>

	<table>
		<colgroup>
			<col style="width: 50%;" />
			<col style="width: 50%;" />
		</colgroup>
		<tr>
			<td>거리(Km)</td>
			<td id="1">${item.getDistance()}</td>
		</tr>
		<tr>
			<td>관리번호</td>
			<td id="2">${item.getX_SWIFI_MGR_NO()}</td>
		</tr>
		<tr>
			<td>자치구</td>
			<td id="3">${item.getX_SWIFI_WRDOFC()}</td>
		</tr>
		<tr>
			<td>와이파이명</td>
			<td id="4"><a
				href="detail.wifi?wifiId=${item.getX_SWIFI_MGR_NO()}&lat=${param.lat}&lnt=${param.lnt}">${item.getX_SWIFI_MAIN_NM()}</a>
			</td>
		</tr>
		<tr>
			<td>도로명주소</td>
			<td id="5">${item.getX_SWIFI_ADRES1()}</td>
		</tr>
		<tr>
			<td>상세주소</td>
			<td id="6">${item.getX_SWIFI_ADRES2()}</td>
		</tr>
		<tr>
			<td>설치위치(층)</td>
			<td id="7">${item.getX_SWIFI_INSTL_FLOOR()}</td>
		</tr>
		<tr>
			<td>설치유형</td>
			<td id="8">${item.getX_SWIFI_INSTL_TY()}</td>
		</tr>
		<tr>
			<td>설치기관</td>
			<td id="9">${item.getX_SWIFI_INSTL_MBY()}</td>
		</tr>
		<tr>
			<td>서비스구분</td>
			<td id="10">${item.getX_SWIFI_SVC_SE()}</td>
		</tr>
		<tr>
			<td>망종류</td>
			<td id="11">${item.getX_SWIFI_CMCWR()}</td>
		</tr>
		<tr>
			<td>설치년도</td>
			<td id="12">${item.getX_SWIFI_CNSTC_YEAR()}</td>
		</tr>
		<tr>
			<td>실내외구분</td>
			<td id="13">${item.getX_SWIFI_INOUT_DOOR()}</td>
		</tr>
		<tr>
			<td>WIFI접속환경</td>
			<td id="14">${item.getX_SWIFI_REMARS3()}</td>
		</tr>
		<tr>
			<td>X좌표</td>
			<td id="16">${item.getLNT()}</td>
		</tr>
		<tr>
			<td>Y좌표</td>
			<td id="15">${item.getLAT()}</td>
		</tr>
		<tr>
			<td>작업일자</td>
			<td id="17">${item.getWORK_DTTM()}</td>
		</tr>
	</table>
</body>
<script type="text/javascript">
	function addBookMark(){
		var groupName = $("select option:selected").text();
		var wifiId = $("#2").text();
		var wifiName = $("#4").children().text();
		$
		.ajax({
			url : "checkToBookMark.wifi",
			data : {
				groupName : groupName,
				wifiId : wifiId,
				wifiName : wifiName
			},
			success : function(res) {
				var parsed = JSON.parse(res);
				if (parsed.result == "O") {
					location.href = "addToBookMark.wifi?groupName="+groupName+"&wifiId="+wifiId+"&wifiName="+wifiName;
				} else if(parsed.result =="N"){
					location.href = "detail.wifi"
				} 
				else {
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
$('.addBtn').on('click',addBookMark);
</script>
</html>