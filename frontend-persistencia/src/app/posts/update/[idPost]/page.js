"use client";
import Button from "@/components/Button";
import SubPageContainer from "@/components/SubPageContainer";
import axios from "axios";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

const UsuarioPage = ({ params }) => {
  const [titulo, setTitulo] = useState("");
  const [corpo, setCorpo] = useState("");
  const [rate, setRate] = useState("");
  const [categoria, setCategoria] = useState("");

  const [post, setPost] = useState(null);

  const { idPost } = params;
  const router = new useRouter();

  useEffect(() => {
    async function getPost() {
      if (post !== null) {
        return;
      }

      const response = await axios
        .get(`http://localhost:8080/posts/${idPost}`)
        .then((response) => response.data);

      if (response === null) {
        alert("Post não encontrado!");
        router.push("/posts/list");
      }

      setTitulo(response.titulo);
      setCorpo(response.corpo);
      setRate(response.rate);
      setCategoria(response.categoria);
      setPost(response);
      console.log(response);
    }
    getPost();
  });

  async function submitHandler(event) {
    event.preventDefault();
    const payload = {
      titulo,
      corpo,
      rate,
      categoria
    };
    await axios
      .put(`http://localhost:8080/posts/${post.id}`, payload)
      .then((response) => {
        if (response.status === 200) {
          router.push("/posts/list");
          alert("Post atualizado com sucesso!");
        } else {
          router.push("/posts/list");
          alert("Erro ao atualizar post!");
        }
      })
      .catch((error) => {
        console.log(error);
        alert("Erro ao atualizar post!");
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
      case "categoria":
        setCategoria(value);
        break;
      case "rate":
        setRate(value);
        break;
    }
  }

  return (
    <SubPageContainer
      menuTitle="Formulário de edição de posts"
      returnCallback={() => router.push("/posts/list")}
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
          textoDoBotao={"Atualizar post"}
          type={"submit"}
          className={" mt-4 "}
        ></Button>
      </form>
    </SubPageContainer>
  );
};

export default UsuarioPage;
