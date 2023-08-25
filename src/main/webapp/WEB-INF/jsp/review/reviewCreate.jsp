<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center border">
	<div>
		<div class="text-center my-3">
			<h3>공연관람후기</h3>
		</div>
		
		<div class="pay-box">
			<form id="reviewCreateForm" method="post" action="/review/create">
			<ul class="w-100 d-flex justify-content-center">
				<div>
					<li class="book-box border">
						<div class="col-2 book-content">
							<strong>상품명</strong>
						</div>
						<div class="col-10 book-content">
							<input type="text" name="showName" id="showName" class="form-control"
							value="${show.name}" disabled />
							<input type="hidden" id="showId" name="showId" value="${show.id}">
						</div>
					</li>
					<li class="book-box border">
						<div class="col-2 book-content">
							<strong>관람일시</strong>
						</div>
						<div class="col-10 book-content">
							<input type="text" id="showDate" name="showDate" class="form-control">
						</div>
					</li>
					<li class="book-box border">
						<div class="col-2 book-content">
							<strong>관람시간</strong>
						</div>
						<div class="col-10 book-content">
							<select id="showTime" name="showTime">  
							    <option value="">시간선택</option>
							    <option value="15:00:00">15시</option>
							    <option value="19:00:00">19시</option>
							</select>
						</div>
					</li>
					<li class="book-box border">
						<div class="col-2 book-content">
							<strong>제목</strong>
						</div>
						<div class="col-10 book-content">
							<input type="text" id="subject" name="subject" class="form-control">
						</div>
					</li>
					<li class="book-box border">
						<div class="col-2 book-content">
							<strong>별점</strong>
						</div>
						<div class="col-10 book-content">
							<input type="radio" name="point" value="1" id="point1" />
							<label for="point1" class="point-radio">1점</label>
							<input type="radio" name="point" value="2" id="point2" />
							<label for="point2" class="point-radio">2점</label>
							<input type="radio" name="point" value="3" id="point3" />
							<label for="point3" class="point-radio">3점</label>
							<input type="radio" name="point" value="4" id="point4" />
							<label for="point4" class="point-radio">4점</label>
							<input type="radio" name="point" value="5" id="point5" checked />
							<label for="point5" class="point-radio">5점</label>
							
						</div>
					</li>
					<li class="book-box border">
						<div class="col-2 book-content">
							<strong>내용</strong>
						</div>
						<div class="col-10 book-content">
							<textarea id="content" name="content" class="w-100" rows="10"></textarea>
						</div>
					</li>
				</div>
			</ul>
			
		</div>
		
		<div class="d-flex justify-content-between mb-3">
			<div>
				<a href="#" class="btn btn-secondary ml-5">목록</a>
			</div>
			<div>
				<button type="submit" id="saveBtn" class="btn btn-info mr-2">확인</button>
				<a href="/show/show_detail_view?showId=${show.id}" class="btn btn-secondary">취소</a>
			</div>
		</div>
		</form>	
	</div>
</div>



<script>
	$(document).ready(function() {
		$('#showDate').datepicker();
		
		
		// /review/create
		// showId - showName으로 변환해서 가져오기 / controller에서 직접 넣기
		// userId - session에 있는 값 controller에서 직접 넣기
		// subject, content, point - form 필수
		// showDate, showTime - form 비필수
		$('#reviewCreateForm').on('submit', function(e) {
			
			//alert("sss");
			
			e.preventDefault(); // submit 중단
			
			let subject = $('#subject').val().trim();
			let content = $('#content').val();
			let showId = $('#showId').val();
			//let point = $('input[name=point]:checked').val();
			//let userId = [[ ${userId} ]];
			//let showDate = $("#showDate").datepicker("getDate");
			//let showTime = $("#showTime option:selected").val();
			
			
			// validation check
			if (!subject) {
				alert("제목을 입력하세요");
				return;
			}
			
			if (!content) {
				alert("내용을 입력하세요");
				return;
			}
			
			// AJAX 통신 - controller가 JSON 리턴
			let url = $(this).attr('action');
			//console.log(url);
			let params = $(this).serialize(); // 폼태그 name으로 값 뽑아냄 (key-value)
			//console.log(params);
			
			$.post(url, params) // request
			.done(function(data) {
				// response
				if (data.code == 1) {
					// 성공
					alert("리뷰가 저장되었습니다.");
					location.href = "/show/show_detail_view?showId=" + showId;
				} else {
					// 로직 실패
					alert(data.errorMessage);
				}
			});
			
			
			
		});
	});
</script>