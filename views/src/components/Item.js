import { useNavigate } from "react-router-dom";

const Item = ({ imageFilesPath, name, price, id }) => {
  const navigate = useNavigate();
  console.log(imageFilesPath);
  return (
    <div className="Item">
      <div
        onClick={() => {
          navigate(`/Detail/${id}`);
        }}
      >
        <img
          src={`http://13.125.7.108:8080/${imageFilesPath.slice(
            imageFilesPath.indexOf("image")
          )}`}
        />
        <div className="itemText">
          <div>{name}</div>
          <div>{price}원</div>
        </div>
      </div>
    </div>
  );
};

export default Item;
