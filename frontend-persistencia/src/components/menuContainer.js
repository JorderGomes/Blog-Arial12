const MenuContainer = ({ children }) => {
  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "flex-start",
        width: "100%",
        height: "100%",
      }}
    >
      {children}
    </div>
  );
};

export default MenuContainer;
