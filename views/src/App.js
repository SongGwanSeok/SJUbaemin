import { BrowserRouter, Route, Routes } from "react-router-dom";
import React, { useEffect, useState } from "react";

import Home from "./pages/Home";
import Mungoo from "./pages/Mungoo";
import Living from "./pages/Living";
import Book from "./pages/Book";
import Detail from "./pages/Detail";

import "./App.css";

export const DataContext = React.createContext();

function App() {
  const [data, setData] = useState([]);

  const getData = async () => {
    const res = await fetch("https://jsonplaceholder.typicode.com/photos").then(
      (res) => res.json()
    );

    const initData = res.slice(0, 20).map((it) => {
      return {
        img: it.url,
        name: it.title.slice(0, 6),
        price: 1000,
        id: it.id,
      };
    });

    setData(initData);
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
          </Routes>
        </div>
      </BrowserRouter>
    </DataContext.Provider>
  );
}

export default App;
