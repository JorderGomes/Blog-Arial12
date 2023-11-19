"use client";
import Button from "@/components/Button";
import SubPageContainer from "@/components/SubPageContainer";
import axios from "axios";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

const UsuarioPage = ({ params }) => {
  const [usuario, setUsuario] = useState(null);
  const [titulo, setTitulo] = useState("");
  const [loading, setLoading] = useState(true);

  const router = new useRouter();

  useEffect(() => {
    async function getUsuario() {
      if (!loading) {
        if (usuario === null) {
          setTitulo("Usuário não encontrado!");
          return;
        }
        setTitulo("Dados do usuario " + usuario.name);
        return;
      }

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

      setUsuario(response);
      setLoading(false);
    }

    if (loading) {
      setTitulo("Carregando...");
    }
    getUsuario();
  }, [loading, usuario, params.idUsuario]);

  function definePagina() {
    if (usuario === null) return <></>;
    return (
      <>
        <table className="table table-dark table-bordered">
          <thead>
            <tr>
              <th> Nome</th>
              <th> Email</th>
              <th> Bio</th>
              <th> Avaliação</th>
              <th> Data de Nascimento</th>
              <th> Id</th>
              <th> Senha</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>{usuario.name}</td>
              <td>{usuario.email}</td>
              <td>{usuario.bio}</td>
              <td>{usuario.rate}</td>
              <td>{usuario.dataDeNascimento}</td>
              <td>{usuario.id}</td>
              <td>{usuario.password}</td>
            </tr>
          </tbody>
        </table>
        <Button
          callback={() => router.push(`/users/${params.idUsuario}/posts`)}
          textoDoBotao={"Ver posts do usuário"}
        ></Button>
      </>
    );
  }
  return (
    <SubPageContainer
      returnCallback={() => router.push("/users/list")}
      menuTitle={titulo}
    >
      {definePagina()}
    </SubPageContainer>
  );
};

export default UsuarioPage;
