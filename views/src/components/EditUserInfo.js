import NullInfo from "./NullInfo";

const EditUserInfo = ({ info }) => {
  if (Object.keys(info).length > 0) return <div className="EditUserInfo"></div>;
  else return <NullInfo text="회원정보" />;
};

export default EditUserInfo;
