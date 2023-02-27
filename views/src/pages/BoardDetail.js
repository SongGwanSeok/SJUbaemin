import Footer from "../components/Footer";
import Header from "../components/Header";

const BoardDetail = ({ title, content, writer, reg_date }) => {
  return (
    <div className="BoardDetail">
      <Header />
      <div className="content">
        <div className="title">{"title"}</div>
        <div className="writer">작성자: {"writer"}</div>
        <div className="reg_date">{"reg_date"}</div>
        {"content"}
      </div>
      <Footer />
    </div>
  );
};

export default BoardDetail;
