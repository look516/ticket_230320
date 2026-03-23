import { Outlet } from 'react-router-dom';

function LayoutBooking() {
  return (
    <div id="wrap">
      <header>
        <img src="/static/images/logo.png" alt="로고" />
      </header>
      <section className="contents">
        <div className="container">
          <Outlet />
        </div>
      </section>
    </div>
  );
}

export default LayoutBooking;
