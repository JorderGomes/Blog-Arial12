"use client";
import SubPageContainer from "@/components/SubPageContainer";
import axios from "axios";
import { useEffect, useRef, useState } from "react";
import PostRow from "./PostListRow";
import { useRouter } from "next/navigation";

const PostsDeUsuarioPage = ({ params }) => {
  const { idUsuario } = params;
  const [usuario, setUsuario] = useState(null);
  const [posts, setPosts] = useState(null);
  const [loading, setLoading] = useState(true);
  const [titulo, setTitulo] = useState("");
  const prev = useRef();
  const router = useRouter();

  useEffect(() => {
    getData();
  });

  async function getData() {
    console.log(posts);
    if (!loading) {
      if (usuario === null) {
        setTitulo("Não foi achado usuário");
        return;
      }
      if (posts === null) {
        setTitulo("Não foi achado nenhum post do usuário");
        return;
      }
      if (posts.length === 0) {
        setTitulo("Não foi achado nenhum post do usuário");
        return;
      }
      setTitulo(`Esses são os posts do usuário ${usuario.name}`);
    }

    if (prev.current === posts) return;
    const response = await axios
      .get(`http://localhost:8080/usuarios/${params.idUsuario}`)
      .then((response) => response.data)
      .catch((error) => {
        console.log(error);
        return null;
      });

    if (response === null) {
      setLoading(false);
      return;
    }

    const postResponse = await axios
      .get(`http://localhost:8080/posts/user/${idUsuario}`)
      .then((response) => response.data)
      .catch((error) => {
        console.log(error);
        return null;
      });

    if (postResponse === null) {
      setLoading(false);
      return;
    }

    prev.current = postResponse;
    setLoading(false);
    setUsuario(response);
    setPosts(postResponse);
  }

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

  async function editarPost(event, id) {
    console.log("editar");
    event.preventDefault();
    router.push(`/posts/update/${id}`);
  }

  return (
    <SubPageContainer
      menuTitle={titulo}
      returnCallback={() => router.push(`/users/${idUsuario}`)}
    >
      <table className="table table-dark table-bordered">
        <thead>
          <tr>
            <th> Id</th>
            <th> Titulo</th>
            <th> Conteudo</th>
            <th> Avaliação</th>
            <th> Categoria</th>
            <th> Data de Criação</th>
            <th> Opções</th>
          </tr>
        </thead>
        <tbody>
          {posts &&
            posts.map((post, index) => (
              <PostRow
                key={index}
                post={post}
                handleEditClick={editarPost}
                handleDeleteClick={removerPost}
              ></PostRow>
            ))}
        </tbody>
      </table>
    </SubPageContainer>
  );
};

export default PostsDeUsuarioPage;
