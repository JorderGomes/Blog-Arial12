const Button = ({ callback, textoDoBotao, type, className }) => {
  return (
    <button
      className={`btn btn-light btn-lg btn-block p-2 ${className??'m-1'}`}
      style={{ fontSize: "1rem", fontWeight: "500"}}
      onClick={callback}
      type={type ?? "button"}
    >
      {textoDoBotao}
    </button>
  );
};

export default Button;
