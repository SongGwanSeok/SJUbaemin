import { useContext, useEffect, useState } from "react";
import Footer from "../components/Footer";
import Header from "../components/Header";
import myInfo from "../utils/myInfo";
import addBoard from "../utils/addBoard";
import { useNavigate } from "react-router-dom";
import { DataContext } from "../App";

const NewBoard = () => {
  const { onAddBoard } = useContext(DataContext);

  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [id, setId] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    myInfo().then(({ data }) => {
      setId(data.id);
    });
  }, []);

  return (
    <div className="NewBoard">
      <Header />
      <div className="content">
        <input
          type="text"
          placeholder="제목"
          onChange={(e) => {
            setTitle(e.target.value);
          }}
          value={title}
          className="title"
        />
        <textarea
          placeholder="내용을 입력하세요"
          onChange={(e) => {
            setContent(e.target.value);
          }}
          value={content}
          className="boardContent"
        />

        <button
          onClick={() => {
            onAddBoard(id, title, content).then(() => {
              alert("저장 되었습니다");
              navigate("/board");
            });
          }}
        >
          저장
        </button>
      </div>
      <Footer />
    </div>
  );
};

export default NewBoard;
