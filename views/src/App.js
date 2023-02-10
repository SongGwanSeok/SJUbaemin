import Home from "./pages/Home";
import Mungoo from "./pages/Mungoo";
import Living from "./pages/Living";
import Book from "./pages/Book";

import { BrowserRouter, Route, Routes } from "react-router-dom";

import "./App.css";

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <h1>hi 123</h1>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/mungoo" element={<Mungoo />} />
          <Route path="/living" element={<Living />} />
          <Route path="/book" element={<Book />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
