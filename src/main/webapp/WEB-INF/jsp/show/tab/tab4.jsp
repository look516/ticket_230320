<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="tab-content w-100">
	<div>
		<div class="text-center bg-secondary py-3">
			<h2>${show.show.genre} &lt; ${show.show.name} &gt; 리뷰</h2>
		</div>
		<div class="my-3 d-flex justify-content-between">
			<h2>
				<c:forEach begin="1" end="${average}">
					★
				</c:forEach>
			</h2>
			<h2>${average}</h2>
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
							<span>
								<fmt:parseDate value="${review.review.createdAt}" pattern="yyyy-MM-dd" var="createdAt" />
								<fmt:formatDate value="${createdAt}" pattern="yyyy년 MM월 dd일" />
							</span>
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