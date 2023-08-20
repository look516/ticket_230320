<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.Date" %>

<div class="d-flex justify-content-between">
	<%-- 공연 정보 --%>
	<div class="col-8">
		<%-- 제목 --%>
		<h1 class="pl-4 mt-3">${show.show.genre} &lt; ${show.show.name} &gt;</h1>

		<div class="d-flex">
			<%-- 이미지 --%>
			<div class="ml-3">
				<img src="${show.show.imagePath}" alt="공연 이미지" width="200px">
			</div>
			
			<%-- 간략정보 --%>
			<div>
				<ul>
					<li class="show-info d-flex">
						<strong>장소</strong>
						<div>
							<!-- <a class="infoBtn" data-popup="info-place" role="button" href="#"> -->
							<p><a class="infoBtn" href="#">
								${show.theater.name}
								<i>(자세히)</i>
							</a></p>
						</div>
					</li>
					<li class="show-info d-flex">
						<strong>공연기간</strong>
						<div>
							<p>${show.show.startDate} ~ ${show.show.endDate}</p>
						</div>
					</li>
					<li class="show-info d-flex">
						<strong>공연시간</strong>
						<div>
							<p>${show.show.time}분</p>
						</div>
					</li>
					<li class="show-info d-flex">
						<strong>관람연령</strong>
						<div>
							<p>${show.show.age}세 이상</p>
						</div>
					</li>
					
				</ul>
			</div>
		</div>
		
		<div class="mt-3 ml-3">
			<%-- 공연정보 탭메뉴 --%>
			<ul class="tab-nav nav nav-fill">
				<li class="nav-item"><a href="tab1" class="btn btn-secondary nav-link tab-menu">공연정보</a></li>
				<li class="nav-item"><a href="tab2" class="btn nav-link tab-menu">캐스팅정보</a></li>
				<li class="nav-item"><a href="tab3" class="btn nav-link tab-menu">판매정보</a></li>
				<li class="nav-item"><a href="tab4" class="btn nav-link tab-menu">관람후기</a></li>
				<li class="nav-item"><a href="tab5" class="btn nav-link tab-menu">기대평</a></li>
				<li class="nav-item"><a href="tab6" class="btn nav-link tab-menu">Q&A</a></li>
			</ul>
			
			<%-- 공연정보 탭내용 --%>
			<div id="tab" class="tab-content-link">
			</div>
			
		</div>
	</div>
	
	<%-- 예매창 --%>
	<div class="ml-3 book-area col-4 d-flex justify-content-center">
		<div class="text-center">
			<h3>관람일</h3>
			<div id="datepicker"></div>
			<div class="my-3" id="selectedDate"></div>
			<div class="d-flex justify-content-around" id="timeBtn">
				
			</div>
			<%-- 로그인 처리 / 공연별 분기 --%>
			<button class="btn btn-info my-2 col-12" id="reserveShowBtn">예매하기</button>
		</div>
	</div>
</div>


<script>
	$(document).ready(function(){
		// 탭 메뉴
		$('.tab-menu').on('click', function(e) {
			event.preventDefault(); // 이동 방지
			
			// 버튼 조정
			$('.tab-menu').removeClass('btn-secondary');
			$(this).addClass('btn-secondary');
			
			// 내용 조정
			//$('.tab-content-link').addClass('d-none');
			//let tabId = $(this).attr('href');
			//$(tabId).removeClass('d-none');
			
			
			
			// 탭 ajax (내용 조정)
			// 보내야 할 것은 queryString에 붙은 값, 리턴해주는 페이지 값
			var query = window.location.search;
			var param = new URLSearchParams(query);
			var showId = param.get('showId');
			
			
			$.ajax({
				url: "/show/show_tab_view?index=" + $(this).attr('href')
				, data: {"showId":showId}
				, success: function(data) {
					// 1) data에 ${show}를 넣든, showId를 넣든 탭 페이지에 정보 넣어주기
					// 2) showView에서 List<ReviewView> 제외
					$('#tab').html(data);
				}
			});
		});
		
		
		
		// 날짜
		
		$.datepicker.setDefaults({
            dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'] // 요일을 한글로 변경
			,dateFormat: "yy년 mm월 dd일"
			,showMonthAfterYear:true // 월- 년 순서가아닌 년도 - 월 순서
			,yearSuffix: "년" //달력의 년도 부분 뒤 텍스트
			,monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 텍스트
	        ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip
	        ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 텍스트
	        ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 Tooltip
	        , minDate: new Date("${show.show.validStartDate}") > new Date() ? new Date("${show.show.validStartDate}") : new Date()
			, maxDate: new Date("${show.show.validEndDate}")
        });
		
		// 추후 fmt 변경할 것
		// 추후 기본값 설정할 것
		
		// 3시 이전, 다음날부터: 둘 다 3시 이후: 7시만 7시 이후: 내일 것부터
	
		
		$('#datepicker').datepicker();
		
		var selectedDate = null;
		$('#datepicker').on('change', function() {
			selectedDate = $(this).val();
			$('#selectedDate').text(selectedDate);
			
			
				$('#timeBtn').html(
						'<input type="radio" name="selectShowTime" id="15:00:00">'
						+ '<label for="15:00:00">15시</label>'
						+ '<input type="radio" name="selectShowTime" id="19:00:00">'
						+ '<label for="19:00:00">19시</label>');
			
			
			
		});
		
		
		
		// 전송할 데이터 객체
		
		var query = window.location.search;
		var param = new URLSearchParams(query);
		var showId = param.get('showId');
		
		var selectedTime = $('input[name="selectShowTime"]:checked').attr('id');
			
		var bookingData = {
			showId: showId
			, selectedDate: selectedDate
			, selectedTime: selectedTime
			, validStartDate: new Date("${show.show.validStartDate}") > new Date() ? new Date("${show.show.validStartDate}") : new Date()
			, validEndDate: new Date("${show.show.validEndDate}")
		};
 		
		// 예매하기 버튼
		// 시간 선택 후 예매하기 버튼 누를 수 있게
		
		$('#reserveShowBtn').on('click', function(e) {
			e.preventDefault();
			bookingData.selectedDate = selectedDate;
			bookingData.selectedTime = selectedTime;

			let bookingUrl = "/book/book_page_view?showId=" + showId;
			var popup = window.open(bookingUrl, "_blank", 'width=800px, height=700px popup');
			popup.onload = function() {
				popup.postMessage(bookingData, "*") // 임의로 모든 주소에서 허용
			}
		});
		
		
		
		
		
		
		
	});

</script>