"use client";
import axios from "axios";
import { useEffect, useRef, useState } from "react";
import { useRouter } from "next/navigation";
import UserRow from "./UserListRow";
import SubPageContainer from "@/components/SubPageContainer";

const ListarUsuariosPage = () => {
  const [usuarios, setUsuarios] = useState([]);
  const prev = useRef();
  const router = useRouter();
  useEffect(() => {
    async function getUsuarios() {
      if (prev.current === usuarios) return;
      const response = await axios
        .get(`http://localhost:8080/usuarios`)
        .then((response) => response.data);

      if (response === null) {
        alert("Nenhum usuário foi encontrado!");
      }
      prev.current = response;

      setUsuarios(response);
    }
    getUsuarios();
  }, [usuarios]);

  async function removerUsuario(event, id) {
    event.preventDefault();
    const confirmation = confirm("Deseja deletar o usuário?");
    if (!confirmation) {
      alert("Usuário não deletado!");
      return;
    }
    const [...novosUsuarios] = [...usuarios];
    const index = usuarios.findIndex((usuario) => usuario.idUsuario === id);
    novosUsuarios.splice(index, 1);
    console.log("deletar");
    await axios
      .delete(`http://localhost:8080/usuarios/${id}`)
      .then((response) => {
        if (response.status !== 200) {
          alert("Usuário deletado com sucesso!");
          setUsuarios(novosUsuarios);
        } else {
          alert("Erro ao deletar usuário!");
        }
      })
      .catch((error) => {
        console.log(error);
        alert("Erro ao deletar usuário!");
      });
  }

  async function editarUsuario(event, id) {
    console.log("editar");
    event.preventDefault();
    router.push(`/users/update/${id}`);
  }

  return (
    <SubPageContainer
      returnCallback={() => router.push("/users")}
      menuTitle="Lista de Usuários"
    >
      <table
        className="table table-dark table-bordered"
        style={{ width: "80%" }}
      >
        <thead>
          <tr>
            <th>Nome</th>
            <th>Email</th>
            <th>Id</th>
            <th>Opções</th>
          </tr>
        </thead>
        <tbody className="table-group-divider">
          {usuarios.map((usuario, index) => (
            <UserRow
              key={usuario.id}
              index={index}
              usuario={usuario}
              handleDeleteClick={removerUsuario}
              handleEditClick={editarUsuario}
              handleViewClick={() => router.push(`/users/${usuario.id}`)}
            ></UserRow>
          ))}
        </tbody>
      </table>
    </SubPageContainer>
  );
};

export default ListarUsuariosPage;
