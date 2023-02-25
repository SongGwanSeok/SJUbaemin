import axios from "axios";

const myInfo = () => {
  return axios.get(`http://13.125.7.108:8080/api/member`, {
    headers: {
      Authorization: `Bearer ${sessionStorage.getItem("token")}`,
    },
  });
};

export default myInfo;
