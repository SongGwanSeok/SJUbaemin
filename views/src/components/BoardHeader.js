import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMagnifyingGlass } from "@fortawesome/free-solid-svg-icons";

const BoardHeader = ({
  text,
  onClick,
  type,
  buttonText,
  handleSearch,
  setSearch,
  isBoard,
}) => {
  return (
    <div className={`BoardHeader`}>
      <h2 className="contentTitle">{text}</h2>
      {isBoard && (
        <div className="search_wrapper">
          <input
            onChange={(e) => setSearch(e.target.value)}
            className="search"
            placeholder="게시물 제목"
            onKeyDown={(e) => {
              if (e.key == "Enter") handleSearch();
            }}
          />
          <FontAwesomeIcon
            className="searchIcon"
            icon={faMagnifyingGlass}
            onClick={() => handleSearch()}
          />
        </div>
      )}

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
