import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Link } from "react-router-dom";

const Header = () => {
  return (
    <div className="Header">
      <Link to={"/"}>
        <img
          className="logo"
          alt=""
          src={process.env.PUBLIC_URL + `/assets/logo.png`}
          width="200px"
        ></img>
      </Link>
      <div className="cate">
        <Link className="cateLink" to={"/mungoo"}>
          문구
        </Link>
        <Link className="cateLink" to={"/living"}>
          리빙
        </Link>
        <Link className="cateLink" to={"/book"}>
          책
        </Link>
      </div>
    </div>
  );
};

export default Header;
