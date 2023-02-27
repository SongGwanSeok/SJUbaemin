const Input = ({ type, onChange, placeholder }) => {
  return (
    <input
      className="Input"
      type={type}
      onChange={(e) => onChange(e.target.value)}
      placeholder={placeholder}
    />
  );
};

export default Input;
