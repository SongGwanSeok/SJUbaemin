import { useState, useContext } from "react";
import { DataContext } from "../App";

import imageCompression from "browser-image-compression";
import axios from "axios";
import Header from "../components/Header";
import addItem from "../utils/addItem";
import removeItem from "../utils/removeItem";
import checkUserInfo from "../utils/checkUserInfo";

const Admin = () => {
  const data = useContext(DataContext);

  const [img, setImg] = useState();
  const [name, setName] = useState();
  const [price, setPrice] = useState();
  const [type, setType] = useState();
  const [search, setSearch] = useState("");
  const [searchData, setSearchData] = useState([]);
  const [userId, setUserId] = useState();
  const [userInfo, setUserInfo] = useState({});

  const searchItem = () => {
    setSearchData(
      data.filter((it) => {
        if (search !== "") return it.name.includes(search);
      })
    );
  };

  return (
    <div className="Admin">
      <Header />
      <div className="content">
        <div className="addItem">
          <input
            type="file"
            multiple
            onChange={(e) => {
              setImg(e.target.files);
            }}
          />
          <br />
          <input
            type="text"
            placeholder="상품명"
            onChange={(e) => {
              setName(e.target.value);
            }}
          />
          <br />
          <input
            type="number"
            placeholder="가격"
            onChange={(e) => {
              setPrice(e.target.value);
            }}
          />
          <br />
          <input
            type="text"
            placeholder="상품 타입"
            onChange={(e) => {
              setType(e.target.value);
            }}
          />
          <br />
          <button
            onClick={() => {
              actionImgCompress();
              // addItem(img, name, price, type);
            }}
          >
            상품 등록
          </button>
        </div>

        <div className="removeItem">
          <input
            type="text"
            placeholder="삭제할 아이템"
            onChange={(e) => {
              setSearch(e.target.value);
            }}
          />
          <button onClick={searchItem}>아이템 조회</button>
          {searchData.map((it) => {
            return (
              <div key={it.id}>
                <div>{it.img}</div>
                <div>{it.name}</div>
                <button
                  onClick={() => {
                    removeItem(it.id);
                  }}
                >
                  삭제
                </button>
              </div>
            );
          })}
        </div>

        <div className="checkUserInfo">
          <input
            type="text"
            placeholder="조회할 유저 아이디"
            onChange={(e) => {
              setUserId(e.target.value);
            }}
          />
          <button
            onClick={() =>
              checkUserInfo(userId)
                .then(({ data }) => setUserInfo(data))
                .catch(() => {
                  alert("존재하지 않는 회원입니다");
                  setUserInfo({});
                })
            }
          >
            유저정보 확인
          </button>
          <div className="userInfo">
            <div>
              {Object.keys(userInfo).map((it, idx) => (
                <div key={idx}>{it}</div>
              ))}
            </div>
            <div>
              {Object.values(userInfo).map((it, idx) => (
                <div key={idx}>{it}</div>
              ))}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Admin;
