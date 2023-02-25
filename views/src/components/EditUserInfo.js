import NullInfo from "./NullInfo";

const EditUserInfo = ({ info }) => {
  if (Object.keys(info).length > 0)
    return (
      <div className="EditUserInfo">
        <h2>회원정보</h2>
        {Object.values(info).map((it) => (
          <div>{it}</div>
        ))}
      </div>
    );
  else return <NullInfo text="회원정보" />;
};

export default EditUserInfo;
