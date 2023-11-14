/* eslint-disable @next/next/no-async-client-component */
"use client";

import Button from "@/components/Button";
import SubPageContainer from "@/components/SubPageContainer";
import axios from "axios";
import { useRouter } from "next/navigation";
import { useState } from "react";
import PostRow from "./PostRow";

const FindPostByRatePage = () => {
  const [posts, setPosts] = useState([]);
  const [rate, setRate] = useState("");
  const [loaded, setLoaded] = useState(false);
  const router = useRouter();

  async function visualizarPost(event, id) {
    event.preventDefault();
    router.push(`/posts/${id}`);
  }

  async function fazConsulta() {
    const response = await axios
      .get(`http://localhost:8080/posts/search/rate?minRate=${rate}`)
      .then((response) => response.data)
      .catch((error) => {
        console.log(error);
        return null;
      });

    setLoaded(true);
    if (response === null) {
      alert("Nenhum Post foi encontrado!");
      return;
    }

    setPosts(response);
  }

  function renderPosts() {
    if (posts === null) {
      return <p>Não foi possível achar nenhum post;</p>;
    } else if (posts.length === 0) {
      return <p>Não foi possível achar nenhum post;</p>;
    } else {
      return (
        <table
          className="table table-dark table-bordered"
          style={{ width: "80%" }}
        >
          <thead>
            <tr>
              <th> Id</th>
              <th> Titulo</th>
              <th> Conteudo</th>
              <th> Avaliação</th>
              <th> Categoria</th>
              <th> Data de Criação</th>
              <th> Id De Usuario</th>
              <th> Opções</th>
            </tr>
          </thead>
          <tbody className="table-group-divider">
            {posts.map((post, index) => (
              <PostRow
                key={post.id}
                index={index}
                post={post}
                handleViewClick={visualizarPost}
              ></PostRow>
            ))}
          </tbody>
        </table>
      );
    }
  }

  function handleChange(event) {
    setRate(event.target.value);
    console.log(rate);
  }

  return (
    <SubPageContainer
      returnCallback={() => router.push("/posts")}
      menuTitle="Lista de Posts por Nota"
    >
      {loaded ? renderPosts() : <></>}

      <p>
        Digite abaixo a nota minima que você quer usar para procurar posts e
        depois clique em Carregar
      </p>
      <form>
        <input
          type="number"
          className="form-control mb-4"
          name="rate"
          id="rate"
          value={rate}
          onChange={handleChange}
          aria-describedby="helpId"
          placeholder="0.0-5"
        />
      </form>

      <Button
        callback={() => {
          fazConsulta();
        }}
        textoDoBotao={"Carregar os posts"}
      ></Button>
    </SubPageContainer>
  );
};

export default FindPostByRatePage;
