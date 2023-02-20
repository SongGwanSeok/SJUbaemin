import { useState } from "react";
import { useNavigate } from "react-router-dom";
import Footer from "../components/Footer";
import Header from "../components/Header";
import MyButton from "../components/MyButton";
import axios from "axios";

const SERVER_URL = "http://13.125.7.108:8080";

const Login = () => {
  const navigate = useNavigate();

  const [userId, setUserId] = useState("");
  const [userPw, setUserPw] = useState("");

  const handleLogin = async () => {
    const { data } = await axios
      .post(`${SERVER_URL}/api/authenticate`, {
        loginId: userId,
        loginPw: userPw,
      })
      .catch(() => {
        alert("다시 입력하세요");
        setUserId("");
        setUserPw("");
      });

    sessionStorage.setItem("token", data.token);

    navigate("/");
  };

  return (
    <div className="Login">
      <Header />
      <div className="content">
        <h1>로그인</h1>
        <div className="input_wrapper">
          <input
            value={userId}
            onChange={(e) => {
              setUserId(e.target.value);
            }}
            type="text"
            placeholder="아이디"
          />
          <input
            value={userPw}
            onChange={(e) => {
              setUserPw(e.target.value);
            }}
            type="password"
            placeholder="비밀번호"
          />
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
