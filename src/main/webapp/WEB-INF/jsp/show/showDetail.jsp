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
		
		<%-- 공연정보 탭영역 --%>
		<%-- d-none으로 1차적으로 실행 후 나중에 template으로 쪼개자 --%>
		<div class="mt-3 ml-3">
			<ul class="tab-nav nav nav-fill">
				<li class="nav-item"><a href="#tab1" class="btn btn-secondary nav-link tab-menu">공연정보</a></li>
				<li class="nav-item"><a href="#tab2" class="btn nav-link tab-menu">캐스팅정보</a></li>
				<li class="nav-item"><a href="#tab3" class="btn nav-link tab-menu">판매정보</a></li>
				<li class="nav-item"><a href="#tab4" class="btn nav-link tab-menu">관람후기</a></li>
				<li class="nav-item"><a href="#tab5" class="btn nav-link tab-menu">기대평</a></li>
				<li class="nav-item"><a href="#tab6" class="btn nav-link tab-menu">Q&A</a></li>
			</ul>
			
			<a href="#" id="tab1" class="tab-content-link"><div class="tab-content w-100"><img src="/static/images/show1.gif" class="w-100" alt="공연 정보"></div></a>
			<a href="#" id="tab2" class="tab-content-link d-none"><div class="tab-content w-100"><img src="/static/images/show2.gif" class="w-100" alt="캐스팅 정보"></div></a>
			<a href="#" id="tab3" class="tab-content-link d-none"><div class="tab-content w-100"><img src="/static/images/show3.gif" class="w-100" alt="판매 정보"></div></a>
			
			
			
			
			
			<div id="tab4" class="tab-content-link d-none">
				<div class="tab-content w-100">
					<div>
						<div class="text-center bg-secondary py-3">
							<h2>${show.show.genre} &lt; ${show.show.name} &gt; 리뷰</h2>
						</div>
						<div class="my-3 d-flex justify-content-between">
							<h2>★★★★★</h2>
							<h2>9.9</h2>
						</div>
						<div class="d-flex justify-content-end">
							<a href="/review/review_create_view?showId=${show.show.id}" class="btn btn-info" id="reviewBtn">후기작성</a>
						</div>
						<div class="border mt-2 p-2">
							<div class="d-flex justify-content-end mb-2">
								<a href="#" class="review-filter">최신글순</a>
								<a href="#" class="review-filter ml-3">평점순</a>
							</div>
							<%-- 반복문 시작 --%>
							<c:forEach var="review" items="${show.reviewList}">
								<div class="border p-3 bg-warning">
									<div class="d-flex justify-content-between">
										<h5>
											<c:forEach begin="1" end="${review.review.point}">
												★
											</c:forEach>
										</h5>
										<div>
											<span class="mr-3">${review.user.name}</span>
											<span><fmt:formatDate value="${Date.from((review.review.createdAt).toInstant())}" pattern="yyyy년 MM월 dd일" /></span>
										</div>
									</div>
									
									<div class="mt-2">
										<h5>${review.review.subject}</h5>
										<span>${review.review.content}</span>
										<%-- 추후 더보기 기능 추가 예정 --%>
										<!-- <a href="">더보기</a> -->
									</div>
								
								</div>
							</c:forEach>
							
						</div>
					</div>
				</div>
			</div>
			
			
			
			<a href="#" id="tab5" class="tab-content-link d-none"><div class="tab-content w-100"><img src="/static/images/show2.gif" class="w-100" alt="기대평"></div></a>
			<a href="#" id="tab6" class="tab-content-link d-none"><div class="tab-content w-100"><img src="/static/images/show3.gif" class="w-100" alt="Q&A"></div></a>
		</div>
	</div>
	
	<%-- 예매창 --%>
	<div class="ml-3 book-area col-4 d-flex justify-content-center">
		<div class="text-center">
			<h3>관람일</h3>
			<div id="datepicker"></div>
			<div class="mt-3" id="selectedDate"></div>
			<%-- 로그인 처리 / 공연별 분기 --%>
			<a href="/book/book_page_view" onclick="window.open(this.href, '_blank', 'width=800, height=500'); return false;"
			class="btn btn-info my-2 col-12" ref="noreferrer noopener">예매하기</a>
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
			$('.tab-content-link').addClass('d-none');
			let tabId = $(this).attr('href');
			$(tabId).removeClass('d-none');
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
	        , minDate: 0
        });
		
		let selectedDate = null;
		
		$('#datepicker').datepicker();
		
		$('#datepicker').on('change', function() {
			selectedDate = $(this).val();
			$('#selectedDate').text(selectedDate);
		});
	});

</script>