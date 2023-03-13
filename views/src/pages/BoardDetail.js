import { useContext } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { DataContext } from "../App";
import BoardHeader from "../components/BoardHeader";
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
  const handleRemove = () => {
    onBoardDelete(id);
    navigate(`/board`);
  };
  if (boardData.length === 0) return <div>로딩중...</div>;
  else
    return (
      <div className="BoardDetail">
        <Header />
        <div className="content">
          <BoardHeader
            text={targetBoardData.title}
            onClick={handleRemove}
            type="red"
            buttonText="삭제"
          />
          <div className="createDate">
            {new Date(targetBoardData.createDate).toLocaleString()}
          </div>
          <div className="boardContent">{targetBoardData.content}</div>
        </div>
        <Footer />
      </div>
    );
};

export default BoardDetail;
