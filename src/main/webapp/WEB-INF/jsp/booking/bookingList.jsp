<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="border text-center">
	<%-- 세션값 --%>
	<div class="my-3">${userName}님은 현재 FAMILY 회원입니다.</div>
	<div class="mb-2"><a href="">※회원정보수정※</a></div>
</div>

<div class="text-center">
	<h3 class="my-3">최근 예매 내역</h3>
</div>

<a href="/book/book_list_view">전체</a>
<a href="/book/book_list_view?isBooked=1">예매완료</a>
<a href="/book/book_list_view?isBooked=0">예매취소</a>

<table class="table text-center">
	<thead>
		<tr>
			<th>예매일</th>
			<th>예약번호</th>
			<th>공연명</th>
			<th>관람일</th>
			<th>매수</th>
			<th>취소가능일</th>
			<th>상태</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="booking" items="${bookingViewList}">
			<tr>
				<td class="text-align:center">
					<fmt:formatDate value="${booking.booking.bookingDate}" pattern="yyyy/MM/dd" />
				</td>
				<td>${booking.booking.bookingNumber}</td>
				<td>${booking.showView.show.name}</td>
				<td>
					<fmt:formatDate value="${booking.booking.showDate}" pattern="yyyy/MM/dd" />
				</td>
				<td>1</td>
				<td>
					<%-- 예약 날짜 이전일 --%>
					<fmt:formatDate value="${booking.booking.bookingDate}" pattern="yyyy/MM/dd" />
				</td>
				<%-- 쿼리스트링으로 예약id가 붙는다. --%>
				<td>
					<c:if test="${booking.booking.isReserved > 0}">
						예약완료
						<a href="/book/book_detail_view?bookingId=${booking.booking.id}" class="btn btn-info">상세</a>
					</c:if>
					<c:if test="${booking.booking.isReserved <= 0}">
						예약취소
						<a href="/book/book_detail_view?bookingId=${booking.booking.id}" class="btn btn-secondary">상세</a>
					</c:if>
					
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<!-- paging -->
<%-- <div class="d-flex justify-content-center">
	<a href="" class="btn btn-dark mr-5">&lt;&lt; 이전</a>
	<a href="" class="btn btn-dark">다음 &gt;&gt;</a>
</div>--%>