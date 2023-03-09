import axios from "axios";

const getBoardAll = async () => {
  return await axios.get(`http://13.125.7.108:8080/api/board/findAll`);
};

export default getBoardAll;
