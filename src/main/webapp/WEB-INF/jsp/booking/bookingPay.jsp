<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>

	<div class="text-center"><h3>&lt;${show.name}&gt;</h3></div>
	
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
					<td><input type="radio" name="discount" value="${seatPrice}" data-name="정가" /></td>
				</tr>
				<tr>
					<td>청소년 30%</td>
					<td><fmt:formatNumber value="${seatPrice * 0.7}" type="number" pattern="0" />원</td>
					<td><input type="radio" name="discount" value="${seatPrice * 0.7}" data-name="청소년 30%" /></td>
				</tr>
				<tr>
					<td>복지 50%</td>
					<td><fmt:formatNumber value="${seatPrice * 0.5}" type="number" pattern="0" />원</td>
					<td><input type="radio" name="discount" value="${seatPrice * 0.5}" data-name="복지 50%" /></td>
				</tr>
			</tbody>
		</table>
		<input type="hidden" id="discountName" name="discountName">
		
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
		// discountName setting
		$("#discountName").val(discountName);
		$("input[name=discount]").on('click', function() {
			let discountName = $(this).data("name");
			$("#discountName").val(discountName);
		});
		
		
		// 폼 제출
		$('#payForm').on('submit', function(e) {
			e.preventDefault();

			let formData = $(this).serialize();
			console.log(formData);
			
			$.ajax({
				type: 'POST'
				, url: '/book/pay'
				, data: formData
				, success: function(data) {
					if (data.code == 1) {
						alert("결제 완료되었습니다.");
						location.href="/book/booking_done_view";
					} else {
						// 로직 실패
						alert(data.errorMessage);
					}
				}
				,error: function(request, status, error) {
					alert("오류");
				}
			});
		});
		
	});

</script>