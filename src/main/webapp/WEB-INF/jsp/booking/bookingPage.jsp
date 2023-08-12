<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
	<div class="text-center"><h3>뮤지컬 &lt;가나다&gt;</h3></div>
	
	<form id="bookingForm" method="post" action="/book/booking">

		<div class="d-flex">
		<select id="showDate" name="showDate" class="form-control col-6 m-2">  
		    <option value="">날짜</option>
		    <option value="2023-09-01">2023-09-01 금</option>
		    <option value="2023-09-02">2023-09-02 토</option>
		    <option value="2023-09-03">2023-09-03 일</option>
		    <option value="2023-09-04">2023-09-04 월</option>
		    <option value="2023-09-05">2023-09-05 화</option>
		    <option value="2023-09-06">2023-09-06 수</option>
		    <option value="2023-09-07">2023-09-07 목</option>
		</select>
		
		<select id="showTime" name="showTime" class="form-control col-6 m-2">  
		    <option value="">시간</option>
		    <option value="15:00:00">15시 00분</option>
		    <option value="19:00:00">19시 00분</option>
		</select>
		</div>
		
		<div>
			<c:forEach begin="1" end="48" step="1" var="seat" varStatus="status">
				<c:if test="${status.current % 8 == 1}">
					<div class="d-flex justify-content-center">
				</c:if>
				<%-- 왜 submit 되는 거지? --%>
				<button type="button" name="seat" class="seat-box mb-2 mx-2" value="${seat}">${seat}</button>
				<c:if test="${status.current % 8 == 0}">
					</div>
				</c:if>
				
			</c:forEach>
		</div>
		
		<div class="d-flex justify-content-around mt-3 mb-5">
			<div>
				<div>총 좌석 수: 1석</div>
				<div name="seatGrade">좌석등급: R석</div>
				<div name="seat">좌석번호: 1층 B열 15</div>
			</div>
		
			<%-- link 가기 <a href="/book/pay_view" class="btn btn-info">결제</a> --%>
			<button type="submit" class="btn btn-info my-2">선택완료</button>
		</div>
	</form>
</div>


<script>
	$(document).ready(function() {
		// 폼 제출
		$('#bookingForm').on('submit', function(e) {
			e.preventDefault();
			
			location.href="/book/pay_view";
		});
		
		
		// 좌석 클릭 시
		$('button[name=seat]').on('click', function() {
			alert($(this).val());
			
			/*if ($(this).hasClass("checked-box") {
				$(this).removeClass("checked-box");
				$(this).addClass("seat-box");
			} else {
				$(this).addClass("checked-box");
				$(this).removeClass("seat-box");
			}*/
		});
	});

</script>

