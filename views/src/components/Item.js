import { useNavigate } from "react-router-dom";

const Item = ({ img, name, price, id }) => {
  const navigate = useNavigate();

  return (
    <div className="Item">
      <div
        onClick={() => {
          navigate(`/Detail/${id}`);
        }}
      >
        <img src={img} />
        <div className="itemText">
          <div>{name}</div>
          <div>{price}</div>
        </div>
      </div>
    </div>
  );
};

export default Item;
