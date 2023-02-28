const Input = ({ type, onChange, placeholder, passref }) => {
  return (
    <input
      ref={passref}
      className="Input"
      type={type}
      onChange={(e) => onChange(e.target.value)}
      placeholder={placeholder}
    />
  );
};

export default Input;
