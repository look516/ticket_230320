import { useEffect, useState } from 'react';
import { useSearchParams, useNavigate } from 'react-router-dom';

function formatDate(dateStr, includeTime = false) {
  if (!dateStr) return '';
  const opts = includeTime
    ? { year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit', second: '2-digit' }
    : { year: 'numeric', month: 'long', day: 'numeric' };
  return new Intl.DateTimeFormat('ko-KR', opts).format(new Date(dateStr));
}

function BookingDetail() {
  const [searchParams] = useSearchParams();
  const bookingId = searchParams.get('bookingId');
  const navigate  = useNavigate();

  const [booking, setBooking] = useState(null);
  const [pay,     setPay]     = useState(null);

  useEffect(() => {
    fetch(`/api/bookings/${bookingId}`)
      .then((res) => res.json())
      .then((data) => { setBooking(data.booking); setPay(data.pay); });
  }, [bookingId]);

  const handleCancel = async () => {
    if (!window.confirm('정말 취소하시겠습니까?')) return;
    try {
      const response = await fetch('/book/update_status', {
        method: 'PUT',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: new URLSearchParams({ bookingId: booking.booking.id, payId: pay.id }),
      });
      const data = await response.json();
      if (data.code === 1) {
        alert('예약이 취소되었습니다.');
        navigate('/book/book_list_view');
      } else {
        alert(data.errorMessage);
      }
    } catch {
      alert('예약 취소에 실패했습니다.');
    }
  };

  if (!booking || !pay) return <div>로딩 중...</div>;

  const bookingRows = [
    { label: '예매자',   value: booking.user.name },
    { label: '예약번호', value: booking.booking.bookingNumber },
    { label: '이용일',   value: `${formatDate(booking.booking.showDate)} ${booking.booking.showTime}` },
    { label: '출연진',   value: 'ㄱㄱㄱ, ㄴㄴㄴ, ㄷㄷㄷ 등' },
    { label: '장소',     value: booking.showView.theater.name },
    { label: '좌석',     value: booking.booking.seat },
  ];

  const payRows = [
    { label: '결제일',    value: formatDate(pay.payDate, true) },
    { label: '예매상태',  value: pay.isValid > 0 ? '예약완료' : '예약취소' },
    { label: '결제수단',  value: `${pay.payment}카드` },
    { label: '결제금액',  value: `${pay.discountPrice}원` },
    {
      label: '환불예상액',
      value: pay.isValid > 0
        ? `환불가능금액은 ${pay.discountPrice}원 입니다.`
        : `${pay.discountPrice}원이 환불됩니다.`,
    },
  ];

  return (
    <>
      <div className="border">
        <div className="text-center w-100 my-3"><b>{booking.user.name}님의 예매내역서</b></div>
        <div className="d-flex justify-content-center">
          <div>
            <img src={booking.showView.show.imagePath} alt="공연이미지" width="200px" />
            <button className="d-block btn btn-info my-3 w-100"><a href="#">달력에 등록</a></button>
          </div>
          <div className="table-box">
            <ul>
              {bookingRows.map(({ label, value }) => (
                <li key={label} className="book-box border">
                  <div className="col-2 book-content"><strong>{label}</strong></div>
                  <div className="col-10 book-content"><span>{value}</span></div>
                </li>
              ))}
            </ul>
          </div>
        </div>
      </div>

      <div className="border mt-5">
        <div className="text-center w-100 my-3"><b>{booking.user.name}님의 결제내역서</b></div>
        <div className="pay-box">
          <ul className="w-100 d-flex justify-content-center">
            <div>
              {payRows.map(({ label, value }) => (
                <li key={label} className="book-box border">
                  <div className="col-2 book-content"><strong>{label}</strong></div>
                  <div className="col-10 book-content"><span>{value}</span></div>
                </li>
              ))}
            </div>
          </ul>
        </div>

        <div className="w-75 d-flex justify-content-end my-4">
          <button type="button" className="btn btn-danger" onClick={handleCancel}>예매취소</button>
          <button className="btn btn-info ml-3"><a href="/book/book_list_view">예매목록</a></button>
        </div>
      </div>
    </>
  );
}

export default BookingDetail;
