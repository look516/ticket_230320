import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function SignUp() {
  const navigate = useNavigate();
  const [form, setForm] = useState({
    loginId: '',
    password: '',
    confirmPassword: '',
    name: '',
    email: '',
    phoneNumber: '',
  });
  const [idCheckStatus, setIdCheckStatus] = useState('idle');

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
    if (name === 'loginId') setIdCheckStatus('idle');
  };

  const handleIdCheck = async () => {
    setIdCheckStatus('idle');
    const loginId = form.loginId.trim();

    if (loginId.length < 4) { setIdCheckStatus('tooShort'); return; }

    try {
      const response = await fetch(`/user/is_duplicated_id?loginId=${encodeURIComponent(loginId)}`);
      const data = await response.json();
      setIdCheckStatus(data.isDuplicatedId ? 'duplicated' : 'ok');
    } catch {
      alert('중복확인에 실패했습니다.');
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const { loginId, password, confirmPassword, name, email, phoneNumber } = form;

    if (!loginId.trim()) { alert('아이디를 입력하세요'); return; }
    if (!password || !confirmPassword) { alert('비밀번호를 입력하세요'); return; }
    if (password !== confirmPassword) { alert('비밀번호가 일치하지 않습니다'); return; }
    if (!name.trim()) { alert('이름을 입력하세요'); return; }
    if (!email.trim()) { alert('이메일을 입력하세요'); return; }
    if (!phoneNumber.trim()) { alert('전화번호를 입력하세요'); return; }
    if (idCheckStatus !== 'ok') { alert('아이디 중복확인을 다시 해주세요'); return; }

    try {
      const response = await fetch('/user/sign_up', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: new URLSearchParams({ loginId, password, confirmPassword, name, email, phoneNumber }),
      });
      const data = await response.json();

      if (data.code === 1) {
        alert('가입을 환영합니다! 로그인을 해주세요.');
        navigate('/user/sign_in_view');
      } else {
        alert(data.errorMessage);
      }
    } catch {
      alert('회원가입에 실패했습니다.');
    }
  };

  return (
    <div className="sign-up-box d-flex justify-content-center">
      <div className="w-100">
        <h1 className="font-weight-bold ml-3 text-center">회원가입</h1>
        <form className="form-box d-flex justify-content-center" id="signUpForm" onSubmit={handleSubmit}>
          <div>
            <span className="sign-up-subject">ID</span>
            <div className="d-flex ml-3 mt-3">
              <input
                type="text"
                id="loginId"
                name="loginId"
                className="form-control"
                placeholder="ID를 입력해주세요"
                value={form.loginId}
                onChange={handleChange}
              />
              <button type="button" className="btn btn-success col-4" onClick={handleIdCheck}>
                중복확인
              </button>
            </div>

            <div className="ml-3 mb-3">
              {idCheckStatus === 'tooShort'    && <div className="small text-danger">ID를 4자 이상 입력해주세요.</div>}
              {idCheckStatus === 'duplicated'  && <div className="small text-danger">이미 사용중인 ID입니다.</div>}
              {idCheckStatus === 'ok'          && <div className="small text-success">사용 가능한 ID 입니다.</div>}
            </div>

            <span className="sign-up-subject">Password</span>
            <div className="m-3">
              <input type="password" name="password" className="form-control" placeholder="비밀번호를 입력하세요" value={form.password} onChange={handleChange} />
            </div>

            <span className="sign-up-subject">Confirm password</span>
            <div className="m-3">
              <input type="password" name="confirmPassword" className="form-control" placeholder="비밀번호를 입력하세요" value={form.confirmPassword} onChange={handleChange} />
            </div>

            <span className="sign-up-subject">Name</span>
            <div className="m-3">
              <input type="text" name="name" className="form-control" placeholder="이름을 입력하세요" value={form.name} onChange={handleChange} />
            </div>

            <span className="sign-up-subject">이메일</span>
            <div className="m-3">
              <input type="text" name="email" className="form-control" placeholder="이메일을 입력하세요" value={form.email} onChange={handleChange} />
            </div>

            <span className="sign-up-subject">전화번호</span>
            <div className="m-3">
              <input type="text" name="phoneNumber" className="form-control" placeholder="010-1234-1234" value={form.phoneNumber} onChange={handleChange} />
            </div>

            <br />
            <div className="d-flex justify-content-center m-3">
              <button type="submit" className="btn btn-info">가입하기</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  );
}

export default SignUp;
