import { useEffect, useState } from 'react';

const BANNER_IMAGES = [
  '/static/images/banner1.png',
  '/static/images/banner2.png',
  '/static/images/banner3.png',
];

function MainPage() {
  const [bannerIndex, setBannerIndex] = useState(0);

  useEffect(() => {
    const timer = setInterval(() => {
      setBannerIndex((prev) => (prev + 1) % BANNER_IMAGES.length);
    }, 3000);

    return () => clearInterval(timer);
  }, []);

  return (
    <>
      <section className="px-1 my-2">
        <a href="https://www.naver.com">
          <img
            src={BANNER_IMAGES[bannerIndex]}
            alt="banner"
            id="banner"
            width="100%"
          />
        </a>
      </section>
    </>
  );
}

export default MainPage;
