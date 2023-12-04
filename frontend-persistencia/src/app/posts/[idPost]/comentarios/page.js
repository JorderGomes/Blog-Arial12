"use client";
import SubPageContainer from "@/components/SubPageContainer";
import axios from "axios";
import { useEffect, useRef, useState } from "react";
import ComentarioRow from "./ComentarioRow";
import { useRouter } from "next/navigation";
import Button from "@/components/Button";

const PostDeUsuarioPage = ({ params }) => {
  const { idPost } = params;
  const [post, setPost] = useState(null);
  const [comentarios, setComentarios] = useState(null);
  const [loading, setLoading] = useState(true);
  const [titulo, setTitulo] = useState("");
  const prev = useRef();
  const router = useRouter();

  useEffect(() => {
    getData();
  });

  async function getData() {
    console.log(comentarios);
    if (!loading) {
      if (post === null) {
        setTitulo("Não foi achado nenhum post com esse id");
        return;
      }
      if (comentarios === null) {
        setTitulo("Não foi achado nenhum comentário nesse post");
        return;
      }
      if (comentarios.length === 0) {
        setTitulo("Não foi achado nenhum comentário nesse post");
        return;
      }
      setTitulo(`Esses são os comentarios do post ${idPost}`);
    }

    if (prev.current === comentarios) return;
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

    const comentariosResponse = await axios
      .get(`http://localhost:8080/comentarios/post/${idPost}`)
      .then((response) => response.data)
      .catch((error) => {
        console.log(error);
        return null;
      });

    if (comentariosResponse === null) {
      setLoading(false);
      return;
    }

    prev.current = comentariosResponse;
    setLoading(false);
    setPost(response);
    setComentarios(comentariosResponse);
  }

  async function removerComentario(event, id) {
    event.preventDefault();
    const confirmation = confirm("Deseja deletar o comentário?");
    if (!confirmation) {
      alert("Comentário não deletado!");
      return;
    }
    const [...novosComentarios] = [...comentarios];
    const index = comentarios.findIndex((comentario) => comentario.id === id);
    novosComentarios.splice(index, 1);
    console.log("deletar");
    await axios
      .delete(`http://localhost:8080/comentarios/${id}`)
      .then((response) => {
        if (response.status !== 200) {
          alert("Comentario deletado com sucesso!");
          setComentarios(novosComentarios);
        } else {
          alert("Erro ao deletar comentario!");
        }
      })
      .catch((error) => {
        console.log(error);
        alert("Erro ao deletar comentario!");
      });
  }

  async function editarComentario(event, id) {
    console.log("editar");
    event.preventDefault();
    router.push(`/comentarios/${idPost}/${id}/update`);
  }

  function definePagina() {
    if (post === null)
      return (
        <>
          <SubPageContainer
            menuTitle={titulo}
            returnCallback={() => router.push(`/posts/${idPost}`)}
          ></SubPageContainer>
        </>
      );
    return (
      <SubPageContainer
        menuTitle={titulo}
        returnCallback={() => router.push(`/posts/${idPost}`)}
      >
        <div
          style={{
            display: "flex",
            flexDirection: "row",
            justifyContent: "end",
            width: "100%",
          }}
        >
          <Button
            callback={() => router.push(`/comentarios/${idPost}/create`)}
            textoDoBotao={"Criar novo comentario"}
            className={"mt-4 mb-4"}
          ></Button>
        </div>
        <table className="table table-dark table-bordered">
          <thead>
            <tr>
              <th> Id</th>
              <th> Conteudo</th>
              <th> Data de Criação</th>
              <th> Id Usuario</th>
              <th> Opções</th>
            </tr>
          </thead>
          <tbody>
            {comentarios &&
              comentarios.map((comentario, index) => (
                <ComentarioRow
                  key={index}
                  comentario={comentario}
                  handleEditClick={editarComentario}
                  handleDeleteClick={removerComentario}
                ></ComentarioRow>
              ))}
          </tbody>
        </table>
      </SubPageContainer>
    );
  }

  return definePagina();
};

export default PostDeUsuarioPage;
