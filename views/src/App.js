import { BrowserRouter, Route, Routes } from "react-router-dom";
import React, { useEffect, useState } from "react";

import Home from "./pages/Home";
import Mungoo from "./pages/Mungoo";
import Living from "./pages/Living";
import Book from "./pages/Book";
import Detail from "./pages/Detail";
import Login from "./pages/Login";
import MyPage from "./pages/MyPage";
import Admin from "./pages/Admin";
import SignUp from "./pages/SignUp";

import addItem from "./utils/addItem";
import getAll from "./utils/getAll";
import removeItem from "./utils/removeItem";

import "./App.css";
import Search from "./pages/Search";
import Board from "./pages/Board";
import BoardDetail from "./pages/BoardDetail";

export const DataContext = React.createContext();

function App() {
  const [data, setData] = useState([]);

  useEffect(() => {
    getAll("all").then(({ data }) => setData(data.data));
  }, []);

  const onAddItem = async (titleImg, contentImg, name, price, type) => {
    addItem(titleImg, contentImg, name, price, type);

    setTimeout(() => {
      getAll("all").then(({ data }) => {
        setData(data.data);
      });
    }, 500);
  };

  const onDelete = async (id) => {
    await removeItem(id);

    getAll("all").then(({ data }) => {
      setData(data.data);
    });
  };

  return (
    <DataContext.Provider value={{ data, onAddItem, onDelete }}>
      <BrowserRouter>
        <div className="App">
          <Routes>
            <Route path="/" element={<Home data={data} />} />
            <Route path="/mungoo" element={<Mungoo />} />
            <Route path="/living" element={<Living />} />
            <Route path="/book" element={<Book />} />
            <Route path="/detail/:id" element={<Detail data={data} />} />
            <Route path="/login" element={<Login />} />
            <Route path="/mypage" element={<MyPage />} />
            <Route path="/admin" element={<Admin />} />
            <Route path="/signup" element={<SignUp />} />
            <Route path="/search" element={<Search />} />
            <Route path="/board" element={<Board />} />
            <Route path="/boarddetail/:id" element={<BoardDetail />} />
          </Routes>
        </div>
      </BrowserRouter>
    </DataContext.Provider>
  );
}

export default App;
