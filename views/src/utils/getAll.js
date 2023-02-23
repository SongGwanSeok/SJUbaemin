import axios from "axios";

const getAll = async () => {
  return axios.get(`http://13.125.7.108:8080/api/product/all`);
};

export default getAll;
