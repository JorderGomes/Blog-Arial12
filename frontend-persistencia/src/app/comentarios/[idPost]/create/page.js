/* eslint-disable @next/next/no-async-client-component */
"use client";

import SubPageContainer from "@/components/SubPageContainer";
import axios from "axios";
import Button from "@/components/Button";
import { useRouter } from "next/navigation";
import { useState } from "react";

const CriarComentarioPage = ({params}) => {
  const [corpo, setCorpo] = useState("");
  const [dataDeCriacao, setDataDeCriacao] = useState("");
  const [idUsuario, setIdUsuario] = useState("");
  const { idPost } = params;

  const router = useRouter();

  async function submitHandler(event) {
    event.preventDefault();
    const payload = {
      corpo,
      dataDeCriacao,
    };
    await axios
      .post(`http://localhost:8080/comentarios?idPost=${idPost}&idUsuario=${idUsuario}`, payload)
      .then((response) => {
        if (response.status === 200) {
          router.push(`/posts/${idPost}/comentarios`);
          alert("Comentario criado com sucesso!");
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
      case "idUsuario":
        setIdUsuario(value);
        break;
    }
  }

  return (
    <SubPageContainer
      menuTitle="Formulário de criação de comentário"
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

        <label className="form-label" htmlFor="idUsuario">
          Id do usuário
        </label>
        <input
          className="form-control"
          type="text"
          id="idUsuario"
          name="idUsuario"
          min={1}
          onChange={handleChange}
          value={idUsuario}
        />

        <label className="form-label" htmlFor="dataDeCriacao">
          Data de criação
        </label>
        <input
          className="form-control"
          type="date"
          id="dataDeCriacao"
          name="dataDeCriacao"
          onChange={handleChange}
          value={dataDeCriacao}
        />

        <Button
          callback={() => {}}
          textoDoBotao={"Criar Comentário"}
          type={"submit"}
          className={" mt-4 "}
        ></Button>
      </form>
    </SubPageContainer>
  );
};

export default CriarComentarioPage;
