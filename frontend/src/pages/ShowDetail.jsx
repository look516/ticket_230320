import { useEffect, useState } from 'react';
import { useSearchParams } from 'react-router-dom';
import FullCalendar from '@fullcalendar/react';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';

import Tab1ShowInfo from '../components/show/tabs/Tab1ShowInfo';
import Tab2Casting  from '../components/show/tabs/Tab2Casting';
import Tab3SaleInfo from '../components/show/tabs/Tab3SaleInfo';
import Tab4Reviews  from '../components/show/tabs/Tab4Reviews';
import Tab5Expected from '../components/show/tabs/Tab5Expected';
import Tab6QnA      from '../components/show/tabs/Tab6QnA';

const TAB_LIST = [
  { id: 'tab1', label: '공연정보' },
  { id: 'tab2', label: '캐스팅정보' },
  { id: 'tab3', label: '판매정보' },
  { id: 'tab4', label: '관람후기' },
  { id: 'tab5', label: '기대평' },
  { id: 'tab6', label: 'Q&A' },
];

function formatDate(date) {
  const y = date.getFullYear();
  const m = String(date.getMonth() + 1).padStart(2, '0');
  const d = String(date.getDate()).padStart(2, '0');
  return `${y}-${m}-${d}`;
}

function ShowDetail({ currentUserId }) {
  const [searchParams] = useSearchParams();
  const showId = searchParams.get('showId');

  const [show, setShow] = useState(null);
  const [average, setAverage] = useState(0);
  const [activeTab, setActiveTab] = useState('tab1');
  const [selectedDate, setSelectedDate] = useState('');
  const [selectedTime, setSelectedTime] = useState('');
  const [isEnded, setIsEnded] = useState(false);
  const [validRange, setValidRange] = useState(null);

  useEffect(() => {
    fetch(`/api/shows/${showId}`)
      .then((res) => res.json())
      .then((data) => {
        setShow(data.show);
        setAverage(data.average);
      });
  }, [showId]);

  useEffect(() => {
    if (!show) return;
    const sDate = new Date(show.show.validStartDate);
    const eDate = new Date(show.show.validEndDate);
    const tDate = new Date();

    if (eDate < tDate) {
      setIsEnded(true);
      setValidRange({ start: formatDate(sDate), end: formatDate(eDate) });
      return;
    }

    const validStart = sDate > tDate ? sDate : tDate;
    const validEnd = new Date(Math.max(eDate, tDate));
    validEnd.setDate(validEnd.getDate() + 1);
    setValidRange({ start: formatDate(validStart), end: formatDate(validEnd) });
  }, [show]);

  const handleDateSelect = (info) => {
    setSelectedDate(info.startStr);
    setSelectedTime('');
  };

  const handleReserve = () => {
    const bookingUrl = `/book/book_page_view?showId=${showId}`;
    const popup = window.open(bookingUrl, '_blank', 'width=800,height=700,popup');
    popup.onload = () => {
      const startDate = new Date(show.show.validStartDate) > new Date()
        ? new Date(show.show.validStartDate) : new Date();
      const endDate = new Date(show.show.validEndDate) > new Date()
        ? new Date(show.show.validEndDate) : new Date();
      popup.postMessage(
        { showId, selectedDate, selectedTime, validStartDate: startDate, validEndDate: endDate },
        '*'
      );
    };
  };

  const renderTabContent = () => {
    switch (activeTab) {
      case 'tab1': return <Tab1ShowInfo show={show} />;
      case 'tab2': return <Tab2Casting />;
      case 'tab3': return <Tab3SaleInfo show={show} />;
      case 'tab4': return <Tab4Reviews show={show} average={average} currentUserId={currentUserId} />;
      case 'tab5': return <Tab5Expected />;
      case 'tab6': return <Tab6QnA />;
      default:     return null;
    }
  };

  if (!show) return <div>로딩 중...</div>;

  return (
    <div className="d-flex justify-content-between">
      <div className="col-8">
        <h1 className="pl-4 mt-3">{show.show.genre} &lt; {show.show.name} &gt;</h1>

        <div className="d-flex">
          <div className="ml-3 col-3">
            <img src={show.show.imagePath} alt="공연 이미지" width="180px" />
          </div>
          <div className="col-9 mt-3">
            <ul>
              <li className="show-info d-flex">
                <strong className="col-2 p-0">장소</strong>
                <div className="col-10"><p className="w-100"><a className="infoBtn" href="#">{show.theater.name}</a></p></div>
              </li>
              <li className="show-info d-flex">
                <strong className="col-2 p-0">공연기간</strong>
                <div className="col-10"><p className="w-100">{show.show.startDate} ~ {show.show.endDate}</p></div>
              </li>
              <li className="show-info d-flex">
                <strong className="col-2 p-0">공연시간</strong>
                <div className="col-10"><p className="w-100">{show.show.time}분</p></div>
              </li>
              <li className="show-info d-flex">
                <strong className="col-2 p-0">관람연령</strong>
                <div className="col-10"><p className="w-100">{show.show.age}세 이상</p></div>
              </li>
            </ul>
          </div>
        </div>

        <div className="mt-3 ml-3">
          <ul className="tab-nav nav nav-fill">
            {TAB_LIST.map((tab) => (
              <li key={tab.id} className="nav-item">
                <a
                  href="#"
                  className={`btn nav-link tab-menu ${activeTab === tab.id ? 'btn-secondary' : ''}`}
                  onClick={(e) => { e.preventDefault(); setActiveTab(tab.id); }}
                >
                  {tab.label}
                </a>
              </li>
            ))}
          </ul>
          <div id="tab" className="tab-content-link">
            {renderTabContent()}
          </div>
        </div>
      </div>

      <div className="ml-3 book-area col-4 d-flex justify-content-center">
        <div className="text-center">
          <h3>관람일</h3>
          {validRange && (
            <FullCalendar
              plugins={[dayGridPlugin, interactionPlugin]}
              initialView="dayGridMonth"
              locale="ko"
              selectable={true}
              selectAllow={(info) => {
                const diff = (new Date(info.end) - new Date(info.start)) / (1000 * 60 * 60 * 24);
                return diff <= 1;
              }}
              validRange={validRange}
              contentHeight="auto"
              headerToolbar={{ left: '', center: 'title', right: 'prev,next' }}
              select={handleDateSelect}
            />
          )}

          <div className="my-3">{selectedDate}</div>

          {selectedDate && (
            <div className="d-flex justify-content-around">
              {[{ value: '15:00:00', label: '15시' }, { value: '19:00:00', label: '19시' }].map((t) => (
                <label key={t.value}>
                  <input
                    type="radio"
                    name="selectShowTime"
                    value={t.value}
                    checked={selectedTime === t.value}
                    onChange={() => setSelectedTime(t.value)}
                  />
                  {' '}{t.label}
                </label>
              ))}
            </div>
          )}

          {!isEnded ? (
            <button className="btn btn-info my-2 col-12" onClick={handleReserve}>예매하기</button>
          ) : (
            <button className="btn btn-secondary my-2 col-12" disabled>예매가 끝난 상품입니다.</button>
          )}
        </div>
      </div>
    </div>
  );
}

export default ShowDetail;
