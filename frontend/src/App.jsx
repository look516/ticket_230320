import { createBrowserRouter, RouterProvider, Navigate } from 'react-router-dom';

import { AuthProvider, useAuth } from './context/AuthContext';

import Layout        from './components/layout/Layout';
import LayoutSignUp  from './components/layout/LayoutSignUp';
import LayoutBooking from './components/layout/LayoutBooking';

import MainPage      from './pages/MainPage';
import SignIn        from './pages/SignIn';
import SignUp        from './pages/SignUp';
import ShowList      from './pages/ShowList';
import ShowDetail    from './pages/ShowDetail';
import BookingList   from './pages/BookingList';
import BookingDetail from './pages/BookingDetail';
import BookingPage   from './pages/BookingPage';
import BookingPay    from './pages/BookingPay';
import BookingDone   from './pages/BookingDone';
import ReviewCreate  from './pages/ReviewCreate';

function MainLayout() {
  const { user, recentShowImageList } = useAuth();
  return (
    <Layout
      userId={user?.userId}
      userName={user?.userName}
      recentShowImageList={recentShowImageList}
    />
  );
}

function PrivateRoute({ children }) {
  const { user } = useAuth();
  return user ? children : <Navigate to="/user/sign_in_view" replace />;
}

function ShowDetailWithAuth() {
  const { user } = useAuth();
  return <ShowDetail currentUserId={user?.userId} />;
}

function BookingListWithAuth() {
  const { user } = useAuth();
  return <BookingList userName={user?.userName} />;
}

const router = createBrowserRouter([
  {
    element: <MainLayout />,
    children: [
      { path: '/',                           element: <Navigate to="/main/main_view" replace /> },
      { path: '/main/main_view',             element: <MainPage /> },
      { path: '/user/sign_in_view',          element: <SignIn /> },
      { path: '/show/show_list_view',        element: <ShowList /> },
      { path: '/show/show_detail_view',      element: <ShowDetailWithAuth /> },
      { path: '/review/review_create_view',  element: <PrivateRoute><ReviewCreate /></PrivateRoute> },
      { path: '/book/book_list_view',        element: <PrivateRoute><BookingListWithAuth /></PrivateRoute> },
      { path: '/book/book_detail_view',      element: <PrivateRoute><BookingDetail /></PrivateRoute> },
    ],
  },
  {
    element: <LayoutSignUp />,
    children: [
      { path: '/user/sign_up_view', element: <SignUp /> },
    ],
  },
  {
    element: <LayoutBooking />,
    children: [
      { path: '/book/book_page_view',    element: <PrivateRoute><BookingPage /></PrivateRoute> },
      { path: '/book/pay_view',          element: <PrivateRoute><BookingPay /></PrivateRoute> },
      { path: '/book/booking_done_view', element: <PrivateRoute><BookingDone /></PrivateRoute> },
    ],
  },
]);

function App() {
  return (
    <AuthProvider>
      <RouterProvider router={router} />
    </AuthProvider>
  );
}

export default App;
