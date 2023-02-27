import { useState } from "react";
import Header from "../components/Header";
import Input from "../components/Input";
import addUser from "../utils/addUser";

const SignUp = () => {
  const [id, setId] = useState("");
  const [pass, setPass] = useState("");
  const [checkPass, setCheckPass] = useState("");
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [birth, setBirth] = useState("");
  const [phone, setPhone] = useState("");
  const [address, setAddress] = useState("");

  return (
    <div className="SignUp">
      <Header />
      <div className="content">
        <h2>회원정보</h2>
        <div input_wrapper>
          <Input type="text" onChange={setId} placeholder={"아이디"} />
          <Input type="password" onChange={setPass} placeholder={"비밀번호"} />
          <Input
            type="password"
            onChange={setCheckPass}
            placeholder={"비밀번호 확인"}
          />
          <Input type="text" onChange={setName} placeholder={"이름"} />
          <Input type="text" onChange={setEmail} placeholder={"이메일"} />
          <Input type="text" onChange={setBirth} placeholder={"생일"} />
          <Input type="text" onChange={setPhone} placeholder={"휴대폰번호"} />
          <Input type="text" onChange={setAddress} placeholder={"주소"} />
          <button
            onClick={() =>
              addUser(id, pass, name, email, birth, phone, address)
            }
          >
            가입
          </button>
        </div>
      </div>
    </div>
  );
};
export default SignUp;
