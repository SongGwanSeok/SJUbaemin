import axios from "axios";
import { useEffect } from "react";
import Header from "../components/Header";

const MyPage = () => {
  useEffect(() => {
    axios
      .get("http://13.125.7.108:8080/api/member", {
        headers: {
          Authorization: `Bearer ${sessionStorage.getItem("token")}`,
        },
      })
      .then((res) => {
        console.log(res);
      });
  }, []);

  return (
    <div className="MyPage">
      <Header />
      <div className="content"></div>
    </div>
  );
};

export default MyPage;
