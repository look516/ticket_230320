<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<%-- 공연 정보 --%>
	<div>
		<%-- 제목 --%>
		<h1 class="pl-4 mt-3">뮤지컬 &lt; ${show.name} &gt;</h1>

		<div class="d-flex">
			<%-- 이미지 --%>
			<div class="ml-3">
				<img src="${show.imagePath}" alt="공연 이미지" width="200px">
			</div>
			
			<%-- 간략정보 --%>
			<div>
				<ul>
					<li class="show-info d-flex">
						<strong>장소</strong>
						<div>
							<!-- <a class="infoBtn" data-popup="info-place" role="button" href="#"> -->
							<p><a class="infoBtn" href="#">
								${show.theaterId}
								<i>(자세히)</i>
							</a></p>
						</div>
					</li>
					<li class="show-info d-flex">
						<strong>공연기간</strong>
						<div>
							<p>${show.startDate} ~ ${show.endDate}</p>
						</div>
					</li>
					<li class="show-info d-flex">
						<strong>공연시간</strong>
						<div>
							<p>${show.time}분</p>
						</div>
					</li>
					<li class="show-info d-flex">
						<strong>관람연령</strong>
						<div>
							<p>${show.age}세 이상</p>
						</div>
					</li>
					
				</ul>
			</div>
			
			
		
			
		</div>
		
		
		<%-- 공연정보 탭영역 --%>
		<%-- d-none으로 1차적으로 실행 후 나중에 template으로 쪼개자 --%>
		
	</div>
	
	<%-- 예매창 --%>
	<div></div>
</div>


