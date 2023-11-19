import Button from "@/components/Button";

const UserListRow = ({
  usuario,
  handleEditClick,
  handleDeleteClick,
  handleViewClick,
  index,
}) => {
  return (
    <tr key={index}>
      <td>{usuario.name}</td>
      <td>{usuario.email}</td>
      <td>{usuario.id}</td>
      <td style={{ width: "10%" }}>
        <div style={{ display: "flex", justifyContent: "space-evenly" }}>
          <Button
            callback={(event) => handleEditClick(event, usuario.id)}
            textoDoBotao={"Editar"}
          ></Button>
          <Button
            callback={(event) => handleDeleteClick(event, usuario.id)}
            textoDoBotao={"Deletar"}
          ></Button>
          <Button
            callback={(event) => handleViewClick(event, usuario.id)}
            textoDoBotao={"Visualizar"}
          ></Button>
        </div>
      </td>
    </tr>
  );
};

export default UserListRow;
