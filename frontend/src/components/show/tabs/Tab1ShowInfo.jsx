function Tab1ShowInfo({ show }) {
  return (
    <div className="tab-content w-100">
      <img src={show.show.infoImagePath} className="w-100" alt="공연 정보" />
    </div>
  );
}

export default Tab1ShowInfo;
