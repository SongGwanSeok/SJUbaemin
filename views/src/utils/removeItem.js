import axios from "axios";

const removeItem = async (id) => {
  await axios.delete(`http://13.125.7.108:8080/api/product/delete/${id}`, {
    headers: {
      Authorization: `Bearer ${sessionStorage.getItem("token")}`,
    },
  });
};

export default removeItem;
