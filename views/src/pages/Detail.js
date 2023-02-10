import { useParams } from "react-router-dom";

const Detail = () => {
  const { id } = useParams();

  return (
    <div className="Detail">
      <h1>{id}번째 아이템 상세페이지 입니당</h1>
    </div>
  );
};
export default Detail;
