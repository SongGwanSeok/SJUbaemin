const Detail = () => {
  return (
    <BrowserRouter>
      <div className="Detail">
        <Routes>
          <Route path="/mungoo:id" element={<Detail />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
};
export default Detail;
