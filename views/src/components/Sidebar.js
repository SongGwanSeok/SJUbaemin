import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faXmark } from "@fortawesome/free-solid-svg-icons";

import { Link, useNavigate } from "react-router-dom";
import myInfo from "../utils/myInfo";
import { useState } from "react";

const Sidebar = ({ toggle, setToggle }) => {
  const navigate = useNavigate();
  const [name, setName] = useState();

  const getName = () => {
    myInfo().then(({ data }) => {
      setName(data.name);
    });
    return <span className="userName">{name}</span>;
  };

  return (
    <div className={`Sidebar ${toggle ? "on" : ""}`}>
      <div className="x_wrapper">
        <FontAwesomeIcon
          className="x"
          icon={faXmark}
          onClick={() => {
            setToggle(false);
          }}
        />
      </div>

      <div className="sideContent">
        {sessionStorage.getItem("token") ? (
          <div className="niceTo" onClick={() => navigate("/mypage")}>
            {getName()}님 <br />
            나이스 투 미츄!
          </div>
        ) : (
          <h1 onClick={() => navigate("/login")}>
            앗! <br />
            로그인이 필요해요
          </h1>
        )}
        <div className="cateTitle">카테고리</div>
        <div className="cate">
          <Link className="cateLink" to={"/"}>
            전체보기
          </Link>
          <Link className="cateLink" to={"/mungoo"}>
            문구
          </Link>
          <Link className="cateLink" to={"/living"}>
            리빙
          </Link>
          <Link className="cateLink" to={"/book"}>
            책
          </Link>
          <Link className="cateLink" to={"/board"}>
            게시판
          </Link>
        </div>
      </div>
    </div>
  );
};

export default Sidebar;
