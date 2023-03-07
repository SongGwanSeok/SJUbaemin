import { useState, useContext, useEffect } from "react";
import { DataContext } from "../App";

import Header from "../components/Header";
import checkUserInfo from "../utils/checkUserInfo";

const Admin = () => {
  const { data, onAddItem, onDelete } = useContext(DataContext);

  useEffect(() => {
    searchItem();
  }, [data]);

  const [img, setImg] = useState();
  const [content, setContent] = useState();
  const [name, setName] = useState();
  const [price, setPrice] = useState();
  const [type, setType] = useState();
  const [search, setSearch] = useState("");
  const [searchData, setSearchData] = useState([]);
  const [userId, setUserId] = useState();
  const [userInfo, setUserInfo] = useState({});

  const searchItem = () => {
    if (search === "") {
      setSearchData(data);
      return;
    }
    setSearchData(
      data.filter((it) => {
        if (search !== "") return it.name?.includes(search);
      })
    );
  };

  return (
    <div className="Admin">
      <Header />
      <div className="content">
        <div className="addItem">
          <label>대표이미지</label>
          <input
            type="file"
            onChange={(e) => {
              setImg(e.target.files);
            }}
          />
          <br />
          <br />

          <label>상품설명</label>
          <input
            type="file"
            multiple
            onChange={(e) => {
              setContent(e.target.files);
            }}
          />
          <br />
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
          <select
            onChange={(e) => {
              setType(e.target.value);
            }}
          >
            <option selected hidden>
              타입
            </option>
            <option value="STATIONERY">문구</option>
            <option value="LIVING">리빙</option>
            <option value="BOOK">책</option>
          </select>
          <br />
          <button
            onClick={() => {
              onAddItem(img, content, name, price, type);
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
                <div>id : {it.id}</div>
                <img src={it.image} />
                <div>{it.name}</div>
                <button
                  onClick={() => {
                    onDelete(it.id);
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
