import { BrowserRouter, Route, Routes } from "react-router-dom";
import React, { useEffect, useState } from "react";

import axios from "axios";

import Home from "./pages/Home";
import Mungoo from "./pages/Mungoo";
import Living from "./pages/Living";
import Book from "./pages/Book";
import Detail from "./pages/Detail";
import Login from "./pages/Login";

import "./App.css";

export const DataContext = React.createContext();

function App() {
  const SERVER_URL = "http://13.125.7.108:8080/api/";
  const [data, setData] = useState([]);

  // ---------------- axios 사용 ----------------

  // const getData = async () => {
  //   const res = await axios.get("https://jsonplaceholder.typicode.com/photos");

  //   const initData = res.data.slice(0, 20).map((it) => {
  //     return {
  //       img: it.url,
  //       name: it.title.slice(0, 6),
  //       price: 1000,
  //       id: it.id,
  //     };
  //   });

  //   setData(initData);
  // };

  // -------------fetch 사용 ----------------

  // const getData = async () => {
  //   const datas = await fetch(
  //     "https://jsonplaceholder.typicode.com/photos"
  //   ).then((res) => res.json());

  //   const initData = datas.slice(0, 20).map((it) => {
  //     return {
  //       img: it.url,
  //       name: it.title.slice(0, 6),
  //       price: 1000,
  //       id: it.id,
  //     };
  //   });

  //   setData(initData);
  // };

  //  --------------- 세배돈 서버 API 연결 -----------------

  const getData = async () => {
    const { data } = await axios.get(`${SERVER_URL}product/all`);
    setData(data.data);
  };

  useEffect(() => {
    getData();
  }, []);

  return (
    <DataContext.Provider value={data}>
      <BrowserRouter>
        <div className="App">
          <Routes>
            <Route path="/" element={<Home data={data} />} />
            <Route path="/mungoo" element={<Mungoo data={data} />} />
            <Route path="/living" element={<Living data={data} />} />
            <Route path="/book" element={<Book data={data} />} />
            <Route path="/detail/:id" element={<Detail data={data} />} />
            <Route path="/login" element={<Login />} />
          </Routes>
        </div>
      </BrowserRouter>
    </DataContext.Provider>
  );
}

export default App;
