import Header from "../components/Header.js";
import ItemList from "../components/ItemList.js";
import Footer from "../components/Footer.js";

const Home = ({ data }) => {
  return (
    <div className="Home">
      <Header />
      <div className="content">
        <ItemList data={data} />
      </div>
      <Footer />
    </div>
  );
};

export default Home;
