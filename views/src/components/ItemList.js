import { useState } from "react";

import Item from "./Item";

const sortOptionList = [
  { value: "latest", name: "최신순" },
  { value: "highPrice", name: "높은가격순" },
  { value: "lowPrice", name: "낮은가격순" },
];

const ControlMenu = ({ value, onChange, optionList }) => {
  return (
    <select
      className="ControlMenu"
      value={value}
      onChange={(e) => onChange(e.target.value)}
    >
      {optionList.map((it, idx) => (
        <option key={idx} value={it.value}>
          {it.name}
        </option>
      ))}
    </select>
  );
};

const ItemList = ({ data }) => {
  const [sortType, setSortType] = useState("latest");

  return (
    <div className="ItemList">
      <ControlMenu
        value={sortType}
        onChange={setSortType}
        optionList={sortOptionList}
      />
      <div className="Item_wrapper">
        {data.map((it) => (
          <Item key={it.id} {...it} />
        ))}
      </div>
    </div>
  );
};

ItemList.defaultProps = {
  data: [],
};

export default ItemList;
