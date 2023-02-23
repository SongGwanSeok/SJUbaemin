import { useEffect, useState } from "react";
import DefaultInfo from "../components/DefaultInfo";
import EditUserInfo from "../components/EditUserInfo";
import Footer from "../components/Footer";
import Header from "../components/Header";
import myInfo from "../utils/myInfo";

const MyPage = () => {
  let info = {};
  const [type, setType] = useState("default");

  useEffect(() => {
    myInfo().then(({ data }) => {
      info = data;
    });
  }, []);

  return (
    <div className="MyPage">
      <Header />

      <div className="content">
        <h2>마이페이지</h2>

        <div className="navbarContent_wrapper">
          <div className="navbar">
            <div className="shoppingInfo">
              <h3>쇼핑 정보</h3>
              <div>주문 / 배송조회</div>
              <div>취소 / 반품 / 교환 내역</div>
              <div>나의 상품 후기</div>
              <div>쿠폰함</div>
            </div>
            <div className="userInfo">
              <h3>회원 정보</h3>
              <div
                onClick={() => {
                  setType("edit");
                }}
              >
                회원정보 변경
              </div>
              <div>배송지 관리</div>
            </div>

            <div className="serviceCenter">
              <h3>고객 센터</h3>
              <div>1:1 문의 내역</div>
              <div>이메일 문의</div>
            </div>
          </div>

          <div className="myPageContent">
            {type === "default" && <DefaultInfo info={info} />}
            {type === "edit" && <EditUserInfo info={info} />}
          </div>
        </div>
      </div>

      <Footer />
    </div>
  );
};

export default MyPage;
