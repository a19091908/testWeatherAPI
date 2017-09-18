<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/index.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body>
	<h2>一般天氣預報-今明 36 小時天氣預報</h2>
	<hr>
	<div>
		<spring:url value="./WeatherCtrl?action=insert36Hours"
			var="insert36Hours" htmlEscape="true"></spring:url>
		<form action="${insert36Hours}" method="post">
			<button type="submit">新增-今明 36 小時天氣預報</button>
		</form>
		<hr>
		<form action="./WeatherCtrl?action=search36Hours" method="post">
			<button type="submit">查詢</button>
			<select name="locationName" id="locationName">
				<option value="嘉義縣">嘉義縣</option>
				<option value="新北市">新北市</option>
				<option value="嘉義市">嘉義市</option>
				<option value="新竹縣">新竹縣</option>
				<option value="新竹市">新竹市</option>
				<option value="臺北市">臺北市</option>
				<option value="臺南市">臺南市</option>
				<option value="宜蘭縣">宜蘭縣</option>
				<option value="苗栗縣">苗栗縣</option>
				<option value="雲林縣">雲林縣</option>
				<option value="花蓮縣">花蓮縣</option>
				<option value="臺中市">臺中市</option>
				<option value="臺東縣">臺東縣</option>
				<option value="桃園市">桃園市</option>
				<option value="南投縣">南投縣</option>
				<option value="高雄市">高雄市</option>
				<option value="金門縣">金門縣</option>
				<option value="屏東縣">屏東縣</option>
				<option value="基隆市">基隆市</option>
				<option value="澎湖縣">澎湖縣</option>
				<option value="彰化縣">彰化縣</option>
				<option value="連江縣">連江縣</option>
			</select>
		</form>
		<hr>
		<c:if test="${not empty locationWeatherList}">
			<!--搜尋到n筆資料 -->
			<c:forEach items="${locationWeatherList}" var="n">
				<table>
					<thead>
						<tr>
							<th colspan="7">${n.locationName}</th>
						</tr>
						<tr>
							<th>天氣描述</th>
							<th>降雨機率(%)</th>
							<th>最小溫度(℃)</th>
							<th>舒適度</th>
							<th>最大溫度(℃)</th>
							<th>起始時間</th>
							<th>結束時間</th>
						</tr>
					</thead>
					<c:forEach begin="0" end="2" var="i">
						<tr>
							<c:forEach begin="0" end="4" var="j">
								<td>${n.weatherElement[j].time[i].parameter.parameterName}</td>
							</c:forEach>
							<td>${n.weatherElement[0].time[i].startTime}</td>
							<td>${n.weatherElement[0].time[i].endTime}</td>
						</tr>
					</c:forEach>
				</table>
				<br>
			</c:forEach>
		</c:if>
	</div>
</body>
</html>
