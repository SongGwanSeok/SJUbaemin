import { useNavigate } from "react-router-dom";

const Item = ({ image, name, price, id }) => {
  const navigate = useNavigate();

  return (
    <div className="Item">
      <div
        onClick={() => {
          navigate(`/Detail/${id}`);
        }}
      >
        <img src={image} />
        <div className="itemText">
          <div>{name}</div>
          <div>{price.toLocaleString()}원</div>
        </div>
      </div>
    </div>
  );
};

export default Item;
