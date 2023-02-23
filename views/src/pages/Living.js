import Header from "../components/Header.js";
import Footer from "../components/Footer.js";
import ItemList from "../components/ItemList.js";
import PageInfo from "../components/PageInfo.js";

const Living = ({ data }) => {
  return (
    <div className="Living">
      <Header />

      <div className="content">
        <PageInfo
          title={"리빙"}
          info={`사랑을 쓸려거든 연필로 쓰세요.
    사랑을 쓰다가 쓰다가 틀리면 지우개로 깨끗이 지워야 하니까.
    - <사랑은 연필로 쓰세요> 전영록, 1983`}
        />
        <hr />
        <ItemList data={data} />
      </div>

      <Footer />
    </div>
  );
};

export default Living;