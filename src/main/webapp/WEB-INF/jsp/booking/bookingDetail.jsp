<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="border">
	<div class="text-center w-100 my-3"><b>${booking.user.name}님의 예매내역서</b></div>
	<div class="d-flex justify-content-center">
		<div>
			<img src="${booking.showView.show.imagePath}" alt="공연이미지" width="200px">
			<button class="d-block btn btn-info my-3 w-100">
				<a href="#">달력에 등록</a>
			</button>
		</div>
		<div class="table-box">
			<ul>
				<li class="book-box border">
					<div class="col-2 book-content">
						<strong>예매자</strong>
					</div>
					<div class="col-10 book-content">
						<span>${booking.user.name}</span>
					</div>
				</li>
				<li class="book-box border">
					<div class="col-2 book-content">
						<strong>예약번호</strong>
					</div>
					<div class="col-10 book-content">
						<span>${booking.booking.bookingNumber}</span>
					</div>
				</li>
				<li class="book-box border">
					<div class="col-2 book-content">
						<strong>이용일</strong>
					</div>
					<div class="col-10 book-content">
						<span>
						<fmt:formatDate value="${booking.booking.showDate}" pattern="yyyy년 MM월 dd일" />
						${booking.booking.showTime}</span>
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
						<span>${booking.showView.theater.name}</span>
						<%-- 추후 제작<button class="btn btn-info h-75 d-flex align-items-center ml-3">
							<a href="#">지도</a>
						</button>
						 --%>
					</div>
				</li>
				<li class="book-box border">
					<div class="col-2 book-content">
						<strong>좌석</strong>
					</div>
					<div class="col-10 book-content">
						<span>
							${booking.booking.seat}
						</span>
						<%-- 추후 제작
						<button class="btn btn-info h-75 d-flex align-items-center ml-3">
							<a href="#">위치</a>
						</button> --%>
					</div>
				</li>
				
					
			</ul>
		</div>
		
	</div>
</div>




<div class="border mt-5">
	<div class="text-center w-100 my-3"><b>${booking.user.name}님의 결제내역서</b></div>
	
		<div class="pay-box">
			<ul class="w-100 d-flex justify-content-center">
				<div>
					<li class="book-box border">
						<div class="col-2 book-content">
							<strong>결제일</strong>
						</div>
						<div class="col-10 book-content">
							<span>
								<fmt:formatDate value="${pay.payDate}" pattern="yyyy년 MM월 dd일 HH:mm:ss" />
							</span>
						</div>
					</li>
					<li class="book-box border">
						<div class="col-2 book-content">
							<strong>예매상태</strong>
						</div>
						<div class="col-10 book-content">
							<span>
								<c:if test="${pay.isValid > 0}">
									예약완료
								</c:if>
								<c:if test="${pay.isValid <= 0}">
									예약취소
								</c:if>
							</span>
						</div>
					</li>
					<li class="book-box border">
						<div class="col-2 book-content">
							<strong>결제수단</strong>
						</div>
						<div class="col-10 book-content">
							<span>${pay.payment}카드</span>
						</div>
					</li>
					<li class="book-box border">
						<div class="col-2 book-content">
							<strong>결제금액</strong>
						</div>
						<div class="col-10 book-content">
							<span>${pay.discountPrice}원</span>
						</div>
					</li>
					<li class="book-box border">
						<div class="col-2 book-content">
							<strong>환불예상액</strong>
						</div>
						<div class="col-10 book-content">
							<span>
								<c:if test="${pay.isValid > 0}">
									환불가능금액은 ${pay.discountPrice}원 입니다.
								</c:if>
								<c:if test="${pay.isValid <= 0}">
									${pay.discountPrice}원이 환불됩니다.
								</c:if>
							</span>
						</div>
					</li>
					<%-- 추후 제작
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
					 --%>
				</div>
			</ul>
		</div>
		
		<div class="w-75 d-flex justify-content-end my-4">
			<button type="button" id="cancelBtn" class="btn btn-danger" data-booking-id="${booking.booking.id}"
			data-pay-id="${pay.id}">
				예매취소
			</button>
			<button class="btn btn-info ml-3">
				<!-- session 들고 자신의 예매목록으로 가야 한다. 전체 처리? -->
				<!-- 로그인 후 redirect? -->
				<a href="/book/book_list_view">예매목록</a>
			</button>
		</div>
		
	</div>
</div>

<script>
	$(document).ready(function() {
		$("#cancelBtn").on('click', function() {
			// 정말 취소하시겠습니까?
			let bookingId = $(this).data('booking-id');
			let payId = $(this).data('pay-id');
			
			
			$.ajax({
				type: "put"
				, url: "/book/update_status"
				, data: {"bookingId":bookingId, "payId":payId}
				, success:function(data) {
					if (data.code == 1) {
						alert("예약이 취소되었습니다.");
						location.href = "/book/book_list_view";
					} else {
						alert(data.errorMessage);
					}
				}
				, error:function(request, status, error) {
					alert("예약 취소에 실패했습니다.");
				}
			});
		});
	});
	
</script>