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
			<%--<input type="text" id="datepicker"> --%>
			<div><div id="calendar"></div>
			<div><div class="my-3" id="selectedDate"></div></div>
			<div><div class="d-flex justify-content-around" id="timeBtn"></div>
				
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
			e.preventDefault(); // 이동 방지
			
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
			
			var tabId = $(this).attr('href');
			$.ajax({
				url: "/show/show_tab_view?index=" + tabId
				, data: {"showId":showId}
				, success: function(data) {
					// 1) data에 ${show}를 넣든, showId를 넣든 탭 페이지에 정보 넣어주기
					// 2) showView에서 List<ReviewView> 제외
					$('#tab').html(data);
				}
			});
		});
		
		
		
		// 날짜
		
		/*$.datepicker.setDefaults({
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
        });*/
		
		// 추후 fmt 변경할 것
		// 추후 기본값 설정할 것
		
		// 3시 이전, 다음날부터: 둘 다 3시 이후: 7시만 7시 이후: 내일 것부터
	
		
		/*
		$("#datepicker").datepicker({
			format: 'yyyy년 mm월 dd일', // 날짜 형식
	        autoclose: true, // 선택 시 자동으로 닫힘
	        todayHighlight: true, // 오늘 날짜 강조 표시
	        //showOtherMonths: true, // 다른 달 날짜도 표시
	        selectOtherMonths: true, // 다른 달 날짜 선택 가능
	        //daysOfWeekDisabled: '0,2,3,4,5,6', // 월, 수, 목, 금, 토 요일 비활성화
	        startDate: new Date(), // 오늘 이전 날짜 비활성화
	        endDate: '+1M' // 1달 뒤까지 선택 가능
		});
		*/
		
		
		  
		  
		  
		  
		  
		  
	  	function getDates() {
			let validStartDate = new Date("${show.show.validStartDate}") > new Date() ? new Date("${show.show.validStartDate}") : new Date();
		    let validEndDate = new Date("${show.show.validEndDate}");
		    //1일 추가
		    validEndDate.setDate(validEndDate.getDate() + 1);
			validStartDate = formatDate(validStartDate);
			validEndDate = formatDate(validEndDate);
			
			return [validStartDate, validEndDate];
		}  
		  
		  
		  
	  	var korea_date = dayjs(new Date().toLocaleString("en-US", {timeZone: "Asia/Seoul"}));
        var format = "YYYY-MM-DDTHH:mm:ss"; // 포맷 타입
        var koreaNow = korea_date.format(format);  
	  
	  	let selectedDate = null;
		  
		var calendarEl = document.getElementById('calendar');
		var selectedDateEl = document.getElementById('selectedDate');
		var timeBtnEl = document.getElementById('timeBtn');
	    
		let calendar = new FullCalendar.Calendar(calendarEl, {
	    	validRange: {
    	      start: getDates()[0], // 시작 날짜
    	      end: getDates()[1]    // 종료 날짜
	    	},
    	    
	    	
	    	contentHeight: 'auto', // 나중에 캘린더 크기 조정
	    	
	    	customButtons: {
	            myCustomButton: {
	                text: '오늘',
	                click: function() {
	                    // 오늘 버튼 동작
	                }
	            }
	        },
	        
	        headerToolbar: {
	            left: '',
	            center: 'title',
	            right: 'prev,next'
	        },
	        
	        locale: 'ko',
	        
	        
	        
	        selectable: function(info) {
	            var activeDates = getDates();
	            var dateStr = info.startStr.slice(0, 10); // 날짜 부분만 추출
	
	            return activeDates.includes(dateStr); // 활성화된 날짜인지 확인
	        },
	        
	        
	        selectAllow: function(info) {
	        	var diffInDays = moment(info.end).diff(info.start, 'days');
	            return diffInDays <= 1;
	        },
	        
	        select: function(info) {
	        	selectedDate = info.startStr;
	        	
	        	// 선택된 날짜에 맞는 버튼 생성
	            var timeOptions = `
	              <input type="radio" name="selectShowTime" id="15:00:00" value="15:00:00">
	              <label for="15:00:00">15시</label>
	              <input type="radio" name="selectShowTime" id="19:00:00" value="19:00:00">
	              <label for="19:00:00">19시</label>`;

	            // 선택된 날짜 정보 업데이트
	            selectedDateEl.innerHTML = selectedDate;

	            // 시간 선택 옵션 업데이트
	            timeBtnEl.innerHTML = timeOptions;
	            

	            
	            
	        	console.log('Selected:', info.startStr, info.endStr);
	        	
	        }
	    });

	    calendar.render();

	    // 오늘 버튼 비활성화
	    calendar.getOption('customButtons').myCustomButton.disabled = true;
	    calendar.setOption('customButtons', calendar.getOption('customButtons'));
		
	    // 스크롤 숨기기
	    
		var selectedTime = null;
		$(document).on('change', 'input[name="selectShowTime"]', function() {
		    selectedTime = $('input[name="selectShowTime"]:checked').attr('id');
		    console.log(selectedDate);
		    console.log(selectedTime);
		});
		/*
		$('input[name="selectShowTime"]').on('change', function(){
			selectedTime = $('input[name="selectShowTime"]:checked').attr('id');
			console.log(selectedDate);
			console.log(selectedTime);
		});
		*/  
		
		
		
		
		/*$('#datepicker').datepicker();
		
		
		
		var selectedDate = null;
		$('#datepicker').on('change', function() {
			selectedDate = $(this).val();
			$('#selectedDate').text(selectedDate);
			$('#datepicker').setDate(selectedDate);
			
				$('#timeBtn').html(
						'<input type="radio" name="selectShowTime" id="15:00:00">'
						+ '<label for="15:00:00">15시</label>'
						+ '<input type="radio" name="selectShowTime" id="19:00:00">'
						+ '<label for="19:00:00">19시</label>');
		});
		*/
		
		
		// 전송할 데이터 객체
		
		var query = window.location.search;
		var param = new URLSearchParams(query);
		var showId = param.get('showId');
		
		//var selectedTime = $('input[name="selectShowTime"]:checked').attr('id');
		
		let endDate = new Date("${show.show.validEndDate}");
		endDate.setDate(endDate.getDate() - 1);
		var bookingData = {
			showId: showId
			, selectedDate: selectedDate
			, selectedTime: selectedTime
			, validStartDate: new Date("${show.show.validStartDate}") > new Date() ? new Date("${show.show.validStartDate}") : new Date()
			, validEndDate: endDate
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
			console.log(selectedDate);
			console.log(selectedTime);
			console.log(bookingData.selectedDate);
			console.log(bookingData.selectedTime);
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