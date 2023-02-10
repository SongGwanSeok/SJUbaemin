import Header from "../components/Header.js";
import PageInfo from "../components/PageInfo.js";
import ItemList from "../components/ItemList.js";
import Footer from "../components/Footer.js";

const Home = () => {
  return (
    <div className="Home">
      <Header />
      <PageInfo />
      <ItemList />
      <Footer />
    </div>
  );
};

export default Home;
