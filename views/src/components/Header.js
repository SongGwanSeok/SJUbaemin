import { Link, useNavigate } from "react-router-dom";
import MyButton from "./MyButton";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faMagnifyingGlass,
  faCartShopping,
  faBars,
} from "@fortawesome/free-solid-svg-icons";
import { faUser } from "@fortawesome/free-regular-svg-icons";
import myInfo from "../utils/myInfo";
import Sidebar from "./Sidebar";
import { useState } from "react";

const Header = () => {
  const navigate = useNavigate();
  const [toggle, setToggle] = useState(false);
  return (
    <div className="Header">
      <div className="headerLeft">
        <Link to={"/"}>
          <img
            className="logo"
            alt=""
            src={process.env.PUBLIC_URL + `/assets/logo.png`}
            width="200px"
          ></img>
        </Link>
      </div>
      <div className="headerCenter">
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

      <div className="headerRight">
        <FontAwesomeIcon
          icon={faMagnifyingGlass}
          onClick={() => navigate("/search")}
        />
        {/* <FontAwesomeIcon icon={faCartShopping} /> */}

        {sessionStorage.getItem("token") ? (
          <span>
            <FontAwesomeIcon
              icon={faUser}
              onClick={() => {
                myInfo().then(({ data }) => {
                  data.admin ? navigate("/admin") : navigate("/mypage");
                });
              }}
            />

            <MyButton
              text={"로그아웃"}
              onClick={() => {
                sessionStorage.removeItem("token");
                navigate("/");
              }}
            />
          </span>
        ) : (
          <MyButton text={"로그인"} onClick={() => navigate("/login")} />
        )}

        <FontAwesomeIcon
          icon={faBars}
          onClick={() => {
            setToggle(true);
          }}
        />
      </div>
      {<Sidebar toggle={toggle} setToggle={setToggle} />}
    </div>
  );
};

export default Header;
