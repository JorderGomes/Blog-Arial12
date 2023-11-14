"use client";
import SubPageContainer from "@/components/SubPageContainer";
import axios from "axios";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

const ComentarioPage = ({ params }) => {
  const [comentario, setComentario] = useState(null);
  const [loading, setLoading] = useState(true);
  const [titulo, setTitulo] = useState(true);
  const { idPost, idComentario } = params;

  const router = new useRouter();

  useEffect(() => {
    async function getUsuario() {
      if (!loading) {
        if (comentario === null) {
          setTitulo("Comentario não encontrado!");
          return;
        }
        setTitulo("Dados do comentario " + comentario.name);
        return;
      }

      const response = await axios
        .get(`http://localhost:8080/comentarios/${idComentario}`)
        .then((response) => response.data)
        .catch((error) => {
          console.log(error);
          return null;
        });

      if (response === null) {
        setLoading(false);
        return;
      }

      setComentario(response);
      setLoading(false);
    }

    if (loading) {
      setTitulo("Carregando...");
    }
    getUsuario();
  }, [loading, comentario, params.idUsuario]);

  function definePagina() {
    if (comentario === null) return <></>;
    return (
      <table className="table table-dark table-bordered">
        <thead>
          <tr>
            <th> Id</th>
            <th> corpo</th>
            <th> Data de Criação</th>
            <th> Id de Usuário</th>
            <th> Id de Post</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>{comentario.id}</td>
            <td>{comentario.corpo}</td>
            <td>{comentario.dataDeCriacao}</td>
            <td>{comentario.usuario.id}</td>
            <td>{idPost}</td>
          </tr>
        </tbody>
      </table>
    );
  }
  return (
    <SubPageContainer
      returnCallback={() => router.push(`/posts/${idPost}/comentarios`)}
      menuTitle={"Dados do comentário " + idComentario}
    >
      {definePagina()}
    </SubPageContainer>
  );
};

export default ComentarioPage;
