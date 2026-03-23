import { useEffect, useState } from 'react';
import { useSearchParams, Link } from 'react-router-dom';

function ShowList() {
  const [searchParams] = useSearchParams();
  const genre = searchParams.get('genre') || '전체';
  const page = parseInt(searchParams.get('page') || '0', 10);
  const search = searchParams.get('search') || '';

  const [showList, setShowList] = useState([]);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);

  useEffect(() => {
    const params = new URLSearchParams({ genre, page, search });
    fetch(`/api/shows?${params}`)
      .then((res) => res.json())
      .then((data) => {
        setShowList(data.showList);
        setCurrentPage(data.currentPage);
        setTotalPages(data.totalPages);
      });
  }, [genre, page, search]);

  const buildUrl = (targetPage) =>
    `/show/show_list_view?genre=${encodeURIComponent(genre)}&page=${targetPage}&search=${encodeURIComponent(search)}`;

  return (
    <>
      <div className="my-4 display-4 text-center">{genre} 공연</div>

      <table className="table text-center">
        <thead>
          <tr>
            <th>사진</th>
            <th>공연명</th>
            <th>장소</th>
            <th>기간</th>
          </tr>
        </thead>
        <tbody>
          {showList.map((product) => (
            <tr key={product.show.id}>
              <td>
                <Link to={`/show/show_detail_view?showId=${product.show.id}`}>
                  <img src={product.show.imagePath} alt="공연 포스터" width="100px" />
                </Link>
              </td>
              <td>
                <Link to={`/show/show_detail_view?showId=${product.show.id}`}>
                  {product.show.name}
                </Link>
              </td>
              <td>{product.theater.name}</td>
              <td>{product.show.startDate} ~ {product.show.endDate}</td>
            </tr>
          ))}
        </tbody>
      </table>

      <div className="d-flex justify-content-center mb-3">
        {currentPage + 1}페이지 / {totalPages} 페이지
      </div>
      <div className="d-flex justify-content-center">
        {currentPage > 0 ? (
          <Link to={buildUrl(currentPage - 1)} className="mr-5 btn btn-warning">&lt;&lt; 이전</Link>
        ) : (
          <a href="#" className="mr-5 btn btn-warning disabled">&lt;&lt; 이전</a>
        )}
        {currentPage < totalPages - 1 ? (
          <Link to={buildUrl(currentPage + 1)} className="btn btn-warning">다음 &gt;&gt;</Link>
        ) : (
          <a href="#" className="btn btn-warning disabled">다음 &gt;&gt;</a>
        )}
      </div>
    </>
  );
}

export default ShowList;
