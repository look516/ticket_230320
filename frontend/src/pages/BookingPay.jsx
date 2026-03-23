import { useState } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';

const SEAT_PRICE_MAP = { R: 60000 };

const DISCOUNT_OPTIONS = (price) => [
  { label: '정가',       value: price,                dataName: '정가' },
  { label: '청소년 30%', value: Math.round(price * 0.7), dataName: '청소년 30%' },
  { label: '복지 50%',   value: Math.round(price * 0.5), dataName: '복지 50%' },
];

const PAYMENT_OPTIONS = [
  { value: 'KB',      label: '국민카드' },
  { value: 'SAMSUNG', label: '삼성카드' },
  { value: 'SHINHAN', label: '신한카드' },
  { value: 'WOORI',   label: '우리카드' },
];

function BookingPay() {
  const navigate = useNavigate();
  const { state } = useLocation();
  const showName  = state?.showName  ?? '';
  const seatGrade = state?.seatGrade ?? 'R';

  const seatPrice       = SEAT_PRICE_MAP[seatGrade] ?? 60000;
  const discountOptions = DISCOUNT_OPTIONS(seatPrice);

  const [selectedDiscount, setSelectedDiscount] = useState(null);
  const [payment, setPayment] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!selectedDiscount) { alert('할인 종류를 선택해주세요.'); return; }
    if (!payment) { alert('결제수단을 선택해주세요.'); return; }

    try {
      const response = await fetch('/book/pay', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: new URLSearchParams({
          discount:     selectedDiscount.value,
          discountName: selectedDiscount.dataName,
          payment,
        }),
      });
      const data = await response.json();
      if (data.code === 1) {
        alert('결제 완료되었습니다.');
        navigate('/book/booking_done_view');
      } else {
        alert(data.errorMessage);
      }
    } catch {
      alert('오류');
    }
  };

  return (
    <div>
      <div className="text-center"><h3>&lt;{showName}&gt;</h3></div>

      <form onSubmit={handleSubmit}>
        <table className="table text-center">
          <thead>
            <tr><th>할인종류</th><th>가격</th><th>선택</th></tr>
          </thead>
          <tbody>
            {discountOptions.map((opt) => (
              <tr key={opt.dataName}>
                <td>{opt.label}</td>
                <td>{opt.value.toLocaleString()}원</td>
                <td>
                  <input
                    type="radio"
                    name="discount"
                    value={opt.value}
                    checked={selectedDiscount?.dataName === opt.dataName}
                    onChange={() => setSelectedDiscount(opt)}
                  />
                </td>
              </tr>
            ))}
          </tbody>
        </table>

        <select className="form-control my-2" value={payment} onChange={(e) => setPayment(e.target.value)}>
          <option value="">결제수단 선택</option>
          {PAYMENT_OPTIONS.map((opt) => (
            <option key={opt.value} value={opt.value}>{opt.label}</option>
          ))}
        </select>

        <button type="submit" className="btn btn-info form-control my-2">결제하기</button>
      </form>
    </div>
  );
}

export default BookingPay;
