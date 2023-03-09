import Footer from "../components/Footer";
import Header from "../components/Header";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMagnifyingGlass } from "@fortawesome/free-solid-svg-icons";
import { useState } from "react";
import Item from "../components/Item";

import { DataContext } from "../App";
import { useContext } from "react";
import ItemList from "../components/ItemList";

const Search = () => {
  const [searchData, setSearchData] = useState([]);
  const [search, setSearch] = useState("");
  const { data } = useContext(DataContext);

  const searchItem = () => {
    setSearchData(
      data.filter((it) => {
        if (search !== "") return it.name.includes(search);
      })
    );
  };
  return (
    <div className="Search">
      <Header />
      <div className="content">
        <div className="search_wrapper">
          <input
            onChange={(e) => setSearch(e.target.value)}
            className="search"
            placeholder="검색어를 입력하세요"
            onKeyDown={(e) => {
              if (e.key == "Enter") searchItem();
            }}
          />
          <FontAwesomeIcon
            className="searchIcon"
            icon={faMagnifyingGlass}
            onClick={() => searchItem()}
          />
        </div>

        <ItemList data={searchData} />
      </div>
      <Footer />
    </div>
  );
};

export default Search;
