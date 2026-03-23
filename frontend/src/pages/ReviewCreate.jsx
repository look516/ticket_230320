import { useEffect, useState } from 'react';
import { useSearchParams, useNavigate } from 'react-router-dom';

const POINT_OPTIONS = [1, 2, 3, 4, 5];
const TIME_OPTIONS  = [
  { value: '15:00:00', label: '15시' },
  { value: '19:00:00', label: '19시' },
];

function ReviewCreate() {
  const [searchParams] = useSearchParams();
  const showId   = searchParams.get('showId');
  const navigate = useNavigate();

  const [showName, setShowName] = useState('');
  const [form, setForm] = useState({
    showDate: '', showTime: '', subject: '', point: '5', content: '',
  });

  useEffect(() => {
    fetch(`/api/shows/${showId}`)
      .then((res) => res.json())
      .then((data) => setShowName(data.show.show.name));
  }, [showId]);

  const handleChange = (e) => {
    setForm((prev) => ({ ...prev, [e.target.name]: e.target.value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!form.subject.trim()) { alert('제목을 입력하세요'); return; }
    if (!form.content)        { alert('내용을 입력하세요'); return; }

    try {
      const response = await fetch('/review/create', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: new URLSearchParams({ showId, ...form }),
      });
      const data = await response.json();
      if (data.code === 1) {
        alert('리뷰가 저장되었습니다.');
        navigate(`/show/show_detail_view?showId=${showId}`);
      } else {
        alert(data.errorMessage);
      }
    } catch {
      alert('리뷰 저장에 실패했습니다.');
    }
  };

  return (
    <div className="d-flex justify-content-center border">
      <div>
        <div className="text-center my-3"><h3>공연관람후기</h3></div>

        <form id="reviewCreateForm" onSubmit={handleSubmit}>
          <div className="pay-box">
            <ul className="w-100 d-flex justify-content-center">
              <div>
                <li className="book-box border">
                  <div className="col-2 book-content"><strong>상품명</strong></div>
                  <div className="col-10 book-content">
                    <input type="text" className="form-control" value={showName} disabled />
                    <input type="hidden" name="showId" value={showId} />
                  </div>
                </li>
                <li className="book-box border">
                  <div className="col-2 book-content"><strong>관람일시</strong></div>
                  <div className="col-10 book-content">
                    <input type="date" name="showDate" className="form-control" value={form.showDate} onChange={handleChange} />
                  </div>
                </li>
                <li className="book-box border">
                  <div className="col-2 book-content"><strong>관람시간</strong></div>
                  <div className="col-10 book-content">
                    <select name="showTime" value={form.showTime} onChange={handleChange}>
                      <option value="">시간선택</option>
                      {TIME_OPTIONS.map((t) => (
                        <option key={t.value} value={t.value}>{t.label}</option>
                      ))}
                    </select>
                  </div>
                </li>
                <li className="book-box border">
                  <div className="col-2 book-content"><strong>제목</strong></div>
                  <div className="col-10 book-content">
                    <input type="text" name="subject" className="form-control" value={form.subject} onChange={handleChange} />
                  </div>
                </li>
                <li className="book-box border">
                  <div className="col-2 book-content"><strong>별점</strong></div>
                  <div className="col-10 book-content">
                    {POINT_OPTIONS.map((p) => (
                      <span key={p}>
                        <input
                          type="radio" name="point" value={String(p)} id={`point${p}`}
                          checked={form.point === String(p)} onChange={handleChange}
                        />
                        <label htmlFor={`point${p}`} className="point-radio">{p}점</label>
                      </span>
                    ))}
                  </div>
                </li>
                <li className="book-box border">
                  <div className="col-2 book-content"><strong>내용</strong></div>
                  <div className="col-10 book-content">
                    <textarea name="content" className="w-100" rows={10} value={form.content} onChange={handleChange} />
                  </div>
                </li>
              </div>
            </ul>
          </div>

          <div className="d-flex justify-content-between mb-3">
            <div><a href="#" className="btn btn-secondary ml-5">목록</a></div>
            <div>
              <button type="submit" className="btn btn-info mr-2">확인</button>
              <a href={`/show/show_detail_view?showId=${showId}`} className="btn btn-secondary">취소</a>
            </div>
          </div>
        </form>
      </div>
    </div>
  );
}

export default ReviewCreate;
