import axios from "axios";

const addItem = async (img, name, price, type) => {
  console.log("압축 시작");
  const fileSrc = img[0];
  const options = {
    maxSizeMB: 0.2,
    maxWidthOrHeight: 1920,
    useWebWorker: true,
  };

  const base64data = undefined;

  const compressedFile = await imageCompression(fileSrc, options);
  const reader = new FileReader();
  reader.readAsDataURL(compressedFile);
  reader.onloadend = () => {
    base64data = reader.result;
  };

  return axios.post(
    `http://13.125.7.108:8080/api/product/enroll`,
    {
      name: name,
      price: price,
      type: type,
      content: "content",
      quantity: 100,
      image: base64data,
    },
    {
      headers: {
        Authorization: `Bearer ${sessionStorage.getItem("token")}`,
      },
    }
  );
};

export default addItem;
