/* eslint-disable @next/next/no-async-client-component */
"use client";

import SubPageContainer from "@/components/SubPageContainer";
import axios from "axios";
import Button from "@/components/Button";
import { useRouter } from "next/navigation";
import { useState } from "react";

const CriarUsuarioPage = () => {
  const [titulo, setTitulo] = useState("");
  const [corpo, setCorpo] = useState("");
  const [rate, setRate] = useState("");
  const [categoria, setCategoria] = useState("");
  const [idUsuario, setIdUsuario] = useState("");

  const router = useRouter();

  async function submitHandler(event) {
    event.preventDefault();
    const payload = {
      titulo,
      corpo,
      rate,
      categoria,
      dataDeCriacao: new Date(),
    };
    await axios
      .post(`http://localhost:8080/posts?userId=${idUsuario}`, payload)
      .then((response) => {
        if (response.status === 200) {
          router.push("/posts/list");
          alert("Post criado com sucesso!");
        } else {
          alert("Erro ao criar post!");
        }
      })
      .catch((error) => {
        console.log(error);
        alert("Erro ao criar post!");
      });
  }

  function handleChange(event) {
    const { name, value } = event.target;
    switch (name) {
      case "titulo":
        setTitulo(value);
        break;
      case "corpo":
        setCorpo(value);
        break;
      case "rate":
        setRate(value);
        break;
      case "categoria":
        setCategoria(value);
        break;
      case "idUsuario":
        setIdUsuario(value);
        break;
    }
  }

  return (
    <SubPageContainer
      menuTitle="Formulário de criação de post"
      returnCallback={() => router.push("/posts")}
    >
      <form
        onSubmit={submitHandler}
        style={{ display: "flex", flexDirection: "column", width: "50%" }}
      >
        <label className="form-label" htmlFor="titulo">
          Titulo do post
        </label>
        <input
          className="form-control"
          type="text"
          id="titulo"
          name="titulo"
          onChange={handleChange}
          value={titulo}
        />

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
          Id do usuario que criou o post
        </label>
        <input
          className="form-control"
          type="text"
          id="idUsuario"
          name="idUsuario"
          onChange={handleChange}
          value={idUsuario}
        />

        <label className="form-label" htmlFor="categoria">
          Categoria
        </label>
        <input
          className="form-control"
          type="text"
          id="categoria"
          name="categoria"
          onChange={handleChange}
          value={categoria}
        />

        <label className="form-label" htmlFor="rate">
          Avaliação
        </label>
        <input
          className="form-control"
          type="number"
          id="rate"
          name="rate"
          placeholder="0.0-5"
          max={5}
          min={0}
          onChange={handleChange}
          value={rate}
        />

        <Button
          callback={() => {}}
          textoDoBotao={"Criar Post"}
          type={"submit"}
          className={" mt-4 "}
        ></Button>
      </form>
    </SubPageContainer>
  );
};

export default CriarUsuarioPage;
