import { useContext } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { DataContext } from "../App";
import Footer from "../components/Footer";
import Header from "../components/Header";
import removeBoard from "../utils/removeBoard";

const BoardDetail = () => {
  const { id } = useParams();
  const navigate = useNavigate();

  const { boardData, onBoardDelete } = useContext(DataContext);
  const targetBoardData = boardData.find((it) => {
    return parseInt(it.id) === parseInt(id);
  });

  if (boardData.length === 0) return <div>로딩중...</div>;
  else
    return (
      <div className="BoardDetail">
        <Header />
        <div className="content">
          <div className="title">{targetBoardData.title}</div>
          <div className="writer">작성자: {targetBoardData.member.name}</div>
          <div className="reg_date">{targetBoardData.reg_date}</div>
          <div className="boardContent">{targetBoardData.content}</div>
          <button
            onClick={() => {
              onBoardDelete(id);
              navigate(`/board`);
            }}
          >
            삭제
          </button>
        </div>
        <Footer />
      </div>
    );
};

export default BoardDetail;
