import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function Header({ userId, userName, recentShowImageList = [] }) {
  const [wingOpen, setWingOpen] = useState(true);
  const [searchText, setSearchText] = useState('');
  const navigate = useNavigate();

  const handleWingToggle = (e) => {
    e.preventDefault();
    setWingOpen((prev) => !prev);
  };

  const handleSearch = () => {
    const trimmed = searchText.trim();
    if (trimmed) {
      navigate(`/show/show_list_view?search=${encodeURIComponent(trimmed)}`);
    }
  };

  const handleSearchKeyDown = (e) => {
    if (e.key === 'Enter') handleSearch();
  };

  return (
    <>
      <div className="d-flex justify-content-end wing">
        <div
          id="wingBox"
          className="wing-menu d-flex justify-content-center"
          style={
            wingOpen
              ? { position: 'fixed', right: 0, top: '50%', transform: 'translateY(-50%)' }
              : { position: 'static' }
          }
        >
          <a href="#" id="closeWing" className="wing-main-font pt-1" onClick={handleWingToggle}>
            &gt;
          </a>

          {wingOpen && (
            <div id="wingBanner">
              {!userId && (
                <div>
                  <div className="text-center wing-main-font">LOGIN</div>
                  <div className="text-center">환영합니다.<br />로그인해주세요.</div>
                  <div className="d-flex justify-content-center mt-3">
                    <a type="button" className="btn btn-info" href="/user/sign_in_view">로그인</a>
                  </div>
                </div>
              )}

              {userId && (
                <div>
                  <div className="text-center mt-4">{userName}님<br />환영합니다.</div>
                  <div className="d-flex justify-content-center mt-3">
                    <a type="button" className="btn btn-warning" href="/book/book_list_view">나의예약</a>
                  </div>
                </div>
              )}

              <div><hr /></div>

              <div>
                <div className="text-center">최근 본 공연</div>
                {recentShowImageList.map((showImage, index) => (
                  <a href="#" key={index}>
                    <div className="mt-3 d-flex justify-content-center">
                      <img src={showImage} width="80px" alt="윙 배너 공연 썸네일" />
                    </div>
                  </a>
                ))}
              </div>
            </div>
          )}
        </div>
      </div>

      <div className="container">
        <div className="d-flex justify-content-end pr-4 pt-2">
          <nav>
            <ul className="nav nav-fill">
              {!userId && (
                <li className="nav-item">
                  <a className="nav-link top-menu" href="/user/sign_in_view">로그인</a>
                </li>
              )}
              {userId && (
                <li className="nav-item">
                  <a className="nav-link top-menu" href="/user/sign_out">로그아웃</a>
                </li>
              )}
              <li className="nav-item"><a className="nav-link top-menu" href="/user/sign_up_view">회원가입</a></li>
              <li className="nav-item"><a className="nav-link top-menu" href="/book/book_list_view">마이페이지</a></li>
              <li className="nav-item"><a className="nav-link top-menu" href="#">고객센터</a></li>
            </ul>
          </nav>
        </div>

        <div className="d-flex justify-content-center w-75">
          <a className="col-4 d-flex align-items-center justify-content-center" href="/main/main_view">
            <img src="/static/images/logo.png" alt="로고" width="200px" />
          </a>
          <div className="col-8 input-group my-3">
            <input
              id="searchText"
              type="text"
              className="form-control"
              placeholder="검색할 공연명을 입력하세요"
              value={searchText}
              onChange={(e) => setSearchText(e.target.value)}
              onKeyDown={handleSearchKeyDown}
            />
            <div className="input-group-append">
              <button className="btn btn-outline-secondary" type="button" onClick={handleSearch}>
                검색
              </button>
            </div>
          </div>
        </div>

        <div>
          <nav className="ml-2">
            <div className="col-4">
              <ul className="nav nav-fill">
                <li className="nav-item"><a className="nav-link middle-menu" href="/show/show_list_view?genre=전체">전체</a></li>
                <li className="nav-item"><a className="nav-link middle-menu" href="/show/show_list_view?genre=뮤지컬">뮤지컬</a></li>
                <li className="nav-item"><a className="nav-link middle-menu" href="/show/show_list_view?genre=대중음악">대중음악</a></li>
                <li className="nav-item"><a className="nav-link middle-menu" href="/show/show_list_view?genre=연극">연극</a></li>
                <li className="nav-item"><a className="nav-link middle-menu" href="#">기타</a></li>
                <li className="nav-item"><a className="nav-link middle-menu" href="#">리뷰</a></li>
              </ul>
            </div>
          </nav>
        </div>
      </div>
    </>
  );
}

export default Header;
