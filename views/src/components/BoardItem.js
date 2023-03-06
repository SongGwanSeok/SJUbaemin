import { useNavigate } from "react-router-dom";

const BoardItem = ({ title, writer, reg_date }) => {
  const id = 1;
  const navigate = useNavigate();
  return (
    <div
      onClick={() => {
        navigate(`/boarddetail/${id}`);
      }}
      className="BoardItem"
    >
      <div className="title">{"제목"}</div>
      <div className="writer">{"작성자"}</div>
      <div className="reg_date">{"날짜"}</div>
    </div>
  );
};

export default BoardItem;
