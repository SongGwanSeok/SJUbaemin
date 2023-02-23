import axios from "axios";

const removeItem = async (id) => {
  axios.delete(`http://13.125.7.108:8080/api/product/delete/${id}`);
};

export default removeItem;
