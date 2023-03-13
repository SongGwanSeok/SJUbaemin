import { useNavigate } from "react-router-dom";

const BoardItem = ({ id, title, content, member, createDate }) => {
  const navigate = useNavigate();
  return (
    <div
      onClick={() => {
        navigate(`/boarddetail/${id}`);
      }}
      className="BoardItem"
    >
      <div className="title">{title}</div>
      <div className="writer">{member.name}</div>
      <div className="createDate">{new Date(createDate).toLocaleString()}</div>
    </div>
  );
};

export default BoardItem;
