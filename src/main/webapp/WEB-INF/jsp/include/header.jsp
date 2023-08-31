<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<div class="d-flex justify-content-end wing">
	<div id="wingBox" class="wing-menu d-flex justify-content-center">
		<!-- 윙 배너 여닫기 -->
		<!-- 추후 구현 -->
		<a href="#" id="closeWing" class="<%--close-wing--%> wing-main-font pt-1">&gt;</a>

		<div id="wingBanner">
			<!-- 윙 배너 - 로그인 영역 (비로그인) -->
			<c:if test="${empty userId}">
				<div>
					<div class="text-center wing-main-font">LOGIN</div>
					<div class="text-center">환영합니다.<br>로그인해주세요.</div>
					<div class="d-flex justify-content-center mt-3">
						<a type="button" class="btn btn-info" href="/user/sign_in_view">로그인</a>
					</div>
				</div>
			</c:if>
			
			<!-- 윙 배너 - 로그인 영역 (로그인) -->
			<c:if test="${not empty userId}">
				<div>
					<div class="text-center mt-4">${userName}님<br>환영합니다.</div>
					<div class="d-flex justify-content-center mt-3">
						<a type="button" class="btn btn-warning" href="/book/book_list_view">나의예약</a>
					</div>
				</div>
			</c:if>
			
			<div><hr></div>
		
			<!-- 윙 배너 - 공연목록 / 리스트와 페이징 추후 추가 -->
			<div>
				<div class="text-center">최근 본 공연</div>
				<%--<div>${showIdList}</div> --%>
				<c:forEach var="showImage" items="${recentShowImageList}">
					<a href="#"><div class="mt-3 d-flex justify-content-center"><img src="${showImage}" width="80px" alt="윙 배너 공연 썸네일"></div></a>
				</c:forEach>
				<%--${recentShowImageList}
				<a href="#"><div class="mt-3 d-flex justify-content-center"><img src="/static/images/show1.gif" width="90px" alt="윙 배너 공연 썸네일"></div></a>
				<a href="#"><div class="mt-3 d-flex justify-content-center"><img src="/static/images/show2.gif" width="90px" alt="윙 배너 공연 썸네일"></div></a>
				 --%>
			</div>
		</div>
		
	</div>
</div>

<div class="container">
<!-- 상단 우측 메뉴 -->
<div class="d-flex justify-content-end pr-4 pt-2">
	<nav>
		<ul class="nav nav-fill">
			<c:if test="${empty userId}">
				<li class="nav-item"><a class="nav-link top-menu" href="/user/sign_in_view">로그인</a></li>
            </c:if>
            <c:if test="${not empty userId}">
            	<li class="nav-item"><a class="nav-link top-menu" href="/user/sign_out">로그아웃</a></li>
            </c:if>
            <li class="nav-item"><a class="nav-link top-menu" href="/user/sign_up_view">회원가입</a></li>
            <li class="nav-item"><a class="nav-link top-menu" href="/book/book_list_view">마이페이지</a></li>
            <li class="nav-item"><a class="nav-link top-menu" href="#">고객센터</a></li>                    
		</ul>
	</nav>
</div>

<div class="d-flex justify-content-center w-75">
	<!-- 로고 -->
	<a class="col-4 d-flex align-items-center justify-content-center" href="/main/main_view"><img src="/static/images/logo.png" alt="로고" width=200px></a>
	
	<!-- 창이 줄어들 때 로고와 검색 영역 안 겹치게 하자 -->
	
	<!-- 검색 영역 -->
	<div class="col-8 input-group my-3">
  		<input type="text" class="form-control" placeholder="검색어를 입력하세요">
  		<div class="input-group-append">
    		<button class="btn btn-outline-secondary" type="button">검색</button>
  		</div>
	</div>
</div>

<!-- 좌측 하단 메뉴 -->
<div>
	<nav class="ml-2">
		<div class="col-4">
		<ul class="nav nav-fill">
			<li class="nav-item"><a class="nav-link middle-menu" href="/show/show_list_view?genre=전체">전체</a></li>
            <li class="nav-item"><a class="nav-link middle-menu" href="/show/show_list_view?genre=뮤지컬">뮤지컬</a></li>
            <li class="nav-item"><a class="nav-link middle-menu" href="/show/show_list_view?genre=대중음악">대중음악</a></li>
            <li class="nav-item"><a class="nav-link middle-menu" href="/show/show_list_view?genre=연극">연극</a></li>
            <li class="nav-item"><a class="nav-link middle-menu" href="#">기타</a></li><%-- 메뉴nav와 기타nav완성 필요 --%>
            <li class="nav-item"><a class="nav-link middle-menu" href="#">리뷰</a></li>                    
		</ul>
		</div>
	</nav>
</div>

<script>
	$(document).ready(function() {
		// 윙 여닫기
		$('#closeWing').on('click', function(e) {
			e.preventDefault(); // 위치 고정
			if ($('#wingBanner').hasClass('d-none')) {
				// 누르면 나타남
				$('#wingBanner').removeClass('d-none');
				$('#wingBox').css("position", "fixed");
				$('#wingBox').removeClass('d-none');
				$('#wingBox').css("right", "0");
				$('#wingBox').css("top", "50%"); // 중앙으로 올리기
                $('#wingBox').css("transform", "translateY(-50%)");
				$('.wing').css('width', "150px");
				//$('#closeWing').css('height', "80px");
			} else {
				// 누르면 사라짐
				$('#wingBanner').addClass('d-none')
				$('#wingBox').addClass('d-none');
				$('#wingBox').css("right", "-150px");
				$('#wingBox').css("top", "-100px");
				$('#wingBox').css("right", "0"); // 오른쪽으로 숨기기
                $('#wingBox').css("top", "50%"); // 중앙으로 올리기
                $('#wingBox').css("transform", "translateY(-50%)");
				$('#wingBox').css("position", "static");
				$('.wing').css('width', "30px");
				$('.wing').css('top', "6%");
			}
		});
	});
</script>