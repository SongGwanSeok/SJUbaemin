import { useState } from "react";
import { Navigate, useNavigate } from "react-router-dom";
import Header from "../components/Header";
import Input from "../components/Input";
import AddUser from "../utils/addUser";

const SignUp = () => {
  const [id, setId] = useState("");
  const [pass, setPass] = useState("");
  const [checkPass, setCheckPass] = useState("");
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [birth, setBirth] = useState("");
  const [phone, setPhone] = useState("");
  const [address, setAddress] = useState("");

  const navigate = useNavigate();
  return (
    <div className="SignUp">
      <Header />
      <div className="content">
        <h2>회원정보</h2>
        <Input type="text" onChange={setId} placeholder={"아이디"} />
        <Input type="password" onChange={setPass} placeholder={"비밀번호"} />
        <Input
          type="password"
          onChange={setCheckPass}
          placeholder={"비밀번호 확인"}
        />
        <div className="same">
          {pass === "" || checkPass === ""
            ? ""
            : pass === checkPass
            ? "비밀번호가 일치합니다."
            : "비밀번호가 일치하지 않습니다."}
        </div>
        <Input type="text" onChange={setName} placeholder={"이름"} />
        <Input type="text" onChange={setEmail} placeholder={"이메일"} />
        <Input type="text" onChange={setBirth} placeholder={"생일"} />
        <Input type="text" onChange={setPhone} placeholder={"휴대폰번호"} />
        <Input type="text" onChange={setAddress} placeholder={"주소"} />

        <button
          onClick={() =>
            AddUser(id, pass, name, email, birth, phone, address)
              .then((res) => {
                console.log(res);
                alert("성공");
                navigate("/");
              })
              .catch((err) => {
                if (err.response.status === 500) {
                }
                if (err.response.status === 401) {
                  alert("이미 있는 아이디입니다.");
                }
              })
          }
        >
          가입
        </button>
      </div>
    </div>
  );
};
export default SignUp;
