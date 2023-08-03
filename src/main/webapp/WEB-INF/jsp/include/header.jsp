<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<div class="d-flex justify-content-end wing">
	<div class="wing-menu d-flex justify-content-center">
		<!-- 윙 배너 여닫기 -->
		<!-- 추후 구현 -->
		<a href="#" class="close-wing wing-main-font pt-1">&gt;</a>

		<div>
			<!-- 윙 배너 - 로그인 영역 (비로그인) -->
			<div>
				<div class="text-center wing-main-font">LOGIN</div>
				<div class="text-center">환영합니다.<br>로그인해주세요.</div>
				<div class="d-flex justify-content-center mt-3">
					<a type="button" class="btn btn-info">로그인</a>
				</div>
			</div>
			
			<!-- 윙 배너 - 로그인 영역 (로그인) -->
			<div class="d-none">
				<div class="text-center mt-4">김바다님<br>환영합니다.</div>
				<div class="d-flex justify-content-center mt-3">
					<a type="button" class="btn btn-warning">나의예약</a>
				</div>
			</div>
			
			<div><hr></div>
		
			<!-- 윙 배너 - 공연목록 / 리스트와 페이징 추후 추가 -->
			<div>
				<div class="text-center">최근 본 공연</div>
				<a href="#"><div class="mt-3 d-flex justify-content-center"><img src="/static/images/show1.gif" width="90px" alt="윙 배너 공연 썸네일"></div></a>
				<a href="#"><div class="mt-3 d-flex justify-content-center"><img src="/static/images/show2.gif" width="90px" alt="윙 배너 공연 썸네일"></div></a>
			</div>
		</div>
	</div>
</div>

<div class="container">
<!-- 상단 우측 메뉴 -->
<div class="d-flex justify-content-end pr-4 pt-2">
	<nav>
		<ul class="nav nav-fill">
			<li class="nav-item"><a class="nav-link top-menu" href="#">로그인</a></li>
            <li class="nav-item"><a class="nav-link top-menu" href="#">회원가입</a></li>
            <li class="nav-item"><a class="nav-link top-menu" href="#">마이페이지</a></li>
            <li class="nav-item"><a class="nav-link top-menu" href="#">고객센터</a></li>                    
		</ul>
	</nav>
</div>

<div class="d-flex justify-content-center w-75">
	<!-- 로고 -->
	<div class="col-4 d-flex align-items-center justify-content-center"><img src="/static/images/logo.png" alt="로고" width=200px></div>
	
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
			<li class="nav-item"><a class="nav-link middle-menu" href="#">메뉴</a></li>
            <li class="nav-item"><a class="nav-link middle-menu" href="#">뮤지컬</a></li>
            <li class="nav-item"><a class="nav-link middle-menu" href="#">콘서트</a></li>
            <li class="nav-item"><a class="nav-link middle-menu" href="#">연극</a></li>
            <li class="nav-item"><a class="nav-link middle-menu" href="#">기타</a></li>
            <li class="nav-item"><a class="nav-link middle-menu" href="#">리뷰</a></li>                    
		</ul>
		</div>
	</nav>
</div>