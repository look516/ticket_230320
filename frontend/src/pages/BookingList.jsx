import { useEffect, useState } from 'react';
import { Link, useSearchParams } from 'react-router-dom';

function formatDate(dateStr) {
  if (!dateStr) return '';
  return new Intl.DateTimeFormat('ko-KR', {
    year: 'numeric', month: '2-digit', day: '2-digit',
  }).format(new Date(dateStr)).replace(/\. /g, '/').replace('.', '');
}

function BookingList({ userName }) {
  const [searchParams] = useSearchParams();
  const isBooked = searchParams.get('isBooked');

  const [bookingViewList, setBookingViewList] = useState([]);

  useEffect(() => {
    const params = new URLSearchParams();
    if (isBooked !== null) params.set('isBooked', isBooked);
    fetch(`/api/bookings?${params}`)
      .then((res) => res.json())
      .then((data) => setBookingViewList(data.bookingViewList));
  }, [isBooked]);

  return (
    <>
      <div className="border text-center">
        <div className="my-3">{userName}님은 현재 FAMILY 회원입니다.</div>
        <div className="mb-2"><a href="">※회원정보수정※</a></div>
      </div>

      <div className="text-center">
        <h3 className="my-3">최근 예매 내역</h3>
      </div>

      <Link to="/book/book_list_view">전체</Link>{' '}
      <Link to="/book/book_list_view?isBooked=1">예매완료</Link>{' '}
      <Link to="/book/book_list_view?isBooked=0">예매취소</Link>

      <table className="table text-center">
        <thead>
          <tr>
            <th>예매일</th><th>예약번호</th><th>공연명</th>
            <th>관람일</th><th>매수</th><th>취소가능일</th><th>상태</th>
          </tr>
        </thead>
        <tbody>
          {bookingViewList.map((booking) => (
            <tr key={booking.booking.id}>
              <td>{formatDate(booking.booking.bookingDate)}</td>
              <td>{booking.booking.bookingNumber}</td>
              <td>{booking.showView.show.name}</td>
              <td>{formatDate(booking.booking.showDate)}</td>
              <td>1</td>
              <td>{formatDate(booking.booking.bookingDate)}</td>
              <td>
                {booking.booking.isReserved > 0 ? (
                  <>예약완료{' '}<Link to={`/book/book_detail_view?bookingId=${booking.booking.id}`} className="btn btn-info">상세</Link></>
                ) : (
                  <>예약취소{' '}<Link to={`/book/book_detail_view?bookingId=${booking.booking.id}`} className="btn btn-secondary">상세</Link></>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </>
  );
}

export default BookingList;
