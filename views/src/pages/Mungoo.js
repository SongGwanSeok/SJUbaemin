import Header from "../components/Header.js";
import Footer from "../components/Footer.js";
import ContentItem from "../components/ContentItem.js";
import ContentTitle from "../components/ContentTitle.js";

const Mungoo = () => {
  return (
    <div className="Mungoo">
      <Header />

      <div className="Content">
        <ContentTitle
          title={"문구"}
          info={`사랑을 쓸려거든 연필로 쓰세요.
      사랑을 쓰다가 쓰다가 틀리면 지우개로 깨끗이 지워야 하니까.
      - <사랑은 연필로 쓰세요> 전영록, 1983`}
        />
        <hr />
        <ContentItem />
      </div>

      <Footer />
    </div>
  );
};

export default Mungoo;
