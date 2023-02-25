const NullInfo = ({ text }) => {
  return (
    <div>
      <h2>{text}</h2>
      <div className="NullInfo">
        <h1>앗!</h1>
        <div>
          {`${text}${
            (text.charCodeAt(text.length - 1) - 44032) % 28 === 0 ? "가" : "이"
          }`}{" "}
          없습니다.
        </div>
      </div>
    </div>
  );
};

export default NullInfo;
