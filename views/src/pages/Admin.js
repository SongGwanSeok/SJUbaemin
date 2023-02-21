import { useState, useContext } from "react";
import axios from "axios";
import { DataContext } from "../App";

import Header from "../components/Header";
import Item from "../components/Item";

const Admin = () => {
  const data = useContext(DataContext);

  const [file, setFile] = useState();
  const [name, setName] = useState();
  const [price, setPrice] = useState();
  const [type, setType] = useState();
  const [search, setSearch] = useState("");
  const [searchData, setSearchData] = useState([]);
  const [userId, setUserId] = useState();

  const addItem = async () => {
    await axios.post(`http://13.125.7.108:8080/api/product/register`, {
      img: file,
      name: name,
      price: price,
      type: type,
    });
  };

  const tmp = [
    { name: "abcd", id: 1 },
    { name: "abc", id: 2 },
    { name: "ab", id: 3 },
  ];

  const searchItem = () => {
    setSearchData(
      tmp.filter((it) => {
        if (search !== "") return it.name.includes(search);
      })
    );
  };

  const removeItem = async (id) => {
    await axios.delete(`http://13.125.7.108:8080/api/product/delete/${id}`, {
      headers: {
        Authorization: `Bearer ${sessionStorage.getItem("token")}`,
      },
    });
  };

  const checkUserInfo = () => {
    axios
      .get(`http://13.125.7.108:8080/api/member/${userId}}`, {
        headers: {
          Authorization: `Bearer ${sessionStorage.getItem("token")}`,
        },
      })
      .then((res) => {
        return <div>{res.name}</div>;
      });
  };

  return (
    <div className="Admin">
      <Header />
      <div className="content">
        <div className="addItem">
          <input
            type="file"
            onChange={(e) => {
              setFile(e.target.files);
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
          <button onClick={addItem}> 상품 등록 </button>
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
              <div>
                <div>{it.img}</div>
                <div>{it.name}</div>
                <button
                  onClick={(it) => {
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
          <button onClick={checkUserInfo}>유저정보 확인</button>
        </div>
      </div>
    </div>
  );
};

export default Admin;
