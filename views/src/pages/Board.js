import axios from "axios";
import BoardItem from "../components/BoardItem";
import Footer from "../components/Footer";
import Header from "../components/Header";

const Board = () => {
  axios
    .get(`http://13.125.7.108:8080/api/board/findAll`)
    .then(({ data }) => console.log(data));
  return (
    <div className="Board">
      <Header />
      <div className="content">
        <h2>게시판</h2>
        <BoardItem />
        <BoardItem />
        <BoardItem />
        <BoardItem />
      </div>
      <Footer />
    </div>
  );
};

export default Board;
