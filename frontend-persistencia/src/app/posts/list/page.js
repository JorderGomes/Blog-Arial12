"use client";
import axios from "axios";
import { useEffect, useRef, useState } from "react";
import { useRouter } from "next/navigation";
import SubPageContainer from "@/components/SubPageContainer";
import PostRow from "./PostRow";

const ListarUsuariosPage = () => {
  const [posts, setPosts] = useState([]);
  const prev = useRef();
  const router = useRouter();
  useEffect(() => {
    async function getPosts() {
      if (prev.current === posts) return;
      const response = await axios
        .get(`http://localhost:8080/posts`)
        .then((response) => response.data);

      if (response === null) {
        alert("Nenhum Post foi encontrado!");
      }
      prev.current = response;

      setPosts(response);
    }
    getPosts();
  }, [posts]);

  async function removerPost(event, id) {
    event.preventDefault();
    const confirmation = confirm("Deseja deletar o post?");
    if (!confirmation) {
      alert("Post não deletado!");
      return;
    }
    const [...novosPosts] = [...posts];
    const index = posts.findIndex((post) => post.id === id);
    novosPosts.splice(index, 1);
    console.log("deletar");
    await axios
      .delete(`http://localhost:8080/posts/${id}`)
      .then((response) => {
        if (response.status !== 200) {
          alert("Post deletado com sucesso!");
          setPosts(novosPosts);
        } else {
          alert("Erro ao deletar post!");
        }
      })
      .catch((error) => {
        console.log(error);
        alert("Erro ao deletar post!");
      });
  }

  async function editarPoat(event, id) {
    console.log("editar");
    event.preventDefault();
    router.push(`/posts/update/${id}`);
  }

  return (
    <SubPageContainer
      returnCallback={() => router.push("/posts")}
      menuTitle="Lista de Posts"
    >
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
          {posts === null ? (
            <></>
          ) : (
            posts.map((post, index) => (
              <PostRow
                key={post.id}
                index={index}
                post={post}
                handleDeleteClick={removerPost}
                handleEditClick={editarPoat}
                handleViewClick={() => router.push(`/posts/${post.id}`)}
              ></PostRow>
            ))
          )}
        </tbody>
      </table>
    </SubPageContainer>
  );
};

export default ListarUsuariosPage;
