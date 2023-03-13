const BoardHeader = ({ text, onClick, type, buttonText }) => {
  return (
    <div className={`BoardHeader `}>
      <h2 className="contentTitle">{text}</h2>
      <button
        className={`boardButton ${type}`}
        onClick={() => {
          onClick();
        }}
      >
        {buttonText}
      </button>
    </div>
  );
};
export default BoardHeader;
