import axios from "axios";
const addItem = async (img, name, price, type) => {
  console.log(img[0]);
  console.log(typeof img[0]);
  const formData = new FormData();
  formData.append("file", img[0]);
  formData.append(
    "data",
    new Blob([
      JSON.stringify({
        name: name,
        price: price,
        type: type,
        content: "content",
        quantity: 100,
      }),
      {
        type: "application/json",
      },
    ])
  );

  axios.post(`http://13.125.7.108:8080/api/product/register`, formData, {
    headers: {
      Authorization: `Bearer ${sessionStorage.getItem("token")}`,
      "Content-Type": `multipart/form-data`,
    },
  });
};

export default addItem;
