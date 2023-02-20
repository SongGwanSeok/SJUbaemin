import { BrowserRouter, Route, Routes } from "react-router-dom";
import React, { useEffect, useState } from "react";

import axios from "axios";

import Home from "./pages/Home";
import Mungoo from "./pages/Mungoo";
import Living from "./pages/Living";
import Book from "./pages/Book";
import Detail from "./pages/Detail";
import Login from "./pages/Login";
import MyPage from "./pages/MyPage";

import "./App.css";

export const DataContext = React.createContext();

function App() {
  const SERVER_URL = "http://13.125.7.108:8080";
  const [data, setData] = useState([]);

  //  --------------- 세배돈 서버 API 연결 -----------------

  const getData = async () => {
    const { data } = await axios.get(`${SERVER_URL}/api/product/all`);
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
            <Route path="/mypage" element={<MyPage />} />
          </Routes>
        </div>
      </BrowserRouter>
    </DataContext.Provider>
  );
}

export default App;
