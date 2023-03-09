import axios from "axios";

const addBoard = async (id, title, content) => {
  await axios.post(
    `http://13.125.7.108:8080/api/board/save/${id}`,
    {
      title: title,
      content: content,
    },
    {
      headers: {
        Authorization: `Bearer ${sessionStorage.getItem("token")}`,
      },
    }
  );
};

export default addBoard;
