import { useNavigate } from "react-router-dom";
import Footer from "../components/Footer";
import Header from "../components/Header";
import MyButton from "../components/MyButton";

const Login = () => {
  const navigate = useNavigate();
  const handleLogin = () => {
    // 아이디랑 비밀번호가 유효한지 확인
    return;
  };

  return (
    <div className="Login">
      <Header />
      <div className="content">
        <h1>로그인</h1>
        <div className="input_wrapper">
          <input type="text" placeholder="아이디" />
          <input type="password" placeholder="비밀번호" />
          <MyButton text="로그인" onClick={handleLogin} />
        </div>
        <div className="newId">
          <div>회원가입</div>
          <div className="center">아이디 찾기</div>
          <div>비밀번호 찾기</div>
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default Login;
