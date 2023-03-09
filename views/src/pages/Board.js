import axios from "axios";
import { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { DataContext } from "../App";
import BoardItem from "../components/BoardItem";
import Footer from "../components/Footer";
import Header from "../components/Header";

const Board = () => {
  const navigate = useNavigate();

  const { boardData } = useContext(DataContext);

  return (
    <div className="Board">
      <Header />
      <div className="content">
        <h2>게시판</h2>
        {boardData.map((it) => {
          return <BoardItem key={it.id} {...it} />;
        })}

        <button
          onClick={() => {
            navigate("/newboard");
          }}
        >
          글쓰기
        </button>
      </div>
      <Footer />
    </div>
  );
};

export default Board;
