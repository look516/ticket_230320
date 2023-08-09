<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="border text-center">
	<div class="my-3">000님은 현재 FAMILY 회원입니다.</div>
	<div class="mb-2"><a href="">※회원정보수정※</a></div>
</div>

<div class="text-center">
	<h3 class="my-3">최근 예매 내역</h3>
</div>

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
	<!-- <c:forEach items="" var="booking"> -->
		<tr>
			<td class="text-align:center">2023-01-02</td>
			<td>abcde1234abcde</td>
			<td>가나다</td>
			<td>2023-02-21</td>
			<td>1</td>
			<td>2023-02-20</td>
			<!-- 쿼리스트링으로 예약id가 붙는다. -->
			<td>예약완료<a href="/book/book_detail_view?bookingId=${book.id}" class="btn btn-info">상세</a></td>
		</tr>
	<!-- </c:forEach> -->
	</tbody>
</table>

<!-- paging -->
<div class="d-flex justify-content-center">
	<a href="" class="btn btn-dark mr-5">&lt;&lt; 이전</a>
	<a href="" class="btn btn-dark">다음 &gt;&gt;</a>
</div>