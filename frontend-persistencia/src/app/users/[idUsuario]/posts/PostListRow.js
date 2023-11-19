import Button from "@/components/Button";

const PostListRow = ({ post, handleEditClick, handleDeleteClick, index }) => {
  return (
    <tr key={index}>
      <td>{post.id}</td>
      <td>{post.titulo}</td>
      <td>{post.corpo}</td>
      <td>{post.rate}</td>
      <td>{post.categoria}</td>
      <td>{post.dataDeCriacao}</td>
      <td>
        <div style={{ display: "flex", justifyContent: "space-evenly" }}>
          <Button
            callback={(event) => handleEditClick(event, post.id)}
            textoDoBotao={"Editar"}
          ></Button>
          <Button
            callback={(event) => handleDeleteClick(event, post.id)}
            textoDoBotao={"Deletar"}
          ></Button>
        </div>
      </td>
    </tr>
  );
};

export default PostListRow;
