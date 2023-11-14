/* eslint-disable @next/next/no-async-client-component */
"use client";

import SubPageContainer from "@/components/SubPageContainer";
import axios from "axios";
import Button from "@/components/Button";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

const AtualizarComentarioPage = ({ params }) => {
  const [corpo, setCorpo] = useState("");
  const [comentario, setComentario] = useState(null);
  const { idPost, idComentario } = params;

  const router = useRouter();

  useEffect(() => {
    async function getComentario() {
      if (comentario !== null) {
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
        alert("Comentario não encontrado!");
        router.push(`/posts/${idPost}/comentarios`);
      }

      setCorpo(response.corpo);
      setComentario(response);
      console.log(response);
    }
    getComentario();
  });

  async function submitHandler(event) {
    event.preventDefault();
    const payload = {
      corpo,
      dataDeCriacao: comentario.dataDeCriacao,
    };
    await axios
      .put(
        `http://localhost:8080/comentarios/${comentario.id}?idPost=${idPost}&idUsuario=${comentario.usuario.id}`,
        payload
      )
      .then((response) => {
        if (response.status === 200) {
          router.push(`/posts/${idPost}/comentarios`);
          alert("Comentário editado com sucesso!");
        } else {
          alert("Erro ao criar comentário!");
        }
      })
      .catch((error) => {
        console.log(error);
        alert("Erro ao criar comentário!");
      });
  }

  function handleChange(event) {
    const { name, value } = event.target;
    switch (name) {
      case "corpo":
        setCorpo(value);
        break;
      case "dataDeCriacao":
        setDataDeCriacao(value);
        break;
    }
  }

  return (
    <SubPageContainer
      menuTitle={"Formulário de edição de comentário - comentário " + idComentario}
      returnCallback={() => router.push(`/posts/${idPost}/comentarios`)}
    >
      <form
        onSubmit={submitHandler}
        style={{ display: "flex", flexDirection: "column", width: "50%" }}
      >
        <label className="form-label" htmlFor="corpo">
          Corpo
        </label>
        <input
          className="form-control"
          type="text"
          id="corpo"
          name="corpo"
          onChange={handleChange}
          value={corpo}
        />

        <Button
          callback={() => {}}
          textoDoBotao={"Atualizar Comentário"}
          type={"submit"}
          className={" mt-4 "}
        ></Button>
      </form>
    </SubPageContainer>
  );
};

export default AtualizarComentarioPage;
