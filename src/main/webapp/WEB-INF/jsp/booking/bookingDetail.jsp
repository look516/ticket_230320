<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="border">
	<div class="text-center w-100 my-3"><b>000님의 예매내역서</b></div>
	<div class="d-flex justify-content-center">
		<div>
			<img src="/static/images/show1.gif" alt="공연이미지" width="200px">
			<button class="d-block btn btn-info my-3 w-100">
				<a href="">달력에 등록</a>
			</button>
		</div>
		<div class="table-box">
			<ul>
				<li class="book-box border">
					<div class="col-2 book-content">
						<strong>예매자</strong>
					</div>
					<div class="col-10 book-content">
						<span>000</span>
					</div>
				</li>
				<li class="book-box border">
					<div class="col-2 book-content">
						<strong>예약번호</strong>
					</div>
					<div class="col-10 book-content">
						<span>12345678</span>
					</div>
				</li>
				<li class="book-box border">
					<div class="col-2 book-content">
						<strong>이용일</strong>
					</div>
					<div class="col-10 book-content">
						<span>2023-09-01 20시 00분</span>
					</div>
				</li>
				<li class="book-box border">
					<div class="col-2 book-content">
						<strong>출연진</strong>
					</div>
					<div class="col-10 book-content">
						<span>ㄱㄱㄱ, ㄴㄴㄴ, ㄷㄷㄷ 등</span>
					</div>
				</li>
				<li class="book-box border">
					<div class="col-2 book-content">
						<strong>장소</strong>
					</div>
					<div class="col-10 book-content">
						<span>동숭아트센터 1관</span>
						<button class="btn btn-info h-75 d-flex align-items-center ml-3">
							<a href="#">지도</a>
						</button>
					</div>
				</li>
				<li class="book-box border">
					<div class="col-2 book-content">
						<strong>좌석</strong>
					</div>
					<div class="col-10 book-content">
						<span>1층 a열 13</span>
						<button class="btn btn-info h-75 d-flex align-items-center ml-3">
							<a href="#">위치</a>
						</button>
					</div>
				</li>
				
					
			</ul>
		</div>
		
	</div>
</div>




<div class="border mt-5">
	<div class="text-center w-100 my-3"><b>000님의 결제내역서</b></div>
	
		<div class="pay-box">
			<ul class="w-100 d-flex justify-content-center">
				<div>
					<li class="book-box border">
						<div class="col-2 book-content">
							<strong>예매일</strong>
						</div>
						<div class="col-10 book-content">
							<span>2023-07-20 10시 00분</span>
						</div>
					</li>
					<li class="book-box border">
						<div class="col-2 book-content">
							<strong>예매상태</strong>
						</div>
						<div class="col-10 book-content">
							<span>예매완료</span>
						</div>
					</li>
					<li class="book-box border">
						<div class="col-2 book-content">
							<strong>결제수단</strong>
						</div>
						<div class="col-10 book-content">
							<span>국민카드</span>
						</div>
					</li>
					<li class="book-box border">
						<div class="col-2 book-content">
							<strong>결제금액</strong>
						</div>
						<div class="col-10 book-content">
							<span>62600원</span>
						</div>
					</li>
					<li class="book-box border">
						<div class="col-2 book-content">
							<strong>환불예상액</strong>
						</div>
						<div class="col-10 book-content">
							<span>58750원</span>
						</div>
					</li>
					<li class="book-box border">
						<div class="col-2 book-content">
							<strong>환불수칙</strong>
						</div>
						<div class="col-10 book-content">
							<button class="btn btn-info h-75 d-flex align-items-center">
								<a href="#">자세히 보기</a>
							</button>
						</div>
					</li>
				</div>
			</ul>
		</div>
		
		<div class="w-75 d-flex justify-content-end my-4">
			<button class="btn btn-danger">
				<a href="#">예매취소</a>
			</button>
			<button class="btn btn-info ml-3">
				<!-- session 들고 자신의 예매목록으로 가야 한다. 전체 처리? -->
				<!-- 로그인 후 redirect? -->
				<a href="/book/book_list_view">예매목록</a>
			</button>
		</div>
		
	</div>
</div>