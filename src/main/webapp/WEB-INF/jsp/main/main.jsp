<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 메인 배너 -->
<section class="px-1 my-2">
	<a href="https://www.naver.com">
		<img src="/static/images/banner1.png" alt="banner" id="banner" width="100%">
	</a>
</section>


<!-- 아래 컨텐츠 (추후 구성) -->

<script>
	<%-- 실제로는 랜덤으로 내려주는 이미지를 받아와서 뿌리면 된다. --%>
	$(document).ready(function(){
		let images =["/static/images/banner1.png", "/static/images/banner2.png", "/static/images/banner3.png"];
        let count = 0;

        setInterval(function() {
            count++;
            if (images.length <= count) {
                count = 0;
            }
            $("#banner").attr("src", images[count]);
        }, 3000);

	});
</script>