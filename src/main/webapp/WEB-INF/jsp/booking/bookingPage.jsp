<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>
	<div class="text-center"><h3>뮤지컬 &lt;가나다&gt;</h3></div>
	
	
	<form id="bookingForm" method="post" action="/book/booking">

		<div class="d-flex">
		<select id="showDate" name="showDate" class="form-control col-6 m-2">  
		    <option value="">날짜</option>
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
				<fmt:parseNumber var="seatCol" type="number" value="${seat/8}" integerOnly="true" />
				
				<button type="button" name="seat" class="seat-box mb-2 mx-2"
				data-seat-floor="1층" data-seat-col="${seatCol}" data-seat-row="${seat%8}"
				>${seat%8}</button>
				<c:if test="${status.current % 8 == 0}">
					</div>
				</c:if>
				
				
				<%-- seat select 레퍼런스 --%>
				<%--document.querySelector("#TmgsTable > tbody > tr > td > img:nth-child(35)")
				<img src="/static/" class="stySeat" style="left: 113px; top: 232px; user-select: auto;" alt="[S석] 1층-A구역 13열-7" title="[S석] 1층-A구역 13열-7" onclick="javascript: SelectSeat('SID16', '2', '1층', 'A구역 13열', '7', '001')">
				<button type="button" title="A4 (스탠다드/일반)" class="jq-tooltip seat-condition standard common" style="position:absolute; left:311px; top:50px; width:20px;" seatclasscd="GERN_CLS" seatzonecd="GERN_ZONE" seatuniqno="00100401" rownm="A" seatno="4" seatchoidircval="1" seatchoigrpno="2" seatchoigrpnm="A2" seatchoirowcnt="6" seatchoigrpseq="4" seattocnt="1"><span class="num">4</span><span class="kind">스탠다드</span><span class="condition">판매가능</span><span class="rank">일반</span></button>
				<a href="#none" alt="O11 일반석 SWEET SPOT 선택불가능" class="sel  sweet_spot p0 grNum48 no_select" block-code="p0" data-seat="O11" seat-statuscode="0" seat-group="grNum48" seat-code="1O11" style="left: 288.273px; top: 391.875px;"><span class="sweet_spot f1 blind" style="top: 0px;">11</span></a>
				--%>
				
				
				
			</c:forEach>
		</div>
		
		<div class="d-flex justify-content-around mt-3 mb-5">
			<div>
				<div name="total">총 좌석 수: 1석</div>
				<div name="seatGrade">좌석등급: R석</div>
				<div name="seat" id="seat">좌석번호: </div>
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
		
		
		
		// 날짜와 시간이 선택됐을 때 => 잔여석 표시
		// 좌석 클릭 시 => 토글 및 정보 띄우기
		$('button[name=seat]').on('click', function() {
			// 새로고침 시 날짜가 안 뜨는 현상 => 수동 select?
			if($("#showDate").val() == "" || $("#showTime").val() == "") {
				alert("날짜와 시간을 선택해주세요");
				return;
			}
			
			// 예매된 좌석 & 선택된 좌석 & 선택되지 않은 좌석
			
			// 글자 추가
			$("#seat").append($(this).attr('data-seat-floor') + " ");
			// +1 해줘야 한다.
			$("#seat").append($(this).attr('data-seat-col') + "열 ");
			$("#seat").append($(this).attr('data-seat-row') + " ");
			
			
			/*if ($(this).hasClass("checked-box") {
				$(this).removeClass("checked-box");
				$(this).addClass("seat-box");
			} else {
				$(this).addClass("checked-box");
				$(this).removeClass("seat-box");
			}*/
		});
			
			
		// 날짜 데이터
		window.addEventListener ('message', function(e) {
			//alert(e.data.selectedDate);
			e.data.validEndDate.setDate(e.data.validEndDate.getDate() + 1);
			
			for (let i = e.data.validStartDate; i <= e.data.validEndDate; i.setDate(i.getDate() + 1)) {
				let finalDate = formatDate(e.data.validStartDate);
				let validStartDate = '<option value=' + finalDate + '>' + finalDate + '</option>';
				$("#showDate").append(validStartDate);
			}
			console.log(e.data.selectedDate);
			// selected 넣기
			// 한 값만 계속 나온다 그 이유는?
			let selectedDate = new Date(e.data.selectedDate);
			selectedDate = formatDate(selectedDate);
			$('#showDate option[value="' + selectedDate + '"]').prop("selected", true);
		});
		
		// 날짜 포맷
		function formatDate(date) {
		    let year = date.getFullYear();
		    let month = String(date.getMonth() + 1).padStart(2, '0');
		    let day = String(date.getDate()).padStart(2, '0');
		    return year + '-' + month + '-' + day;
		}
			
			
		
	});

</script>

