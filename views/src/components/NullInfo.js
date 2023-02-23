const NullInfo = ({ text }) => {
  return (
    <div>
      <h2>{text}</h2>
      <div className="NullInfo">
        <h1>앗!</h1>
        <div>{text}가 없습니다</div>
      </div>
    </div>
  );
};

export default NullInfo;
