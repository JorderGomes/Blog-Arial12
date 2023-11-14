import Button from "@/components/Button";

const ComentarioRow = ({
  comentario,
  handleEditClick,
  handleDeleteClick,
  handleViewClick,
  index,
}) => {
  return (
    <tr key={index}>
      <td>{comentario.id}</td>
      <td>{comentario.corpo}</td>
      <td>{comentario.dataDeCriacao}</td>
      <td>{comentario.usuario.id}</td>
      <td>
        <div style={{ display: "flex", justifyContent: "space-evenly" }}>
          <Button
            callback={(event) => handleEditClick(event, comentario.id)}
            textoDoBotao={"Editar"}
          ></Button>
          <Button
            callback={(event) => handleDeleteClick(event, comentario.id)}
            textoDoBotao={"Deletar"}
          ></Button>
        </div>
      </td>
    </tr>
  );
};

export default ComentarioRow;
