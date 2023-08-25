<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>
	<div class="text-center"><h3>&lt;${show.name}&gt;</h3></div>
	
	
	<form id="bookingForm" method="post" action="/book/booking">
		<input type="hidden" name="showId" id="showId" value="${show.id}">
		
		<div class="d-flex">
		<select id="showDate" name="showDate" class="form-control col-6 m-2">  
			<%-- 날짜 왜 안 뜨지 --%>
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
				<%-- form 안 button은 submit 된다. --%>
				<fmt:parseNumber var="seatCol" type="number" value="${seat/8}" integerOnly="true" />
				
				<button type="button" name="seat" class="seat-box mb-2 mx-2"
				data-seat-floor="1층" data-seat-col="${seatCol}" data-seat-row="${seat%8}"
				data-seat-num="${status.count}"
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
				<input type="hidden" name="totalInput" id="totalInput" value="1" data-total="1">				
				<div name="total" id="total">
				</div>
				
				<input type="hidden" name="seatGradeInput" id="seatGradeInput" value="R" data-seat-grade="R">				
				<div name="seatGrade" id="seatGrade">
				</div>
				
				<input type="hidden" name="seatInput" id="seatInput">
				<div name="seat" id="seat">
				</div>
			</div>
		
			<%-- link 가기 <a href="/book/pay_view" class="btn btn-info">결제</a> --%>
			<button type="submit" class="btn btn-info my-2">선택완료</button>
		</div>
	</form>
</div>


<script>
	$(document).ready(function() {
		// 맨 처음에 아무것도 선택하지 않은 채 예매창에 들어왔을 때 해당 날짜 좌석을 띄움.
		// 날짜 시간 중 하나라도 선택되지 않으면 좌석 모두 비활성화
		// 날짜 시간 공연명에 맞는 좌석정보를 불러오고 그에 따라 처리
		
		
	    
	    // 날짜와 시간이 모두 선택되었을 때 실행되는 함수
	    function checkDateTimeSelection() {
	    	// 왜 val값이 undefined가 될까 시점의 문제?
	        var selectedDate = $('#showDate').val();
	        var selectedTime = $('#showTime').val();

	        console.log(selectedDate);
	        console.log(selectedTime);
	        if ((selectedDate != null && selectedTime !== null && selectedDate != "" && selectedTime != "")) {
	            // 날짜와 시간이 모두 선택되었을 때 실행할 작업
	            $.ajax({
	            	url:"/book/booking_seat"
	            	, data: {"showId":${show.id}, "selectedDate":selectedDate , "selectedTime":selectedTime}
	            	, success: function(data) {
	            		//console.log(data);
	            		
	            		
	            		$('button[name=seat]').prop("disabled", false);
	            		$('button[name=seat]').removeClass("reserved-box");
	            		$('button[name=seat]').removeClass("selected-box");
	            		$('button[name=seat]').addClass("seat-box");
	            		
	            		//data[0]이 seat-num인 버튼 data[1]이 seat-num인 버튼
	            			
	            		if (Array.isArray(data)) {
	            			data.forEach(function(seatNum) {
	            				var selectedSeat = $('button[data-seat-num="' + seatNum + '"]');
            					selectedSeat.prop("disabled", true);
	            				selectedSeat.removeClass("seat-box");
	            				selectedSeat.addClass("reserved-box");

	            			});
	            			
	            		}
	            	}
	            });
	        }
	    }
		
		$('#showDate').on('change', checkDateTimeSelection);
	    $('#showTime').on('change', checkDateTimeSelection);
		
	    
	    
	    // 처음 화면에 들어왔을 때
	    // 함수 실행
	    
	    // 날짜가 바뀌었을 때
	    // 시간 비우고 좌석초기화하고 함수 실행
	    
	    // 시간이 바뀌었을 때
	    // 좌석 초기화하고 함수 실행
	    
	    
	    
	    
	    
		
		// 폼 제출
		$('#bookingForm').on('submit', function(e) {
			e.preventDefault();
			
			// validation 충분한지?
			if($('#seat').is(':empty')) {
				alert("좌석을 선택해주세요");
				return;
			}
			
			let url = $(this).attr('action');
			let params = $(this).serialize(); // 폼태그에 있는 name 속성-값들로 파라미터 구성
			console.log(url);
			console.log(params);

						
			$.ajax({
		        type: 'POST'
		        ,url: url
		        ,data: params
		        ,success: function(data) {
		            if (data.code == 1) {
		            	window.location.href = data.forwardUrl;
		            } else {
		            	alert(data.errorMessage);
		            }
		        }
		        ,error: function(request, status, error) {
		        	alert("오류입니다.");
		        }
		    });

			
			/*$.ajax({
				url: '/book/booking'
				, type: 'POST'
				//, contentType: 'application/json'
				, data: params
				, success: function(data) {
					if (data === 'Success') {
						//location.href = '/book/pay_view';
						
						var form = document.createElement('form');
						form.method = 'POST'; // post로 변경
						form.action = '/book/pay_view';
						form.style.display = 'none'; // ?
								
						var input = document.createElement('input');
						input.type = 'hidden';
						input.name = 'params';
						input.value = params;
						form.appendChild(input);
						
						document.body.appendChild(form);
						form.submit();
					} else {
						alert("좌석 선택에 실패했습니다. 다시 시도해주세요.");
					}
				}
				, error: function() {
					alert("에러입니다.");
				}
			});*/
			
			
			
			
			
			
			
			//function sendRequest(retry) {
			/*$.post(url, params) // request
			.done(function(data) {
				if (data.code == 1) {
					// showId를 쿼리스트링으로 넘기려하니 (${showId})
					// Err_incomplete_chunked_encoding 발생하며 날짜도 안 뿌려짐
					localStorage.setItem('bookingData', data);
					
					//location.href="/book/pay_view?showId=" + data.showId + "&bookingId=" + data.bookingId;
					location.href="/book/pay_view";
				} else {
					alert(data.errorMessage);
				}
			});*/
				/* 원래 bookingNumber 중복발생으로 실패하면
				재시도하려 했으나 에러 발생 시 무한반복되는 현상으로 보류
				// 이 문구 삭제에도 불구하고 무한루프 현상 발생. 원인???
				.fail(function(jqXHR, textStatus) {
					if (retry > 0 && jqXHR.responseJSON && jqXHR.responseJSON.errorMessage && jqXHR.responseJSON.errorMessage.includes("Duplicated")) {	                        // bookingNumber 중복 에러 처리 (임시)
                        sendRequest(retry - 1); // 다시 요청 시도
                    } else {
                        alert(jqXHR.responseJSON.errorMessage);
                    }
				});
			}
			sendRequest(5); // 초기 실행
			*/
		});
		
		
		
		
		
		// 엔터를 누르면 초기화되는 현상 고치기
		// 날짜와 시간이 선택됐을 때 => 잔여석 표시
		// 좌석 클릭 시 => 토글 및 정보 띄우기
		$('button[name=seat]').on('click', function() {
			var checkedCount = $('button[name=seat].checked-box').length;
			if (!$(this).hasClass("checked-box") && checkedCount > 0) {
				alert('한 좌석만 선택 가능합니다.');
				return;
			}
			
			// 새로고침 시 날짜가 안 뜨는 현상 => 수동 select?
			if($("#showDate").val() == "" || $("#showTime").val() == "") {
				alert("날짜와 시간을 선택해주세요");
				return;
			}
			
						
			// toggle
			if ($(this).hasClass("checked-box")) {
				$(this).removeClass("checked-box");
				$(this).addClass("seat-box");
				$("#seat").empty();
				$("#total").empty();
				$("#seatGrade").empty();
				
				
				
			} else {
				$(this).addClass("checked-box");
				$(this).removeClass("seat-box");
				
				// 좌석 정보 뿌리기
				$("#seat").append("좌석번호: " + $(this).attr('data-seat-floor') + " ");
				// +1 해줘야 한다.
				$("#seat").append($(this).attr('data-seat-col') + "열 ");
				$("#seat").append($(this).attr('data-seat-row') + " ");
				
				// 아무것도 클릭 안 하고 맨 처음에 선택했을 때 -> -> 뜨는 현상
				let seatInfo = $(this).data('seat-floor') + " " + $(this).data('seat-col') + "열 " + $(this).data('seat-row') + " " + $(this).data('seat-num');
				
				//alert(seatInfo);
				$("#seatInput").val(seatInfo);
				//alert($("#seatInput").val());
				
				// 좌석매수와 좌석등급
				// val()로 하면 2번 이상 toggle 시 undefined 현상 발생
				// total을 empty하니까 input도 같이 사라짐!!!
				let totalInput = $("#totalInput").data('total');
				let seatGradeInput = $("#seatGradeInput").data('seat-grade');
				
				// 좌석 수
				$("#total").append("총 좌석 수: " + totalInput + "석");
				// 좌석 등급
				$("#seatGrade").append("좌석등급: " + seatGradeInput + "석");
				
			}
		});
			
			
		// 날짜 데이터 - 새로고침 시에도 동작하게
		//window.addEventListener('beforeunload', function() {
			window.addEventListener ('message', function(e) {
				e.data.validEndDate.setDate(e.data.validEndDate.getDate() + 1);
				//console.log(e.data.selectedTime);
				// 뿌리기
				/*
				for (let i = e.data.validStartDate; i <= e.data.validEndDate; i.setDate(i.getDate() + 1)) {
					let finalDate = formatDate(e.data.validStartDate);
					let validStartDate = '<option value=' + finalDate + '>' + finalDate + '</option>';
					$("#showDate").append(validStartDate);
				}
				*/
				
				for (let i = new Date(e.data.validStartDate); i < e.data.validEndDate; i = new Date(i.getTime() + 24 * 60 * 60 * 1000)) {
				    let finalDate = formatDate(i);
				    let validStartDate = '<option value="' + finalDate + '">' + finalDate + '</option>';
				    $("#showDate").append(validStartDate);
				}
				// selected 넣기
				// 한 값만 계속 나온다 그 이유는?
				// selectedTime 지금 날짜와 시간 이전 거는 선택 제한하기
				// 시간 설정 및 validation은 추후에
				let selectedDate = e.data.selectedDate;
				let selectedTime = e.data.selectedTime;
				
				// 1) 예매하기 버튼이 눌릴 때 가장 첫 날짜(today or validStartDate)가 자동 선택되도록 한다. -> setDate가 작동하나?
				// 2) 여기서 분기를 넣는다.
				if (selectedDate != null) {
					//selectedDate = formatDatepicker(selectedDate);
					//console.log("###", e.data.selectedDate);
					$('#showDate option[value="' + selectedDate + '"]').prop("selected", true);
					$('#showTime option[value="' + selectedTime + '"]').prop("selected", true);
				} 
				// 현재는 동작하지 않는 코드 => 추후 수정
				else {
					$('#showDate option[value="' + formatDate(new Date()) + '"]').prop("selected", true);
					$('#showTime option[value="15:00:00"]').prop("selected", true);
				}
		
				checkDateTimeSelection();
			});
		//});
		
		
		
		// 날짜 포맷
		function formatDate(date) {
		    let year = date.getFullYear();
		    let month = String(date.getMonth() + 1).padStart(2, '0');
		    let day = String(date.getDate()).padStart(2, '0');
		    return year + '-' + month + '-' + day;
		}
		
		function formatDatepicker(date) {
			let year = date.substring(0, 4);
			let month = date.substring(6, 8);
			let day = date.substring(10, 12);
			return year + '-' + month + '-' + day;
		}
			
			
		
	});

</script>

