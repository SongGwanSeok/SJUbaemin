import axios from "axios";
const addItem = async (img, name, price, type) => {
  const formData = new FormData();
  formData.append("file", img);
  formData.append(
    "data",
    new Blob([JSON.stringify({ name: name, price: price, type: type })], {
      type: "application/json",
    })
  );
  axios
    .post(`http://13.125.7.108:8080/api/product/register`, formData, {
      headers: {
        Authorization: `Bearer ${sessionStorage.getItem("token")}`,
        "Content-Type": `multipart/form-data`,
      },
    })
    .then((res) => console.log(res))
    .catch((res) => console.log(res));
};

export default addItem;
