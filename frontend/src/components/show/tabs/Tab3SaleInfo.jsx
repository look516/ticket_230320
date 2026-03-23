function Tab3SaleInfo({ show }) {
  return (
    <div className="tab-content w-100">
      <img src={show.show.discountImagePath} className="w-100" alt="판매 정보" />
    </div>
  );
}

export default Tab3SaleInfo;
