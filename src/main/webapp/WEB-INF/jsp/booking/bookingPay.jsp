<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>

	<div class="text-center"><h3>&lt;${booking.showId}&gt;</h3></div>
	
	<%-- 제목 / 날짜 / 시간 / 좌석정보 (+좌석수 좌석등급) --%>
	
	<form id="payForm" method="post" action="/book/pay">
	<c:if test = "${booking.seatGradeInput eq 'R'}">
		<c:set var="seatPrice" value="60000" />
	</c:if>
	
		<table class="table text-center">
			<thead>
				<tr>
					<th>할인종류</th>
					<th>가격</th>
					<th>선택</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>정가</td>
					<td>${seatPrice}원</td>
					<td><input type="radio" name="discount" checked /></td>
				</tr>
				<tr>
					<td>청소년 30%</td>
					<td>${seatPrice * 0.7}원</td>
					<td><input type="radio" name="discount" /></td>
				</tr>
				<tr>
					<td>복지 50%</td>
					<td>${seatPrice * 0.5}원</td>
					<td><input type="radio" name="discount" /></td>
				</tr>
			</tbody>
		</table>
		
		<select id="payment" name="payment" class="form-control my-2">  
		    <option value="">결제수단 선택</option>
		    <option value="KB">국민카드</option>
		    <option value="SAMSUNG">삼성카드</option>
		    <option value="SHINHAN">신한카드</option>
		    <option value="WOORI">우리카드</option>
		</select>
	
		<button type="submit" class="btn btn-info form-control my-2">결제하기</button>
	</form>
</div>


<script>
	$(document).ready(function() {
		// 폼 제출
		$('#payForm').on('submit', function(e) {
			e.preventDefault();
			alert("####");
			
			//$.ajax
			location.href="/book/booking_done_view";
		});
	});

</script>