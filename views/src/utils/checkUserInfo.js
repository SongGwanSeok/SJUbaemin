import axios from "axios";

const checkUserInfo = (userId) => {
  return axios.get(`http://13.125.7.108:8080/api/member/${userId}`, {
    headers: {
      Authorization: `Bearer ${sessionStorage.getItem("token")}`,
    },
  });
};

export default checkUserInfo;
