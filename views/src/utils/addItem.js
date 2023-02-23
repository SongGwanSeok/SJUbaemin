import axios from "axios";

const addItem = async (img, name, price, type) => {
  axios.post(`http://13.125.7.108:8080/api/product/register`, {
    img: img,
    name: name,
    price: price,
    type: type,
  });
};

export default addItem;
