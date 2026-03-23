import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function SignIn() {
  const navigate = useNavigate();
  const [form, setForm] = useState({ loginId: '', password: '' });
  const [autoLogin, setAutoLogin] = useState(false);
  const [saveId, setSaveId] = useState(false);

  const handleChange = (e) => {
    setForm((prev) => ({ ...prev, [e.target.name]: e.target.value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!form.loginId.trim()) { alert('아이디를 입력해주세요.'); return; }
    if (!form.password) { alert('비밀번호를 입력해주세요.'); return; }

    try {
      const response = await fetch('/user/sign_in', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: new URLSearchParams(form),
      });
      const data = await response.json();

      if (data.result === '성공') {
        navigate('/main/main_view');
      } else {
        alert('로그인에 실패했습니다. 다시 시도해주세요.');
      }
    } catch {
      alert('로그인에 실패했습니다. 다시 시도해주세요.');
    }
  };

  return (
    <div className="d-flex justify-content-center">
      <div className="m-5">
        <div className="d-flex justify-content-center m-5">
          <div>
            <form id="loginForm" onSubmit={handleSubmit}>
              <div className="input-group mb-3">
                <div className="input-group-prepend">
                  <span className="input-group-text">ID</span>
                </div>
                <input
                  type="text"
                  className="form-control"
                  name="loginId"
                  value={form.loginId}
                  onChange={handleChange}
                />
              </div>

              <div className="input-group mb-3">
                <div className="input-group-prepend">
                  <span className="input-group-text">PW</span>
                </div>
                <input
                  type="password"
                  className="form-control"
                  name="password"
                  value={form.password}
                  onChange={handleChange}
                />
              </div>

              <input type="submit" className="btn btn-block btn-info" value="로그인" />
            </form>

            <div className="d-flex justify-content-around mt-3">
              <div>
                <input
                  type="checkbox"
                  id="autoLogin"
                  checked={autoLogin}
                  onChange={(e) => setAutoLogin(e.target.checked)}
                />
                <label htmlFor="autoLogin"><span>자동로그인</span></label>
              </div>
              <div>
                <input
                  type="checkbox"
                  id="saveId"
                  checked={saveId}
                  onChange={(e) => setSaveId(e.target.checked)}
                />
                <label htmlFor="saveId"><span>아이디 저장</span></label>
              </div>
            </div>

            <div className="mt-4">
              <a className="btn btn-block btn-warning" href="#">카카오 로그인</a>
            </div>

            <div className="d-flex justify-content-around pr-2 mt-3">
              <a className="user-link" href="">아이디 찾기</a>
              <a className="user-link" href="">비밀번호 찾기</a>
              <a className="user-link" href="/user/sign_up_view">회원가입</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default SignIn;
