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
  const [search, setSearch] = useState("");
  const [searchData, setSearchData] = useState(boardData);

  useEffect(() => {
    setSearchData(boardData);
  }, [boardData]);

  const handleSearch = () => {
    axios
      .get(`http://13.125.7.108:8080/api/board/search?keyword=${search}`, {
        headers: {
          Authorization: `Bearer ${sessionStorage.getItem("token")}`,
        },
      })
      .then(({ data }) => setSearchData(data));
  };

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
          handleSearch={handleSearch}
          setSearch={setSearch}
          isBoard={true}
        />
        <div className="boardContent">
          {searchData.map((it) => {
            return <BoardItem key={it.id} {...it} />;
          })}
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default Board;
