<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div class="m-5">
		<div class="d-flex justify-content-center m-5">
		<div>
			<form id="loginForm" action="/user/sign_in" method="post">
				<!-- 아이디 -->
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text">ID</span>
					</div>
					<input type="text" class="form-control" name="loginId">
				</div>
				
				<!-- 패스워드 -->
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text">PW</span>
					</div>
					<input type="password" class="form-control" name="password">
				</div>

				<!-- 로그인 -->
				<input type="submit" class="btn btn-block btn-info" value="로그인">
			</form>
			
			
			
			<%-- 체크박스 - 추후 checked 이용 --%>
			<div class="d-flex justify-content-around mt-3">
				<div>
					<input type="checkbox" name="autoLogin" id="autoLogin" value="autoLogin">
					<label for="autoLogin"><span>자동로그인</span></label>
				</div>
				<div>
					<input type="checkbox" name="saveId" id="saveId" value="saveId">
					 <label for="saveId"><span>아이디 저장</span></label>
				</div>
			</div>
			
			<div class="mt-4">
				<!-- 위치 고정? -->
				<a class="btn btn-block btn-warning" href="#">카카오 로그인</a>
			</div>
			
			<div class="d-flex justify-content-around pr-2 mt-3">
				<a class="user-link" href="">아이디 찾기</a>
				<a class="user-link" href="">비밀번호 찾기</a>
				<a class="user-link" href="/user/sign_up_view">회원가입</a>
			</div>
			
		</div>	
		</div>
	</div>
</div>
