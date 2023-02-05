const Item = ({ itemImg, itemName, itemPrice }) => {
  return (
    <div className="Item">
      <div>{itemImg}</div>
      <div>{itemName}</div>
      <div>{itemPrice}</div>
    </div>
  );
};

export default Item;
