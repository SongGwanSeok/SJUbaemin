const PageInfo = ({ title, info }) => {
  return (
    <div className="PageInfo">
      <h1>{title}</h1>
      <p>{info}</p>
    </div>
  );
};

export default PageInfo;
