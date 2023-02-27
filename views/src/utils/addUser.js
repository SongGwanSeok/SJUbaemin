import axios from "axios";

const addUser = async (id, password, name, email, birth, phone, address) => {
  axios
    .post(
      `http://13.125.7.108:8080/api/member/signup`,
      {
        loginId: id,
        loginPw: password,
        name: name,
        email: email,
        birthday: birth,
        phone: phone,
        address: address,
      },
      {
        headers: {
          Authorization: `Bearer ${sessionStorage.getItem("token")}`,
        },
      }
    )
    .catch((res) => {
      alert(res);
    });
};

export default addUser;
