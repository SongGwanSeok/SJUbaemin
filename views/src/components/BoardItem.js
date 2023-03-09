import { useNavigate } from "react-router-dom";

const BoardItem = ({ id, title, content, member, reg_date }) => {
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
      <div className="reg_date">{reg_date}</div>
    </div>
  );
};

export default BoardItem;
