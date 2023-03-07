import axios from "axios";

const getAll = async (type) => {
  return type !== "all"
    ? await axios.get(`http://13.125.7.108:8080/api/product/type/${type}`)
    : await axios.get(`http://13.125.7.108:8080/api/product/all`);
};

export default getAll;
