import Button from "@/components/Button";

const PostRow = ({
  post,
  handleViewClick,
  index,
}) => {
  return (
    <tr key={index}>
      <td>{post.id}</td>
      <td>{post.titulo}</td>
      <td>{post.corpo}</td>
      <td>{post.rate}</td>
      <td>{post.categoria}</td>
      <td>{post.dataDeCriacao}</td>
      <td>{post.usuario.id}</td>
      <td>
        <div style={{ display: "flex", justifyContent: "space-evenly" }}>
          <Button
            callback={(event) => handleViewClick(event, post.id)}
            textoDoBotao={"Visualizar"}
          ></Button>
        </div>
      </td>
    </tr>
  );
};

export default PostRow;
