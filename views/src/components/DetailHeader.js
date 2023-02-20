import { useState } from "react";
import MyButton from "./MyButton";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCartShopping } from "@fortawesome/free-solid-svg-icons";
import { Navigate, useNavigate } from "react-router-dom";

const ControlCounter = ({ count, handleDecrease, handleIncrease }) => {
  return (
    <div className="ControlCounter">
      <MyButton text={"-"} onClick={handleDecrease} />
      <input value={count} />
      <MyButton text={"+"} onClick={handleIncrease} />
    </div>
  );
};

const DetailHeader = ({ img, name, price, id }) => {
  const navigate = useNavigate();
  const [count, setCount] = useState(1);

  const handleIncrease = () => {
    setCount(count + 1);
  };

  const handleDecrease = () => {
    if (count === 0) return;
    setCount(count - 1);
  };
  return (
    <div className="DetailHeader">
      <div className="detailHeaderLeft">
        <h2> {name} </h2>
        <div> {price}원 </div>
      </div>
      <div className="detailHeaderCenter">
        <img src={img} />
      </div>
      <div className="detailHeaderRight">
        <div>
          <div className="deliInfo">
            <h4>배송정보</h4>
            <p>3,000원 (30,000원 이상 구매 시 무료)</p>
          </div>
          <div className="detailCount_wrapper">
            <div>{name} </div>
            <div className="controller">
              <ControlCounter
                count={count}
                handleIncrease={handleIncrease}
                handleDecrease={handleDecrease}
              />

              <div>{parseInt(price) * count}원</div>
            </div>
          </div>
          <div className="price_wrapper">
            <h4>총 금액</h4>
            <h3>{parseInt(price) * count}원</h3>
          </div>
          <div className="button_wrapper">
            <div className="cart">
              <MyButton
                text={<FontAwesomeIcon icon={faCartShopping} />}
                onClick={() => {
                  navigate("/");
                }}
              />
            </div>
            <div className="buy">
              <MyButton text={"바로 구매하기"} />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default DetailHeader;
