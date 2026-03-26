 # 온엔터 티켓 (ONenter Ticket)

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

  ```  bash
  npm install
  ```

  ### 개발 서버 실행

  ```  bash
  npm run dev
  ```

  ### 프로덕션 빌드

  ```  bash
  npm run build
  ```

  ---

  ## 주요 패키지

  ```bash
  npm install react-router-dom bootstrap
  npm install @fullcalendar/react @fullcalendar/daygrid
  @fullcalendar/interaction
  ```

  ---

  ## 폴더 구조


  ```
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
  ```