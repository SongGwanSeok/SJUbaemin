import { useParams } from "react-router-dom";
import Header from "../components/Header";
import { DataContext } from "../App.js";
import React from "react";
import DetailHeader from "../components/DetailHeader";

const Detail = () => {
  const { id } = useParams();
  const { data } = React.useContext(DataContext);
  const targetItem = data.find((it) => {
    return parseInt(it.id) === parseInt(id);
  });

  if (data.length === 0) return null;
  else
    return (
      <div className="Detail">
        <Header />
        <div className="content">
          <DetailHeader {...targetItem} />
          <div className="DetailContent">
            {targetItem.content.map((it, idx) => {
              // return <img src={it.content} />;
              return <img key={idx} src={it} />;
            })}
          </div>
        </div>
      </div>
    );
};
export default Detail;
