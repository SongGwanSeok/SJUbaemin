import axios from "axios";
import { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { DataContext } from "../App";
import BoardHeader from "../components/BoardHeader";
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
        <BoardHeader
          text="게시판"
          onClick={() => {
            navigate("/newboard");
          }}
          buttonText="글쓰기"
          type="mint"
        />
        {boardData.map((it) => {
          return <BoardItem key={it.id} {...it} />;
        })}
      </div>
      <Footer />
    </div>
  );
};

export default Board;
