import { Outlet } from 'react-router-dom';
import Header from '../common/Header';
import Footer from '../common/Footer';

function Layout({ userId, userName, recentShowImageList }) {
  return (
    <div id="wrap">
      <header>
        <Header
          userId={userId}
          userName={userName}
          recentShowImageList={recentShowImageList}
        />
      </header>
      <section className="contents">
        <div className="container">
          <Outlet />
        </div>
      </section>
      <footer className="d-flex align-items-center">
        <Footer />
      </footer>
    </div>
  );
}

export default Layout;
