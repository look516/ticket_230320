import { useEffect, useState } from 'react';
import { useSearchParams } from 'react-router-dom';

const TOTAL_SEATS = 48;
const COLS_PER_ROW = 8;

const ALL_SEATS = Array.from({ length: TOTAL_SEATS }, (_, i) => {
  const num = i + 1;
  return { num, floor: '1층', col: Math.floor(num / COLS_PER_ROW), row: num % COLS_PER_ROW };
});

const SEAT_ROWS = Array.from({ length: TOTAL_SEATS / COLS_PER_ROW }, (_, i) =>
  ALL_SEATS.slice(i * COLS_PER_ROW, (i + 1) * COLS_PER_ROW)
);

function formatDate(date) {
  const y = date.getFullYear();
  const m = String(date.getMonth() + 1).padStart(2, '0');
  const d = String(date.getDate()).padStart(2, '0');
  return `${y}-${m}-${d}`;
}

function buildDateOptions(start, end) {
  const options = [];
  const endDate = new Date(end);
  endDate.setDate(endDate.getDate() + 1);
  for (let d = new Date(start); d < endDate; d.setDate(d.getDate() + 1)) {
    options.push(formatDate(new Date(d)));
  }
  return options;
}

function BookingPage() {
  const [searchParams] = useSearchParams();
  const showId = searchParams.get('showId');

  const [showName, setShowName] = useState('');
  const [dateOptions, setDateOptions] = useState([]);
  const [showDate, setShowDate] = useState('');
  const [showTime, setShowTime] = useState('');
  const [reservedSeats, setReservedSeats] = useState([]);
  const [selectedSeat, setSelectedSeat] = useState(null);

  useEffect(() => {
    fetch(`/api/shows/${showId}`)
      .then((res) => res.json())
      .then((data) => setShowName(data.show.show.name));
  }, [showId]);

  useEffect(() => {
    const handler = (e) => {
      const { validStartDate, validEndDate, selectedDate, selectedTime } = e.data;
      if (!validStartDate) return;

      const endDate = new Date(validEndDate);
      endDate.setDate(endDate.getDate() + 1);
      const options = buildDateOptions(new Date(validStartDate), endDate);
      setDateOptions(options);

      const initDate = selectedDate ?? formatDate(new Date(validStartDate));
      const initTime = selectedTime ?? '15:00:00';
      setShowDate(initDate);
      setShowTime(initTime);
    };
    window.addEventListener('message', handler);
    return () => window.removeEventListener('message', handler);
  }, []);

  useEffect(() => {
    if (!showDate || !showTime) return;
    fetch(`/book/booking_seat?showId=${showId}&selectedDate=${showDate}&selectedTime=${showTime}`)
      .then((res) => res.json())
      .then((data) => {
        setReservedSeats(Array.isArray(data) ? data : []);
        setSelectedSeat(null);
      });
  }, [showDate, showTime, showId]);

  const handleSeatClick = (seat) => {
    if (!showDate || !showTime) { alert('날짜와 시간을 선택해주세요'); return; }
    if (reservedSeats.includes(String(seat.num))) return;
    setSelectedSeat((prev) => (prev?.num === seat.num ? null : seat));
  };

  const getSeatClass = (seat) => {
    if (reservedSeats.includes(String(seat.num))) return 'seat-box reserved-box mb-2 mx-2';
    if (selectedSeat?.num === seat.num) return 'seat-box checked-box mb-2 mx-2';
    return 'seat-box mb-2 mx-2';
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!selectedSeat) { alert('좌석을 선택해주세요'); return; }

    const seatInfo = `${selectedSeat.floor} ${selectedSeat.col}열 ${selectedSeat.row} ${selectedSeat.num}`;
    const body = new URLSearchParams({
      showId, showDate, showTime,
      totalInput: '1', seatGradeInput: 'R', seatInput: seatInfo,
    });

    try {
      const response = await fetch('/book/booking', { method: 'POST', body });
      const data = await response.json();
      if (data.code === 1) {
        window.location.href = data.forwardUrl;
      } else {
        alert(data.errorMessage);
      }
    } catch {
      alert('오류입니다.');
    }
  };

  return (
    <div>
      <div className="text-center"><h3>&lt;{showName}&gt;</h3></div>

      <form id="bookingForm" onSubmit={handleSubmit}>
        <input type="hidden" name="showId" value={showId} />

        <div className="d-flex">
          <select className="form-control col-6 m-2" value={showDate}
            onChange={(e) => { setShowDate(e.target.value); setSelectedSeat(null); }}>
            <option value="">날짜</option>
            {dateOptions.map((d) => <option key={d} value={d}>{d}</option>)}
          </select>

          <select className="form-control col-6 m-2" value={showTime}
            onChange={(e) => { setShowTime(e.target.value); setSelectedSeat(null); }}>
            <option value="">시간</option>
            <option value="15:00:00">15시 00분</option>
            <option value="19:00:00">19시 00분</option>
          </select>
        </div>

        <div>
          {SEAT_ROWS.map((row, rowIdx) => (
            <div key={rowIdx} className="d-flex justify-content-center">
              {row.map((seat) => (
                <button
                  key={seat.num}
                  type="button"
                  name="seat"
                  className={getSeatClass(seat)}
                  disabled={reservedSeats.includes(String(seat.num))}
                  onClick={() => handleSeatClick(seat)}
                >
                  {seat.row}
                </button>
              ))}
            </div>
          ))}
        </div>

        <div className="d-flex justify-content-around mt-3 mb-5">
          <div>
            <div id="seat">
              {selectedSeat && <span>좌석번호: {selectedSeat.floor} {selectedSeat.col}열 {selectedSeat.row}</span>}
            </div>
            <div id="total">
              {selectedSeat && <span>총 좌석 수: 1석</span>}
            </div>
            <div id="seatGrade">
              {selectedSeat && <span>좌석등급: R석</span>}
            </div>
          </div>
          <button type="submit" className="btn btn-info my-2">선택완료</button>
        </div>
      </form>
    </div>
  );
}

export default BookingPage;
