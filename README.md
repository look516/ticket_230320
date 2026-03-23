 # 엔터파크 티켓 (Enterpark Ticket)

  공연 예매 서비스 웹 애플리케이션입니다.
  기존 Spring MVC + JSP 구조에서 React SPA로 마이그레이션한 프로젝트입니다.

  ---

  ## 기술 스택

  | 구분 | 기술 |
  |------|------|
  | UI | React 18, Bootstrap 4 |
  | 라우팅 | React Router v6 |
  | 상태 관리 | React Context API (useState, useEffect) |
  | 캘린더 | FullCalendar v5 (@fullcalendar/react) |
  | 빌드 | Vite |
  | 백엔드 | Spring Boot (기존 유지) |

  ---

  ## 설치 및 실행

  ### 의존성 설치

  \`\`\`bash
  npm install
  \`\`\`

  ### 개발 서버 실행

  \`\`\`bash
  npm run dev
  \`\`\`

  ### 프로덕션 빌드

  \`\`\`bash
  npm run build
  \`\`\`

  ---

  ## 주요 패키지

  \`\`\`bash
  npm install react-router-dom bootstrap
  npm install @fullcalendar/react @fullcalendar/daygrid
  @fullcalendar/interaction
  \`\`\`

  ---

  ## 폴더 구조

  \`\`\`
  src/
  ├── main.jsx                     # 앱 진입점
  ├── App.jsx                      # 라우터 설정
  ├── index.css                    # 전역 스타일 (기존 style.css 복사)
  │
  ├── context/
  │   └── AuthContext.jsx          # 로그인 세션 전역 상태
  │
  ├── components/
  │   ├── layout/
  │   │   ├── Layout.jsx           # 기본 레이아웃 (헤더 + 푸터)
  │   │   ├── LayoutSignUp.jsx     # 회원가입 레이아웃
  │   │   └── LayoutBooking.jsx    # 예매 레이아웃 (팝업용)
  │   ├── common/
  │   │   ├── Header.jsx           # 공통 헤더 (윙배너, 검색, 메뉴)
  │   │   └── Footer.jsx           # 공통 푸터
  │   └── show/
  │       └── tabs/
  │           ├── Tab1ShowInfo.jsx  # 공연정보
  │           ├── Tab2Casting.jsx  # 캐스팅정보
  │           ├── Tab3SaleInfo.jsx # 판매정보
  │           ├── Tab4Reviews.jsx  # 관람후기
  │           ├── Tab5Expected.jsx # 기대평
  │           └── Tab6QnA.jsx      # Q&A
  │
  └── pages/
      ├── MainPage.jsx             # 메인 (배너 슬라이드)
      ├── SignIn.jsx               # 로그인
      ├── SignUp.jsx               # 회원가입
      ├── ShowList.jsx             # 공연 목록 + 페이징
      ├── ShowDetail.jsx           # 공연 상세 + 캘린더 + 탭
      ├── BookingPage.jsx          # 좌석 선택 (팝업)
      ├── BookingList.jsx          # 나의 예매 목록
      ├── BookingPay.jsx           # 결제
      ├── BookingDetail.jsx        # 예매 상세 + 취소
      ├── BookingDone.jsx          # 예매 완료
      └── ReviewCreate.jsx         # 리뷰 작성

  public/
  └── static/
      ├── images/                  # 기존 이미지 파일 복사
      │   ├── logo.png
      │   ├── banner1.png
      │   ├── banner2.png
      │   └── banner3.png
      └── css/
          └── style.css            # 기존 커스텀 CSS 복사
  \`\`\`

  ---

  ## 라우트 구조

  \`\`\`
  /  →  /main/main_view (리다이렉트)

  [기본 레이아웃] 헤더 + 푸터
  ├── /main/main_view
  ├── /user/sign_in_view
  ├── /show/show_list_view
  ├── /show/show_detail_view
  ├── /review/review_create_view   🔒 로그인 필요
  ├── /book/book_list_view         🔒 로그인 필요
  └── /book/book_detail_view       🔒 로그인 필요

  [회원가입 레이아웃] 로고 헤더 + 푸터
  └── /user/sign_up_view

  [예매 레이아웃] 로고 헤더만
  ├── /book/book_page_view         🔒 로그인 필요 (팝업)
  ├── /book/pay_view               🔒 로그인 필요
  └── /book/booking_done_view      🔒 로그인 필요
  \`\`\`

  ---

  ## 백엔드 API 변경 사항

  React 전환에 따라 아래 엔드포인트는 **JSON 응답**을 반환하도록 수정이
  필요합니다.

  ### 신규 추가 필요

  | 메서드 | URL | 설명 | 응답 예시 |
  |--------|-----|------|-----------|
  | GET | `/api/auth/me` | 현재 세션 사용자 정보 | `{ userId, userName,
  recentShowImageList }` |
  | GET | `/api/shows` | 공연 목록 | `{ showList, currentPage, totalPages,
  genre, search }` |
  | GET | `/api/shows/:id` | 공연 상세 | `{ show: { show, theater, reviewList
   }, average }` |
  | GET | `/api/bookings` | 나의 예매 목록 | `{ bookingViewList }` |
  | GET | `/api/bookings/:id` | 예매 상세 | `{ booking, pay }` |

  ### 기존 유지 가능 (JSON 응답이면)

  | 메서드 | URL | 설명 |
  |--------|-----|------|
  | POST | `/user/sign_in` | 로그인 → `{ result: "성공" }` |
  | POST | `/user/sign_up` | 회원가입 → `{ code, errorMessage }` |
  | GET | `/user/is_duplicated_id` | 아이디 중복확인 → `{ isDuplicatedId }` |
  | GET | `/book/booking_seat` | 예약된 좌석 조회 → `[seatNum, ...]` |
  | POST | `/book/booking` | 좌석 선택 → `{ code, forwardUrl, showName,
  seatGrade }` |
  | POST | `/book/pay` | 결제 → `{ code, errorMessage }` |
  | PUT | `/book/update_status` | 예매 취소 → `{ code, errorMessage }` |
  | POST | `/review/create` | 리뷰 작성 → `{ code, errorMessage }` |
  | DELETE | `/review/delete` | 리뷰 삭제 → `{ code, errorMessage }` |

  ---

  ## 정적 파일 마이그레이션

  기존 `/static/` 경로를 그대로 유지하려면 파일을 `public/static/` 에
  복사합니다.

  \`\`\`bash
  cp -r src/main/webapp/static/images public/static/images
  cp -r src/main/webapp/static/css    public/static/css
  \`\`\`

  ---

  ## 주요 변경 사항 (JSP → React)

  | JSP | React |
  |-----|-------|
  | JSTL `<c:if>`, `<c:forEach>` | 조건부 렌더링, `Array.map()` |
  | jQuery AJAX (`$.ajax`, `$.post`) | `fetch` + `async/await` |
  | jQuery DOM 조작 | `useState` |
  | `setInterval` + jQuery | `useEffect` + `useState` |
  | `<jsp:include page="...">` | React Router `<Outlet />` |
  | 서버 세션 (`${userId}`) | `AuthContext` |
  | FullCalendar CDN | `@fullcalendar/react` npm 패키지 |
  | jQuery UI datepicker | `<input type="date">` |
  | `location.href` | `useNavigate()` |
  | JSTL `<fmt:formatDate>` | `Intl.DateTimeFormat` |
