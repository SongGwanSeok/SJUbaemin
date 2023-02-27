import axios from "axios";

const addUser = async (id, pass, name, email, birth, phone, address) => {
  axios
    .post(
      `http://13.125.7.108:8080/api/member/signup`,

      {
        loginId: id,
        loginPw: pass,
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

    .catch((err) => {
      if (err.response.status === 500) {
        alert("이미 있는 아이디입니다.");
      }
    });
};

export default addUser;
