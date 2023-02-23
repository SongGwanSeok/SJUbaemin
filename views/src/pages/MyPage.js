import { useEffect } from "react";
import Header from "../components/Header";
import myInfo from "../utils/myInfo";

const MyPage = () => {
  useEffect(() => {
    myInfo().then((res) => {
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
