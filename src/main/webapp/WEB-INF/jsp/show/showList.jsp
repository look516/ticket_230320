<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%--
<h1>root element: ${root}</h1>
<h2>파싱할 총 리스트 개수: ${num}</h2>
 --%>

<div class="my-4 display-4 text-center">${genre} 공연</div>

<%-- api 가져오기 1차시도
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
--%>



<table class="table text-center">
	<thead>
		<tr>
			<th>사진</th>
			<th>공연명</th>
			<th>장소</th>
			<th>기간</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="product" items="${showList}">
			<tr>
				<td><a href="/show/show_detail_view?showId=${product.show.id}"><img src="${product.show.imagePath}" alt="공연 포스터" width=100px;></a></td>
				<td><a href="/show/show_detail_view?showId=${product.show.id}">${product.show.name}</a></td>
				<td>${product.theater.name}</td>
				<td>${product.show.startDate} ~ ${product.show.endDate}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<%-- 페이징 --%>
<div class="d-flex justify-content-center mb-3">
	${currentPage + 1}페이지 / ${totalPages} 페이지
</div>
<div class="d-flex justify-content-center">
    <c:choose>
        <c:when test="${currentPage > 0}">
            <a href="/show/show_list_view?genre=${genre}&page=${currentPage - 1}" class="mr-5 btn btn-warning">&lt;&lt; 이전</a>
        </c:when>
        <c:otherwise>
            <a href="#" class="mr-5 btn btn-warning disabled">&lt;&lt; 이전</a>
        </c:otherwise>
    </c:choose>
    
    <c:choose>
        <c:when test="${currentPage < totalPages - 1}">
            <a href="/show/show_list_view?genre=${genre}&page=${currentPage + 1}" class="btn btn-warning">다음 &gt;&gt;</a>
        </c:when>
        <c:otherwise>
            <a href="#" class="btn btn-warning disabled">다음 &gt;&gt;</a>
        </c:otherwise>
    </c:choose>
</div>