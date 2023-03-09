import axios from "axios";
import imageCompression from "browser-image-compression";

const addItem = async (titleImg, contentImg, name, price, type) => {
  const fileSrc = [...titleImg, ...contentImg];
  const options = {
    maxSizeMB: 0.2,
    maxWidthOrHeight: 1920,
    useWebWorker: true,
  };

  const base64datas = await Promise.all(
    fileSrc.map(async (it) => {
      const compressedFile = await imageCompression(it, options);
      const reader = new FileReader();
      reader.readAsDataURL(compressedFile);
      return new Promise((resolve) => {
        reader.onloadend = () => {
          resolve(reader.result);
        };
      });
    })
  );

  postItem(name, price, type, base64datas);
};

const postItem = async (name, price, type, base64datas) => {
  await axios.post(
    `http://13.125.7.108:8080/api/product/enroll`,
    {
      name: name,
      price: price,
      type: type,
      content: base64datas.slice(1),
      image: base64datas[0],
    },
    {
      headers: {
        Authorization: `Bearer ${sessionStorage.getItem("token")}`,
      },
    }
  );
};

export default addItem;
