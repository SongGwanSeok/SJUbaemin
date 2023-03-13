import { useContext, useEffect, useState } from "react";
import Footer from "../components/Footer";
import Header from "../components/Header";
import myInfo from "../utils/myInfo";
import addBoard from "../utils/addBoard";
import { useNavigate } from "react-router-dom";
import { DataContext } from "../App";
import BoardHeader from "../components/BoardHeader";

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

  const handleSubmit = () => {
    onAddBoard(id, title, content).then(() => {
      alert("저장 되었습니다");
      navigate("/board");
    });
  };

  return (
    <div className="NewBoard">
      <Header />
      <div className="content">
        <BoardHeader
          text="글쓰기"
          onClick={handleSubmit}
          type="mint"
          buttonText="저장"
        />

        <div className="content_wrapper">
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
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default NewBoard;
