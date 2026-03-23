import { Outlet } from 'react-router-dom';
import Footer from '../common/Footer';

function LayoutSignUp() {
  return (
    <div id="wrap">
      <header>
        <a
          className="mt-3 col-4 d-flex align-items-center justify-content-center"
          href="/main/main_view"
        >
          <img src="/static/images/logo.png" alt="로고" width="200px" />
        </a>
        <hr />
      </header>
      <section className="contents">
        <Outlet />
      </section>
      <footer className="d-flex align-items-center">
        <Footer />
      </footer>
    </div>
  );
}

export default LayoutSignUp;
