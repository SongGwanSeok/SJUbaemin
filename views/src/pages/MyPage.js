import axios from "axios";
import { useEffect } from "react";
import Header from "../components/Header";

const MyPage = () => {
  useEffect(() => {
    axios
      .get("http://13.125.7.108:8080/api/member/admin", {
        headers: { Authorization: sessionStorage.getItem("token") },
      })
      .then((res) => {
        console.log(res);
      });
  }, []);

  return (
    <div className="MyPage">
      <Header />
      <div className="content">
        <h3>마이페이지</h3>
        <div className="nemo">반가워요, {}님</div>
      </div>
    </div>
  );
};

export default MyPage;
