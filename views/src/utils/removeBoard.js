import axios from "axios";

const removeBoard = async (id) => {
  await axios.delete(`http://13.125.7.108:8080/api/board/delete/${id}`, {
    headers: {
      Authorization: `Bearer ${sessionStorage.getItem("token")}`,
    },
  });
};

export default removeBoard;