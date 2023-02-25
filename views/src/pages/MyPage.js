import { useEffect, useState } from "react";
import DefaultInfo from "../components/DefaultInfo";
import EditUserInfo from "../components/EditUserInfo";
import Footer from "../components/Footer";
import Header from "../components/Header";
import NullInfo from "../components/NullInfo";
import myInfo from "../utils/myInfo";

const MyPage = () => {
  const [info, setInfo] = useState({});
  const [type, setType] = useState("default");

  useEffect(() => {
    myInfo().then(({ data }) => {
      setInfo(data);
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
              <div
                onClick={() => {
                  setType("buy");
                }}
              >
                주문 / 배송조회
              </div>
              <div
                onClick={() => {
                  setType("change");
                }}
              >
                취소 / 반품 / 교환 내역
              </div>
              <div
                onClick={() => {
                  setType("review");
                }}
              >
                나의 상품 후기
              </div>
              <div
                onClick={() => {
                  setType("coopon");
                }}
              >
                쿠폰함
              </div>
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
              <div
                onClick={() => {
                  setType("delivery");
                }}
              >
                배송지 관리
              </div>
            </div>

            <div className="serviceCenter">
              <h3>고객 센터</h3>
              <div
                onClick={() => {
                  setType("ask");
                }}
              >
                1:1 문의 내역
              </div>
              <div
                onClick={() => {
                  setType("emailAsk");
                }}
              >
                이메일 문의
              </div>
            </div>
          </div>

          <div className="myPageContent">
            {type === "default" && <DefaultInfo info={info} />}
            {type === "buy" && <NullInfo text={"주문내역"} />}
            {type === "change" && <NullInfo text={"반품내역"} />}
            {type === "edit" && <EditUserInfo info={info} />}
            {type === "coopon" && <NullInfo text={"쿠폰"} />}
            {type === "review" && <NullInfo text={"후기"} />}
            {type === "delivery" && <NullInfo text={"배송지"} />}
            {type === "ask" && <NullInfo text={"1:1 문의 내역"} />}
            {type === "emailAsk" && <NullInfo text={"이메일 문의 내역"} />}
          </div>
        </div>
      </div>

      <Footer />
    </div>
  );
};

export default MyPage;
