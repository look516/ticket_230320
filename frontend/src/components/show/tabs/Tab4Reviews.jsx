function Tab4Reviews({ show, average, currentUserId }) {
  const handleDeleteReview = async (reviewId) => {
    try {
      const response = await fetch(`/review/delete?reviewId=${reviewId}`, { method: 'DELETE' });
      const data = await response.json();
      if (data.code === 1) {
        alert('리뷰가 삭제되었습니다.');
        window.location.reload();
      } else {
        alert(data.errorMessage);
      }
    } catch {
      alert('댓글 삭제가 불가합니다.');
    }
  };

  return (
    <div className="tab-content w-100">
      <div>
        <div className="text-center bg-secondary py-3">
          <h2>{show.show.genre} &lt; {show.show.name} &gt; 리뷰</h2>
        </div>

        <div className="my-3 d-flex justify-content-between">
          <h2>{'★'.repeat(Math.round(average))}</h2>
          <h2>{average}</h2>
        </div>

        <div className="d-flex justify-content-end">
          <a href={`/review/review_create_view?showId=${show.show.id}`} className="btn btn-info" id="reviewBtn">
            후기작성
          </a>
        </div>

        <div className="border mt-2 p-2">
          {(!show.reviewList || show.reviewList.length === 0) && (
            <div className="text-center">리뷰가 없습니다.</div>
          )}
          {show.reviewList && show.reviewList.map((review) => (
            <div key={review.review.id} className="border p-3 bg-warning">
              <div className="d-flex justify-content-between">
                <h5>{'★'.repeat(review.review.point)}</h5>
                <div>
                  <span className="mr-3">{review.user.name}</span>
                  <span>
                    {review.review.createdAt?.slice(0, 10)}
                    {currentUserId === review.review.userId && (
                      <a
                        href="#"
                        className="review-del-btn ml-1"
                        onClick={(e) => { e.preventDefault(); handleDeleteReview(review.review.id); }}
                      >
                        <img
                          src="https://www.iconninja.com/files/603/22/506/x-icon.png"
                          width="10px"
                          height="10px"
                          alt="삭제"
                        />
                      </a>
                    )}
                  </span>
                </div>
              </div>
              <div className="mt-2">
                <h5>{review.review.subject}</h5>
                <span>{review.review.content}</span>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

export default Tab4Reviews;
