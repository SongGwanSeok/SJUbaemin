const ContentTitle = ({ title, info }) => {
  return (
    <div className="ContentTitle">
      <h1 className="ContentName">{title}</h1>
      <p>{info}</p>
    </div>
  );
};

export default ContentTitle;
