import { useRef, useState } from "react";
import { Navigate, useNavigate } from "react-router-dom";
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

  const navigate = useNavigate();

  const passref = useRef();

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
          passref={passref}
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
          onClick={() => {
            if (pass === checkPass)
              addUser(id, pass, name, email, birth, phone, address)
                .then((res) => {
                  alert("성공");
                  navigate("/");
                })
                .catch((err) => {
                  alert("아이디가 중복이거나 제대로 입력 안함");
                });
            else passref.current.focus();
          }}
        >
          가입
        </button>
      </div>
    </div>
  );
};
export default SignUp;
