import Header from "../components/Header.js";
import ItemList from "../components/ItemList.js";
import Footer from "../components/Footer.js";
import PageInfo from "../components/PageInfo.js";

const Home = ({ data }) => {
  return (
    <div className="Home">
      <Header />
      <div className="content">
        <PageInfo title={"전체"} />
        <hr />

        <ItemList data={data} />
      </div>
      <Footer />
    </div>
  );
};

export default Home;
