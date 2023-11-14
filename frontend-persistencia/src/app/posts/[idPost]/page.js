"use client";
import Button from "@/components/Button";
import SubPageContainer from "@/components/SubPageContainer";
import axios from "axios";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

const PostPage = ({ params }) => {
  const [post, setPost] = useState(null);
  const [titulo, setTitulo] = useState("");
  const [loading, setLoading] = useState(true);

  const router = new useRouter();

  useEffect(() => {
    async function getPost() {
      if (!loading) {
        if (post === null) {
          setTitulo("Post não encontrado!");
          return;
        }
        setTitulo("Dados do post");
        return;
      }

      const response = await axios
        .get(`http://localhost:8080/posts/${params.idPost}`)
        .then((response) => response.data)
        .catch((error) => {
          console.log(error);
          return null;
        });

      if (response === null) {
        setLoading(false);
        return;
      }

      setPost(response);
      setLoading(false);
    }

    if (loading) {
      setTitulo("Carregando...");
    }
    getPost();
  }, [loading, post, params.idPost]);

  function definePagina() {
    if (post === null) return <></>;
    return (
      <>
        <table className="table table-dark table-bordered">
          <thead>
            <tr>
              <th> Titulo</th>
              <th> Corpo</th>
              <th> Avaliação</th>
              <th> Categoria</th>
              <th> Data de Criação</th>
              <th> Id</th>
              <th> Id do Usuário</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>{post.titulo}</td>
              <td>{post.corpo}</td>
              <td>{post.rate}</td>
              <td>{post.categoria}</td>
              <td>{post.dataDeCriacao}</td>
              <td>{post.id}</td>
              <td>{post.usuario.id}</td>
            </tr>
          </tbody>
        </table>
        <Button
          callback={() => router.push(`/posts/${params.idPost}/comentarios`)}
          textoDoBotao={"Ver comentários do post"}
        ></Button>
      </>
    );
  }
  return (
    <SubPageContainer
      returnCallback={() => router.push("/posts/list")}
      menuTitle={titulo}
    >
      {definePagina()}
    </SubPageContainer>
  );
};

export default PostPage;
