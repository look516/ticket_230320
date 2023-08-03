<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%--
<h1>root element: ${root}</h1>
<h2>파싱할 총 리스트 개수: ${num}</h2>
 --%>

<div class="my-4 display-4 text-center">전체 공연</div>

<table class="table text-center">
	<thead>
		<tr>
			<th>사진</th>
			<!-- 객체에 담고 사진은 따로 담은 후 db에 넣는다.
			단, 사진이 비어있다면 에러를 내면서 db에 넣지 않는다. -->
			<th>공연명</th>
			<th>장소</th>
			<th>기간</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="show" items="${shows}" varStatus="count">
			<tr>
				<td><img src="${show.poster}" alt="공연 포스터" width=200px;></td>
				<td>${show.prfnm}</td>
				<td>${show.fcltynm}</td>
				<td>${show.prfpdfrom}~${show.prfpdto}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>