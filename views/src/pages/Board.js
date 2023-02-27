import BoardItem from "../components/BoardItem";
import Footer from "../components/Footer";
import Header from "../components/Header";

const Board = () => {
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
