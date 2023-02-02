import { useState, useRef, useEffect, useMemo } from "react";
import "./App.css";
import DiaryEditor from "./DiaryEditor";
import DiaryList from "./DiaryList";

function App() {
  const [data, setData] = useState([]);
  const dataId = useRef(0);

  const getData = async () => {
    const dummy = await fetch(
      "https://jsonplaceholder.typicode.com/comments"
    ).then((res) => res.json());

    const initData = dummy.slice(0, 20).map((it) => {
      return {
        author: it.email,
        content: it.body,
        emotion: Math.floor(Math.random() * 5) + 1,
        created_date: new Date(),
        id: dataId.current++,
      }; // return 되어버리면 dataId 증가코드를 작성할 수 없으므로 후치연산자를 이용
    });

    setData(initData);
  };
  useEffect(() => {
    getData();
  }, []);

  const addItem = (author, content, emotion) => {
    const created_date = new Date();

    const newItem = {
      author,
      content,
      emotion,
      created_date,
      id: dataId.current,
    };
    setData([newItem, ...data]);

    dataId.current++;
  };

  const removeItem = (id) => {
    setData(data.filter((item) => item.id !== id));
  };

  const modifyItem = (modifyId, modifiedContent) => {
    setData(
      data.map((it) =>
        it.id === modifyId ? { ...it, content: modifiedContent } : it
      )
    );
  };

  const getDiaryAnalysis = useMemo(() => {
    const goodCount = data.filter((it) => it.emotion >= 3).length;

    const badCount = data.length - goodCount;
    const goodRatio = (goodCount / data.length) * 100;

    return { goodCount, badCount, goodRatio };
  }, [data.length]);

  const { goodCount, badCount, goodRatio } = getDiaryAnalysis;

  return (
    <div className="App">
      <DiaryEditor addItem={addItem} />
      <hr width="80%" />
      <div>전체일기 : {data.length}</div>
      <div>기분 좋은 일기 개수 : {goodCount}</div>
      <div>기분 나쁜 일기 개수 : {badCount}</div>
      <div>기분 좋은 일기 비율 : {Math.floor(goodRatio)}%</div>
      <DiaryList
        diaryList={data}
        removeItem={removeItem}
        modifyItem={modifyItem}
      />
    </div>
  );
}

export default App;
