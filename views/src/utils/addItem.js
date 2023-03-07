import axios from "axios";
import imageCompression from "browser-image-compression";

const addItem = async (img, content, name, price, type) => {
  const fileSrc = img[0];
  const options = {
    maxSizeMB: 0.2,
    maxWidthOrHeight: 1920,
    useWebWorker: true,
  };
  const compressedFile = await imageCompression(fileSrc, options);
  const reader = new FileReader();
  reader.readAsDataURL(compressedFile);
  reader.onloadend = () => {
    const base64data = reader.result;
    postItem(content, name, price, type, base64data);
  };
};

const postItem = async (content, name, price, type, base64data) => {
  await axios.post(
    `http://13.125.7.108:8080/api/product/enroll`,
    {
      name: name,
      price: price,
      type: type,
      content: ["img1", "img2", "img3"],
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
